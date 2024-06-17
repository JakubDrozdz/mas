package pl.jakubdrozdz.divingschool.model.diving.rent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Equipment_Rent")
public class EquipmentRent {
    @Id
    @GeneratedValue
    private Long equipmentRentId;
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

    public EquipmentRent(LocalDateTime rentDate, RegistrationRequest registrationRequest) {
        equipmentSet = new HashSet<>();
        setRentDate(rentDate);
        setRegistrationRequest(registrationRequest);
    }

    public EquipmentRent(LocalDateTime rentDate, LocalDateTime rentReturnDate, RegistrationRequest registrationRequest) {
        this(rentDate, registrationRequest);
        setRentReturnDate(rentReturnDate);
    }

    public void setRentDate(LocalDateTime rentDate) {
        if(rentDate == null || rentDate.isBefore(LocalDateTime.of(2020,1,1,0,0))){
            throw new IllegalArgumentException("Rent date cannot be null and before 2020-01-01");
        } else if (rentReturnDate != null && !rentDate.isBefore(rentReturnDate)) {
            throw new IllegalArgumentException("Rent date cannot be after return date");
        }
        this.rentDate = rentDate;
    }

    public void setRentReturnDate(LocalDateTime rentReturnDate) {
        if(rentReturnDate == null || rentReturnDate.isBefore(LocalDateTime.of(2020,1,1,0,0))){
            throw new IllegalArgumentException("Rent date cannot be null and before 2020-01-01");
        } else if (rentDate != null && !rentReturnDate.isAfter(rentDate)) {
            throw new IllegalArgumentException("Return date cannot be before rent date");
        }
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
