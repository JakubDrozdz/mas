package pl.jakubdrozdz.divingschool.model.diving.rent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class for EquipmentRent entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Equipment_Rent")
public class EquipmentRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentRentId;
    private LocalDateTime rentDate;
    private LocalDateTime rentReturnDate;
    @ManyToOne
    @JoinColumn(name = "registration_requests", nullable = false)
    private RegistrationRequest registrationRequest;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "EquipmnetRent_Equipment",
            joinColumns = { @JoinColumn(name = "equipmentRentId") },
            inverseJoinColumns = { @JoinColumn(name = "equipmentId") }
    )
    private Set<DivingEquipment> equipmentSet;

    public EquipmentRent(LocalDateTime rentDate, RegistrationRequest registrationRequest, Set<DivingEquipment> equipment) {
        equipmentSet = new HashSet<>();
        setRentDate(rentDate);
        setRegistrationRequest(registrationRequest);
        addEquipments(equipment);
    }

    public EquipmentRent(LocalDateTime rentDate, LocalDateTime rentReturnDate, RegistrationRequest registrationRequest, Set<DivingEquipment> equipment) {
        this(rentDate, registrationRequest, equipment);
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

    /**
     * Method used to add association with RegistrationRequest class
     *
     * @param registrationRequest instance of RegistrationRequest class
     */
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

    /**
     * Method used to add association with DivingEquipment class
     *
     * @param equipment instance of DivingEquipment class
     */
    public void addEquipment(DivingEquipment equipment) {
        if(equipment == null){
            throw new IllegalArgumentException("Cannot add null diving equipment");
        }
        if(!this.equipmentSet.contains(equipment)){
            this.equipmentSet.add(equipment);
            equipment.addEquipmentRent(this);
        }
    }

    /**
     * Method used to add associations with objects of DivingEquipment class
     *
     * @param equipment set of DivingEquipment instances
     */
    public void addEquipments(Set<DivingEquipment> equipment) {
        if(equipment == null || equipment.isEmpty()){
            throw new IllegalArgumentException("Equipment list cannot be null or empty");
        }
        for(DivingEquipment eq : equipment){
            this.addEquipment(eq);
        }
    }

    //TODO: move to service?
    public static EquipmentRent createEquipmentRent(){
        return null;
    }

    //TODO: move to service?
    /*public static void removeEquipmentRent(EquipmentRent equipmentRent) {
        //if(extent.contains(parkingSpot)){
            //extent.remove(equipmentRent);
            RegistrationRequest registrationRequestTmp = equipmentRent.getRegistrationRequest();
            equipmentRent.registrationRequest = null;
            registrationRequestTmp.removeEquipmentRent(equipmentRent);
        //}
    }*/
}
