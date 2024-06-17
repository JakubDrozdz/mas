package pl.jakubdrozdz.divingschool.repository.certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateOwnership;

@Repository
public interface CertificateOwnershipRepository extends JpaRepository<CertificateOwnership, Long> {
}
