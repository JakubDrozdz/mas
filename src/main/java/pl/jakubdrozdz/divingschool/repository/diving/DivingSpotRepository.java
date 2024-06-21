package pl.jakubdrozdz.divingschool.repository.diving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.DivingSpot;

/**
 * Interface used as DAO for DivingSpot class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface DivingSpotRepository extends JpaRepository<DivingSpot, Long> {
}
