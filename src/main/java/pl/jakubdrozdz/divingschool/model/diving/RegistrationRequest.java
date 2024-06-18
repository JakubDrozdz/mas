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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Registration_Request")
public class RegistrationRequest {
    @Id
    @GeneratedValue
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

    private void setPerson(Person person) {
        if(person == null){
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
        this.person.addRegistrationRequest(this);
    }

    private void setDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null){
            throw new IllegalArgumentException("Diving course cannot be null");
        }
        this.divingCourse = divingCourse;
        this.divingCourse.addRegistrationRequest(this);
    }

    public void addEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null){
            throw new IllegalArgumentException("EquipmentRent cannot be null");
        } else if (!equipmentRent.getRegistrationRequest().equals(this)){
            throw new IllegalArgumentException("EquipmentRent has already been registered");
        }
        equipmentRents.add(equipmentRent);
    }

    /*public void removeEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null || !equipmentRents.contains(equipmentRent)){
            throw new IllegalArgumentException();
        }
        EquipmentRent.removeEquipmentRent(equipmentRent);
        equipmentRents.remove(equipmentRent);
    }*/
}
