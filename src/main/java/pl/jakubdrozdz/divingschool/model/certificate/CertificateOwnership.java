package pl.jakubdrozdz.divingschool.model.certificate;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDateTime;

@Getter
public class CertificateOwnership {
    private String certificateNumber;
    private LocalDateTime issueDate;
    private Diver diver;
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
