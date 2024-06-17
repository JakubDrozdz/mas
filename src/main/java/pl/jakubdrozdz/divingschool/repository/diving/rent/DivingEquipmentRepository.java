package pl.jakubdrozdz.divingschool.repository.diving.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.rent.DivingEquipment;

@Repository
public interface DivingEquipmentRepository extends JpaRepository<DivingEquipment, Long> {
}
