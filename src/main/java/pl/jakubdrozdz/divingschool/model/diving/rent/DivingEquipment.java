package pl.jakubdrozdz.divingschool.model.diving.rent;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Model class for DivingEquipment entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Equipment")
public class DivingEquipment {
    @Id
    private Long equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentBrand;
    @ManyToMany(mappedBy = "equipmentSet")
    private Set<EquipmentRent> rents;

    public DivingEquipment(int equipmentId, String equipmentName, String equipmentType, String equipmentBrand) {
        rents = new HashSet<>();
        setEquipmentId(equipmentId);
        setEquipmentName(equipmentName);
        setEquipmentType(equipmentType);
        setEquipmentBrand(equipmentBrand);
    }

    public void setEquipmentId(int equipmentId) {
        if(equipmentId <= 0) {
            throw new IllegalArgumentException("Equipment Id must be greater than 0");
        }
        this.equipmentId = (long) equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        if(StringUtils.isBlank(equipmentName)) {
            throw new IllegalArgumentException("Equipment name cannot be empty");
        }
        this.equipmentName = equipmentName;
    }

    public void setEquipmentType(String equipmentType) {
        if(StringUtils.isBlank(equipmentType)) {
            throw new IllegalArgumentException("Equipment type cannot be empty");
        }
        this.equipmentType = equipmentType;
    }

    public void setEquipmentBrand(String equipmentBrand) {
        if(StringUtils.isBlank(equipmentBrand)) {
            throw new IllegalArgumentException("Equipment brand cannot be empty");
        }
        this.equipmentBrand = equipmentBrand;
    }

    /**
     * Method used to check if equipment is available
     *
     * @return true if equipment is available
     */
    public boolean isAvailable() {
        //TODO: check if logic is valid
        LocalDateTime now = LocalDateTime.now();
        return rents.stream()
                .filter(rent -> rent.getRentDate().isBefore(now) &&
                        rent.getRentReturnDate() != null &&
                        rent.getRentReturnDate().isAfter(now)).collect(Collectors.toUnmodifiableSet()).isEmpty();
    }

    /**
     * Method used to add association to EquipmentRent class
     *
     * @param equipmentRent instance of EquipmentRent class
     */
    public void addEquipmentRent(EquipmentRent equipmentRent) {
        if(!this.rents.contains(equipmentRent)){
            this.rents.add(equipmentRent);
            equipmentRent.addEquipment(this);
        }
    }
}
