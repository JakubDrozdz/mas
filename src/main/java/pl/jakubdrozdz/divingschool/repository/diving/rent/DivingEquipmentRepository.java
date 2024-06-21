package pl.jakubdrozdz.divingschool.repository.diving.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.rent.DivingEquipment;

/**
 * Interface used as DAO for DivingEquipment class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface DivingEquipmentRepository extends JpaRepository<DivingEquipment, Long> {
}
