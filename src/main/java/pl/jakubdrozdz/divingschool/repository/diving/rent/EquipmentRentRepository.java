package pl.jakubdrozdz.divingschool.repository.diving.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.rent.EquipmentRent;

@Repository
public interface EquipmentRentRepository extends JpaRepository<EquipmentRent, Long> {
}
