package pl.jakubdrozdz.divingschool.model.certificate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "Certificate_Ownership")
public class CertificateOwnership {
    @Id
    @GeneratedValue
    private Long certificateOwnershipId;
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
        this.certificateNumber = certificateNumber;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    private void setDiver(Diver diver) {
        this.diver = diver;
        this.diver.addCertificate(this);
    }

    private void setCertificate(Certificate certificate) {
        this.certificate = certificate;
        this.certificate.addCertificate(this);
    }
}
