package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

/**
 * Model class for SingleDivingCourse entity
 *
 * @author Jakub Drozdz
 */
@Getter
@Entity
@DiscriminatorValue("single")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SingleDivingCourse extends DivingCourse {
    private static int priceForPerson = 1500;

    public SingleDivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        super(courseType, detailedDescription, courseStatus, startDate, endDate);
    }

    public SingleDivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        super(courseType, detailedDescription, courseStatus, startDate, endDate, additionalCost);
    }

    public static void setPriceForPerson(int priceForPerson) {
        if(priceForPerson <= 0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        SingleDivingCourse.priceForPerson = priceForPerson;
    }

    @Override
    public int calculateTotalPrice() {
        return priceForPerson;
    }
}
