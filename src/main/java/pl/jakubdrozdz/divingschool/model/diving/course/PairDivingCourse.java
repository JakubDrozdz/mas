package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

@Getter

@Entity
@DiscriminatorValue("pair")
public class PairDivingCourse extends DivingCourse {
    private static int priceForPerson = 1350;

    public PairDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        super(detailedDescription, courseStatus, startDate, endDate);
    }

    public PairDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        super(detailedDescription, courseStatus, startDate, endDate, additionalCost);
    }

    public static void setPriceForPerson(int priceForPerson) {
        PairDivingCourse.priceForPerson = priceForPerson;
    }

    @Override
    public int calculateTotalPrice() {
        return 2 * (priceForPerson + additionalCost);
    }
}
