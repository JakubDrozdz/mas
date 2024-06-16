package pl.jakubdrozdz.divingschool.model.diving.rent;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class DivingEquipment {
    private int equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentBrand;
    private Set<EquipmentRent> rents;

    public DivingEquipment(int equipmentId, String equipmentName, String equipmentType, String equipmentBrand) {
        rents = new HashSet<>();
        setEquipmentId(equipmentId);
        setEquipmentName(equipmentName);
        setEquipmentType(equipmentType);
        setEquipmentBrand(equipmentBrand);
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setEquipmentBrand(String equipmentBrand) {
        this.equipmentBrand = equipmentBrand;
    }

    public boolean isAvailable() {
        //TODO: think about some logic
        LocalDateTime now = LocalDateTime.now();
        return rents.stream()
                .filter(rent -> rent.getRentDate().isBefore(now) &&
                        rent.getRentReturnDate() != null &&
                        rent.getRentReturnDate().isAfter(now)).collect(Collectors.toSet()).isEmpty();
    }

    public void addEquipmentRent(EquipmentRent equipmentRent) {
        if(!this.rents.contains(equipmentRent)){
            this.rents.add(equipmentRent);
            equipmentRent.addEquipment(this);
        }
    }
}
