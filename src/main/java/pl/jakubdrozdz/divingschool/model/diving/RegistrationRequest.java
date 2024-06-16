package pl.jakubdrozdz.divingschool.model.diving;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.model.diving.rent.EquipmentRent;
import pl.jakubdrozdz.divingschool.model.person.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class RegistrationRequest {
    private int registrationRequestNumber;
    private LocalDateTime registrationDate;
    private Person person;
    private DivingCourse divingCourse;
    private Set<EquipmentRent> equipmentRents;

    public RegistrationRequest(int registrationRequestNumber, LocalDateTime registrationDate, Person person, DivingCourse divingCourse) {
        equipmentRents = new HashSet<>();
        setRegistrationRequestNumber(registrationRequestNumber);
        setRegistrationDate(registrationDate);
        setPerson(person);
        setDivingCourse(divingCourse);
    }

    public void setRegistrationRequestNumber(int registrationRequestNumber) {
        this.registrationRequestNumber = registrationRequestNumber;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    private void setPerson(Person person) {
        this.person = person;
        this.person.addRegistrationRequest(this);
    }

    private void setDivingCourse(DivingCourse divingCourse) {
        this.divingCourse = divingCourse;
        this.divingCourse.addRegistrationRequest(this);
    }

    public void addEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null || !equipmentRent.getRegistrationRequest().equals(this)){
            //TODO
            throw new IllegalArgumentException();
        }
        equipmentRents.add(equipmentRent);
    }

    public void removeEquipmentRent(EquipmentRent equipmentRent) {
        if(equipmentRent == null || !equipmentRents.contains(equipmentRent)){
            throw new IllegalArgumentException();
        }
        EquipmentRent.removeEquipmentRent(equipmentRent);
        equipmentRents.remove(equipmentRent);
    }
}
