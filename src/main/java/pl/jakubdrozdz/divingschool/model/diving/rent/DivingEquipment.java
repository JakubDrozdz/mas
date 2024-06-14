package pl.jakubdrozdz.divingschool.model.diving.rent;

import lombok.Getter;

@Getter
public class DivingEquipment {
    private int equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentBrand;

    public DivingEquipment(int equipmentId, String equipmentName, String equipmentType, String equipmentBrand) {
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
        return true;
    }
}
