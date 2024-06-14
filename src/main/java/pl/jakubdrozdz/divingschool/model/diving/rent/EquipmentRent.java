package pl.jakubdrozdz.divingschool.model.diving.rent;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class EquipmentRent {
    private int rentId;
    private LocalDateTime rentDate;
    private LocalDateTime rentReturnDate;

    public EquipmentRent(int rentId, LocalDateTime rentDate) {
        setRentId(rentId);
        setRentDate(rentDate);
    }

    public EquipmentRent(int rentId, LocalDateTime rentDate, LocalDateTime rentReturnDate) {
        this(rentId, rentDate);
        setRentReturnDate(rentReturnDate);
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public void setRentReturnDate(LocalDateTime rentReturnDate) {
        this.rentReturnDate = rentReturnDate;
    }

    public static EquipmentRent createEquipmentRent(){
        return null;
    }
}
