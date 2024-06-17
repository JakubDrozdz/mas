package pl.jakubdrozdz.divingschool.repository.diving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.DivingSpot;

@Repository
public interface DivingSpotRepository extends JpaRepository<DivingSpot, Long> {
}
