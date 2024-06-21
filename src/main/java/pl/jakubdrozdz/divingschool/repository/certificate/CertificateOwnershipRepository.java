package pl.jakubdrozdz.divingschool.repository.certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateOwnership;

/**
 * Interface used as DAO for CertificateOwnership class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface CertificateOwnershipRepository extends JpaRepository<CertificateOwnership, Long> {
}
