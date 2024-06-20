package pl.jakubdrozdz.divingschool.model.certificate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Certificate_Ownership")
public class CertificateOwnership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateOwnershipId;
    @Column(unique = true)
    private String certificateNumber;
    private LocalDateTime issueDate;
    @ManyToOne
    @JoinColumn(name="diver", nullable=false)
    private Diver diver;
    @ManyToOne
    @JoinColumn(name="certificate", nullable=false)
    private Certificate certificate;

    public CertificateOwnership(String certificateNumber, LocalDateTime issueDate, Diver diver, Certificate certificate) {
        setCertificateNumber(certificateNumber);
        setIssueDate(issueDate);
        setDiver(diver);
        setCertificate(certificate);
    }

    public void setCertificateNumber(String certificateNumber) {
        if(StringUtils.isBlank(certificateNumber)){
            throw new IllegalArgumentException("Certificate number cannot be blank");
        }
        this.certificateNumber = certificateNumber;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        if(issueDate == null){
            throw new IllegalArgumentException("Issue date cannot be null");
        } else if(issueDate.isBefore(LocalDate.of(1900,1,1).atTime(0,0))){
            throw new IllegalArgumentException("Issue date cannot be before 1900-01-01");
        }
        this.issueDate = issueDate;
    }

    private void setDiver(Diver diver) {
        if(diver == null){
            throw new IllegalArgumentException("Diver cannot be null");
        }
        this.diver = diver;
        this.diver.addCertificateOwnership(this);
    }

    private void setCertificate(Certificate certificate) {
        if(certificate == null){
            throw new IllegalArgumentException("Certificate cannot be null");
        }
        this.certificate = certificate;
        this.certificate.addCertificateOwnership(this);
    }
}
