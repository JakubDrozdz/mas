package pl.jakubdrozdz.divingschool.repository.diving.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.rent.EquipmentRent;

/**
 * Interface used as DAO for EquipmentRent class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface EquipmentRentRepository extends JpaRepository<EquipmentRent, Long> {
}
