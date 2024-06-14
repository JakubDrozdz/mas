package pl.jakubdrozdz.divingschool.model.diving;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegistrationRequest {
    private int registrationRequestNumber;
    private LocalDateTime registrationDate;

    public RegistrationRequest(int registrationRequestNumber, LocalDateTime registrationDate) {
        setRegistrationRequestNumber(registrationRequestNumber);
        setRegistrationDate(registrationDate);
    }

    public void setRegistrationRequestNumber(int registrationRequestNumber) {
        this.registrationRequestNumber = registrationRequestNumber;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
