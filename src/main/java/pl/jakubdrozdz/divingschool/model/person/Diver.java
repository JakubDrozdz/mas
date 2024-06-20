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

import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(mappedBy = "diver")
    private Person person;
    @OneToMany(mappedBy = "diver")
    private Set<Diving> divings;
    @OneToMany(mappedBy = "diver")
    private Set<CertificateOwnership> certificateOwnershipSet;

    public Diver(Person person, String medicalInformation) {
        setPerson(person);
        setMedicalInformation(medicalInformation);
        certificateOwnershipSet = new HashSet<>();
        divings = new HashSet<>();
    }

    public void setMedicalInformation(String medicalInformation) {
        if(StringUtils.isBlank(medicalInformation) || medicalInformation.trim().length() >= 500){
            throw new IllegalArgumentException("Medical information should be max 500 characters long and not be blank");
        }
        this.medicalInformation = medicalInformation;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.person.setDiver(this);

    }

    public void addCertificateOwnership(CertificateOwnership certificateOwnership) {
        if(certificateOwnership == null) {
            throw new IllegalArgumentException("Certificate ownership cannot be null");
        }
        if(this.certificateOwnershipSet.contains(certificateOwnership)){
            throw new IllegalArgumentException("Cannot have duplicated certificates");
        }
        this.certificateOwnershipSet.add(certificateOwnership);
    }

    public void addDiving(Diving diving) {
        if(diving == null) {
            throw new IllegalArgumentException("Cannot add null reference");
        }
        if(!diving.getDiver().equals(this)){
            throw new IllegalArgumentException("Trying to add diving for unrelated diver");
        }
        divings.add(diving);
    }

    public void addCertificate(Certificate certificate) {

    }

    public void addDiving(DivingSpot divingSpot) {

    }

    public int calculateYearOfExperience() {
        //TODO: calculate based on date of first certificate
        return 0;
    }
}
