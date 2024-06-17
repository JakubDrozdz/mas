package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Course_Attendee")
public class CourseAttendee {
    @Id
    @GeneratedValue
    private Long courseAttendeeId;
    private String emergencyPhoneNumber;
    @OneToOne(mappedBy = "courseAttendee")
    private Person person;

    public CourseAttendee(Person person, String emergencyPhoneNumber) {
        setPerson(person);
        setEmergencyPhoneNumber(emergencyPhoneNumber);
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.person.setCourseAttendee(this);
    }

    public int getAge(){
        return LocalDate.now().getYear() - this.person.getBirthDate().getYear();
    }
}
