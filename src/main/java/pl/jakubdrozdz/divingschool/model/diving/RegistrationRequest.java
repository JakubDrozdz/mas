package pl.jakubdrozdz.divingschool.model.diving;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.model.diving.rent.EquipmentRent;
import pl.jakubdrozdz.divingschool.model.person.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class for RegistrationRequest entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Registration_Request")
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationRequestId;
    private int registrationRequestNumber;
    private LocalDateTime registrationDate;
    @ManyToOne
    @JoinColumn(name = "registration_requests_person", nullable = false)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "registration_requests_course", nullable = false)
    private DivingCourse divingCourse;
    @OneToMany(mappedBy = "registrationRequest")
    private Set<EquipmentRent> equipmentRents;

    public RegistrationRequest(int registrationRequestNumber, LocalDateTime registrationDate, Person person, DivingCourse divingCourse) {
        equipmentRents = new HashSet<>();
        setRegistrationDate(registrationDate);
        setPerson(person);
        setDivingCourse(divingCourse);
        setRegistrationRequestNumber(registrationRequestNumber);
    }

    public void setRegistrationRequestNumber(int registrationRequestNumber) {
        if(registrationRequestNumber <= 0) {
            throw new IllegalArgumentException("Registration request number must be greater than 0");
        }
        this.registrationRequestNumber = registrationRequestNumber;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if(registrationDate == null || registrationDate.isBefore(LocalDateTime.of(2020,1,1,0,0))) {
            throw new IllegalArgumentException("Registration date cannot be null and before 2020-01-01");
        }
        this.registrationDate = registrationDate;
    }

    /**
     * Method used to add association with Person object
     *
     * @param person instance of Person class
     */
    private void setPerson(Person person) {
        if(person == null){
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
        this.person.addRegistrationRequest(this);
    }

    /**
     * Method used to add association with DivingCourse object
     *
     * @param divingCourse instance of DivingCourse class
     */
    private void setDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null){
            throw new IllegalArgumentException("Diving course cannot be null");
        }
        this.divingCourse = divingCourse;
        this.divingCourse.addRegistrationRequest(this);
    }

    /**
     * Method used to add association with EquipmentRent object
     *
     * @param equipmentRent instance of EquipmentRent class
     */
    public void addEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null){
            throw new IllegalArgumentException("EquipmentRent cannot be null");
        } else if (!equipmentRent.getRegistrationRequest().equals(this)){
            throw new IllegalArgumentException("EquipmentRent has already been registered");
        }
        equipmentRents.add(equipmentRent);
    }

    //TODO: move to service
    /*public void removeEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null || !equipmentRents.contains(equipmentRent)){
            throw new IllegalArgumentException();
        }
        EquipmentRent.removeEquipmentRent(equipmentRent);
        equipmentRents.remove(equipmentRent);
    }*/
}
