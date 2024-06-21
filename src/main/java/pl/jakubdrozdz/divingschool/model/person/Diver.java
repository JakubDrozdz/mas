package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateOwnership;
import pl.jakubdrozdz.divingschool.model.diving.Diving;
import pl.jakubdrozdz.divingschool.model.diving.DivingSpot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class for Diver entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Diver")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("diver")
public class Diver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diverId;
    private String medicalInformation;
    @OneToOne(mappedBy = "diver", cascade = CascadeType.PERSIST)
    private Person person;
    @OneToMany(mappedBy = "diver", cascade = CascadeType.REMOVE)
    private Set<Diving> divings;
    @OneToMany(mappedBy = "diver", cascade = CascadeType.REMOVE)
    private Set<CertificateOwnership> certificateOwnershipSet;

    public Diver(Person person, String medicalInformation, CertificateOwnership certificateOwnership) {
        setPerson(person);
        setMedicalInformation(medicalInformation);
        certificateOwnershipSet = new HashSet<>();
        divings = new HashSet<>();
        addCertificateOwnership(certificateOwnership);
    }

    public void setMedicalInformation(String medicalInformation) {
        if(StringUtils.isBlank(medicalInformation) || medicalInformation.trim().length() >= 500){
            throw new IllegalArgumentException("Medical information should be max 500 characters long and not be blank");
        }
        this.medicalInformation = medicalInformation;
    }

    /**
     * Method used to add association with Person object
     *
     * @param person instance of Person class
     */
    public void setPerson(Person person) {
        this.person = person;
        this.person.setDiver(this);

    }

    /**
     * Method used to add association with CertificateOwnership object
     *
     * @param certificateOwnership instance of CertificateOwnership class
     */
    public void addCertificateOwnership(CertificateOwnership certificateOwnership) {
        if(certificateOwnership == null) {
            throw new IllegalArgumentException("Certificate ownership cannot be null");
        }
        if(this.certificateOwnershipSet.contains(certificateOwnership)){
            throw new IllegalArgumentException("Cannot have duplicated certificates");
        }
        this.certificateOwnershipSet.add(certificateOwnership);
    }

    /**
     * Method used to add association with Diving object
     *
     * @param diving instance of Diving class
     */
    public void addDiving(Diving diving) {
        if(diving == null) {
            throw new IllegalArgumentException("Cannot add null reference");
        }
        if(!diving.getDiver().equals(this)){
            throw new IllegalArgumentException("Trying to add diving for unrelated diver");
        }
        divings.add(diving);
    }

    //TODO: move to service?
    public void addCertificate(Certificate certificate) {

    }

    //TODO: move to service?
    public void addDiving(DivingSpot divingSpot) {

    }

    /**
     * Method used to calculate years of experience from first certificate issued
     *
     * @return years of experience
     */
    public int calculateYearOfExperience() {
        LocalDateTime firstCertificateIssueDate = certificateOwnershipSet.stream()
                .map(CertificateOwnership::getIssueDate)
                .min(LocalDateTime::compareTo)
                .orElseThrow(() -> new RuntimeException("Cannot get issue date from Certificate Ownership"));
        return Period.between(firstCertificateIssueDate.toLocalDate(), LocalDate.now()).getYears();
    }
}
