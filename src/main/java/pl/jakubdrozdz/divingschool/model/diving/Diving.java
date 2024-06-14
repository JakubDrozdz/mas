package pl.jakubdrozdz.divingschool.model.diving;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class Diving {
    private LocalDateTime divingStart;
    private LocalDateTime divingEnd;
    private double maxDivingDepth;
    private Diver diver;
    private DivingSpot divingSpot;

    public Diving(LocalDateTime divingStart, LocalDateTime divingEnd, double maxDivingDepth, Diver diver, DivingSpot divingSpot) {
        setDivingStart(divingStart);
        setDivingEnd(divingEnd);
        setMaxDivingDepth(maxDivingDepth);
        setDiver(diver);
        setDivingSpot(divingSpot);
    }

    public void setDivingStart(LocalDateTime divingStart) {
        this.divingStart = divingStart;
    }

    public void setDivingEnd(LocalDateTime divingEnd) {
        this.divingEnd = divingEnd;
    }

    public void setMaxDivingDepth(double maxDivingDepth) {
        this.maxDivingDepth = maxDivingDepth;
    }

    public void setDiver(Diver diver) {
        this.diver = diver;
    }

    public void setDivingSpot(DivingSpot divingSpot) {
        this.divingSpot = divingSpot;
    }

    public int getDurationMinutes() {
        return (int) ChronoUnit.MINUTES.between(divingStart, divingEnd);
    }
}
