package pl.jakubdrozdz.divingschool.repository.diving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;

/**
 * Interface used as DAO for RegistrationRequest class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
}
