package pl.jakubdrozdz.divingschool.model.person;

import pl.jakubdrozdz.divingschool.model.certificate.CertificateOwnership;
import pl.jakubdrozdz.divingschool.model.enumeration.AdvancementLevel;
import java.time.LocalDate;

/**
 * Builder class for Person
 *
 * @author Jakub Drozdz
 */
public class PersonBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String emailAddress;
    private String phoneNumber;

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public PersonBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CourseAttendee buildAsCourseAttendee(String emergencyPhoneNumber){
        return new CourseAttendee(this.build(), emergencyPhoneNumber);
    }

    public Diver buildAsDiver(String medicalInformation, CertificateOwnership certificateOwnership){
        return new Diver(this.build(), medicalInformation, certificateOwnership);
    }

    public Instructor buildAsInstructor(String medicalInformation, AdvancementLevel advancementLevel, CertificateOwnership certificateOwnership){
        if(phoneNumber == null){
            throw new IllegalArgumentException("Phone number cannot be null for Instructor");
        }
        return new Instructor(this.build(), advancementLevel, phoneNumber, certificateOwnership, medicalInformation);
    }

    public Person build() {
        if(phoneNumber == null) {
            return new Person(firstName, lastName, birthDate, emailAddress);
        }
        return new Person(firstName, lastName, birthDate, emailAddress, phoneNumber);
    }
}
