package pl.jakubdrozdz.divingschool.model.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateOwnership;
import pl.jakubdrozdz.divingschool.model.diving.Diving;
import pl.jakubdrozdz.divingschool.model.diving.DivingSpot;

import java.util.HashSet;
import java.util.Set;

@Getter

@Entity(name = "Diver")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("diver")
public class Diver {
    @Id
    @GeneratedValue
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
        this.medicalInformation = medicalInformation;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.person.setDiver(this);
    }

    public void addCertificate(CertificateOwnership certificateOwnership) {
        if(this.certificateOwnershipSet.contains(certificateOwnership)){
            //TODO: throw exception?
            throw new IllegalArgumentException("Cannot have duplicated certificates");
        }
        this.certificateOwnershipSet.add(certificateOwnership);
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
