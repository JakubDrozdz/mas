package pl.jakubdrozdz.divingschool.model.diving;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.person.Diver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Diving")
public class Diving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        setDiver(diver);
        setDivingSpot(divingSpot);
        setMaxDivingDepth(maxDivingDepth);
    }

    public void setDivingStart(LocalDateTime divingStart) {
        if(divingStart == null || divingStart.isBefore(LocalDateTime.of(1900,1,1,0,0))){
            throw new IllegalArgumentException("Start date cannot be null and before 1900-01-01");
        } else if (divingEnd != null && !divingStart.isBefore(divingEnd)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        this.divingStart = divingStart;
    }

    public void setDivingEnd(LocalDateTime divingEnd) {
        if(divingEnd == null || divingEnd.isBefore(LocalDateTime.of(1900,1,1,0,0))){
            throw new IllegalArgumentException("Start date cannot be null and before 1900-01-01");
        } else if (divingStart != null && !divingEnd.isAfter(divingStart)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.divingEnd = divingEnd;
    }

    public void setMaxDivingDepth(double maxDivingDepth) {
        if(maxDivingDepth <= 0){
            throw new IllegalArgumentException("Max diving depth must be greater than zero");
        } else if (maxDivingDepth > divingSpot.getDepth()) {
            throw new IllegalArgumentException("Max diving depth must be less than diving spot depth");
        }
        this.maxDivingDepth = maxDivingDepth;
    }

    //TODO: can we change associations?
    public void setDiver(Diver diver) {
        if(diver == null){
            throw new IllegalArgumentException("Diver cannot be null");
        }
        this.diver = diver;
        diver.addDiving(this);
    }

    //TODO: can we change associations?
    public void setDivingSpot(DivingSpot divingSpot) {
        if(divingSpot == null){
            throw new IllegalArgumentException("Diving spot cannot be null");
        }
        this.divingSpot = divingSpot;
        divingSpot.addDiving(this);
    }

    public int getDurationMinutes() {
        return (int) ChronoUnit.MINUTES.between(divingStart, divingEnd);
    }
}
