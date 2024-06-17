package pl.jakubdrozdz.divingschool.model.diving;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter

@Entity(name = "Diving")
public class Diving {
    @Id
    @GeneratedValue
    private Long equipmentRentId;
    private LocalDateTime divingStart;
    private LocalDateTime divingEnd;
    private double maxDivingDepth;
    @ManyToOne
    @JoinColumn(name = "divings_diver", nullable = false)
    private Diver diver;
    @ManyToOne
    @JoinColumn(name = "divings_spot", nullable = false)
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
