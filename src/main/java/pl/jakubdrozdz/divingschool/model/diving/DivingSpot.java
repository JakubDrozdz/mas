package pl.jakubdrozdz.divingschool.model.diving;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.enumeration.DivingSpotType;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class for DivingSpot entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Diving_Spot")
public class DivingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long divingSpotId;
    private String spotName;
    private String localization;
    private double depth;
    @Setter
    private DivingSpotType divingSpotType;
    @OneToMany(mappedBy = "divingSpot")
    private Set<Diving> divingSet;

    public DivingSpot(String spotName, String localization, double depth, DivingSpotType divingSpotType) {
        setSpotName(spotName);
        setLocalization(localization);
        setDepth(depth);
        setDivingSpotType(divingSpotType);
        divingSet = new HashSet<>();
    }

    public void setSpotName(String spotName) {
        if(StringUtils.isBlank(spotName)) {
            throw new IllegalArgumentException("Spot name cannot be blank");
        }
        this.spotName = spotName;
    }

    public void setLocalization(String localization) {
        if(StringUtils.isBlank(localization)) {
            throw new IllegalArgumentException("Spot localization cannot be blank");
        }
        this.localization = localization;
    }

    public void setDepth(double depth) {
        if(depth <= 0) {
            throw new IllegalArgumentException("Depth must be greater than zero");
        }
        this.depth = depth;
    }

    /**
     * Method used to add association with Diving class
     *
     * @param diving instance of Diving class
     */
    public void addDiving(Diving diving) {
        if(diving == null) {
            throw new IllegalArgumentException("Cannot add null reference");
        }
        if(!diving.getDivingSpot().equals(this)){
            throw new IllegalArgumentException("Trying to add diving for unrelated diving spot");
        }
        if(this.getDepth() < diving.getMaxDivingDepth()) {
            throw new IllegalArgumentException("Cannot add diving to diving stop with less depth");
        }
        divingSet.add(diving);
    }
}
