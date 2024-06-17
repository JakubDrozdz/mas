package pl.jakubdrozdz.divingschool.model.diving.rent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter

@Entity(name = "Equipment_Rent")
public class EquipmentRent {
    @Id
    @GeneratedValue
    private Long equipmentRentId;
    private int rentId;
    private LocalDateTime rentDate;
    private LocalDateTime rentReturnDate;
    @ManyToOne
    @JoinColumn(name = "registration_requests", nullable = false)
    private RegistrationRequest registrationRequest;
    @ManyToMany
    @JoinTable(
            name = "EquipmnetRent_Equipment",
            joinColumns = { @JoinColumn(name = "equipmentRentId") },
            inverseJoinColumns = { @JoinColumn(name = "equipmentId") }
    )
    private Set<DivingEquipment> equipmentSet;

    public EquipmentRent(int rentId, LocalDateTime rentDate, RegistrationRequest registrationRequest) {
        equipmentSet = new HashSet<>();
        setRentId(rentId);
        setRentDate(rentDate);
        setRegistrationRequest(registrationRequest);
    }

    public EquipmentRent(int rentId, LocalDateTime rentDate, LocalDateTime rentReturnDate, RegistrationRequest registrationRequest) {
        this(rentId, rentDate, registrationRequest);
        setRentReturnDate(rentReturnDate);
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public void setRentReturnDate(LocalDateTime rentReturnDate) {
        this.rentReturnDate = rentReturnDate;
    }

    public static EquipmentRent createEquipmentRent(){
        return null;
    }

    public void setRegistrationRequest(RegistrationRequest registrationRequest) {
        if(registrationRequest == null){
            throw new IllegalArgumentException("Registration request cannot be null");
        }
        if(this.registrationRequest != null) {
            throw new IllegalArgumentException("Equipment rent can only be assigned to one registration request");
        }
        this.registrationRequest = registrationRequest;
        registrationRequest.addEquipmentRent(this);
    }

    public static void removeEquipmentRent(EquipmentRent equipmentRent) {
        //if(extent.contains(parkingSpot)){
            //extent.remove(equipmentRent);
            RegistrationRequest registrationRequestTmp = equipmentRent.getRegistrationRequest();
            equipmentRent.registrationRequest = null;
            registrationRequestTmp.removeEquipmentRent(equipmentRent);
        //}
    }

    public void addEquipment(DivingEquipment equipment) {
        if(!this.equipmentSet.contains(equipment)){
            this.equipmentSet.add(equipment);
            equipment.addEquipmentRent(this);
        }
    }
}
