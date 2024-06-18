package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @OneToOne(cascade = CascadeType.ALL)
    private Diver diver;
    @OneToOne(cascade = CascadeType.ALL)
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
        if(StringUtils.isBlank(firstName)){
            throw new IllegalArgumentException("First name cannot be blank");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(StringUtils.isBlank(lastName)){
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        if(birthDate == null){
            throw new IllegalArgumentException("Birth date cannot be null");
        } else if(birthDate.isBefore(LocalDate.of(1900,1,1))){
            throw new IllegalArgumentException("Birth date cannot be before 1900-01-01");
        }
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(emailAddress)){
            throw new IllegalArgumentException("Email is not valid");
        }
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null && this.diver instanceof Instructor){
            throw new IllegalArgumentException("Cannot set null phone number for instructor");
        } else if(phoneNumber != null && !phoneNumber.matches(".+\\d{10}")) {
            throw new IllegalArgumentException("Phone number is not valid (valid format start with + and is minimum 10 numbers long)");
        }
        this.phoneNumber = phoneNumber;
    }

    public void addRegistrationRequest(RegistrationRequest registrationRequest) {
        if(registrationRequest == null) {
            throw new IllegalArgumentException("Registration request cannot be null");
        }
        if(this.registrationRequests.contains(registrationRequest)){
            throw new IllegalArgumentException("Cannot add duplicated registration request");
        }
        registrationRequests.add(registrationRequest);
    }

    public void setDiver(Diver diver) {
        if(this.diver != null && diver != null) {
            throw new IllegalArgumentException("Cannot change Diver");
        } else {
            this.diver = diver;
        }
    }

    public void setCourseAttendee(CourseAttendee courseAttendee) {
        if(this.courseAttendee != null && courseAttendee != null) {
            throw new IllegalArgumentException("Cannot change CourseAttendee");
        } else {
            this.courseAttendee = courseAttendee;
        }
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }
}
