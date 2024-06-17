package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.model.enumeration.AdvancementLevel;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Instructor")
@DiscriminatorValue("instructor")
public class Instructor extends Diver{
    @Setter
    private AdvancementLevel advancementLevel;
    @OneToMany(mappedBy = "courseInstructor")
    private Set<DivingCourse> divingCourses;
    public Instructor(Person person, AdvancementLevel advancementLevel, String phoneNumber, String medicalInformation) {
        super(person, medicalInformation);
        setAdvancementLevel(advancementLevel);
        person.setPhoneNumber(phoneNumber);
        divingCourses = new HashSet<>();
    }

    public void assignToDivingCourse(DivingCourse divingCourse) {
        if(!divingCourses.contains(divingCourse)) {
            divingCourses.add(divingCourse);
            divingCourse.addInstructor(this);
        }
    }

    public void removeFromDivingCourse(DivingCourse divingCourse) {
        if(divingCourses.contains(divingCourse)) {
            divingCourses.remove(divingCourse);
            //divingCourse.removeInstructor(this);
        }
    }
}
