package pl.jakubdrozdz.divingschool.model.diving;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.enumeration.DivingSpotType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class DivingSpot {
    private String spotName;
    private String localization;
    private double depth;
    private DivingSpotType divingSpotType;
    private Set<Diving> divingSet;

    public DivingSpot(String spotName, String localization, double depth, DivingSpotType divingSpotType) {
        setSpotName(spotName);
        setLocalization(localization);
        setDepth(depth);
        setDivingSpotType(divingSpotType);
        divingSet = new HashSet<>();
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setDivingSpotType(DivingSpotType divingSpotType) {
        this.divingSpotType = divingSpotType;
    }

    public void addDiving(Diving diving) {

    }
}
