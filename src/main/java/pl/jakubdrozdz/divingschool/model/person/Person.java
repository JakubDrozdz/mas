package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity(name = "Person")
public class Person {
    @Id
    @GeneratedValue
    private Long personId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String emailAddress;
    private String phoneNumber;
    @OneToMany(mappedBy = "person")
    private Set<RegistrationRequest> registrationRequests;
    @OneToOne
    private Diver diver;
    @OneToOne
    private CourseAttendee courseAttendee;


    public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress) {
        registrationRequests = new HashSet<>();
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmailAddress(emailAddress);
    }

    public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress, String phoneNumber) {
        this(firstName, lastName, birthDate, emailAddress);
        setPhoneNumber(phoneNumber);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addRegistrationRequest(RegistrationRequest registrationRequest) {
        if(this.registrationRequests.contains(registrationRequest)){
            //TODO: add detailed exception or do not throw?
            throw new IllegalArgumentException("Cannot add duplicated registration request");
        }
        registrationRequests.add(registrationRequest);
    }

    public void setDiver(Diver diver) {
        if(this.diver == null && diver != null) {
            this.diver = diver;
        }
    }

    public void setCourseAttendee(CourseAttendee courseAttendee) {
        if(this.courseAttendee == null && courseAttendee != null) {
            this.courseAttendee = courseAttendee;
        }
    }
}
