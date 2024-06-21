package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Model class for CourseAttendee entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Course_Attendee")
public class CourseAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseAttendeeId;
    private String emergencyPhoneNumber;
    @OneToOne(mappedBy = "courseAttendee")
    private Person person;

    public CourseAttendee(Person person, String emergencyPhoneNumber) {
        setPerson(person);
        setEmergencyPhoneNumber(emergencyPhoneNumber);
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        if(emergencyPhoneNumber == null || !emergencyPhoneNumber.matches(".+\\d{10}")) {
            throw new IllegalArgumentException("Phone number is not valid (valid format start with + and is minimum 10 numbers long)");
        }
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    /**
     * Method used to create association with Person object
     *
     * @param person instance of Person class
     */
    public void setPerson(Person person) {
        this.person = person;
        this.person.setCourseAttendee(this);
    }

    /**
     * Method used to calculate course attendee age
     *
     * @return age of person (number of years)
     */
    public int getAge(){
        return LocalDate.now().getYear() - this.person.getBirthDate().getYear();
    }
}
