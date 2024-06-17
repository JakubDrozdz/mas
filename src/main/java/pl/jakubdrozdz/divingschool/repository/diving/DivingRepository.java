package pl.jakubdrozdz.divingschool.repository.diving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.Diving;

@Repository
public interface DivingRepository extends JpaRepository<Diving, Long> {
}
