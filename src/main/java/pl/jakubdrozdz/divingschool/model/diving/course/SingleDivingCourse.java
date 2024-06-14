package pl.jakubdrozdz.divingschool.model.diving.course;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

@Getter
public class SingleDivingCourse extends DivingCourse {
    private static int priceForPerson = 1500;

    public SingleDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        super(detailedDescription, courseStatus, startDate, endDate);
    }

    public SingleDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        super(detailedDescription, courseStatus, startDate, endDate, additionalCost);
    }

    public static void setPriceForPerson(int priceForPerson) {
        SingleDivingCourse.priceForPerson = priceForPerson;
    }

    @Override
    public int calculateTotalPrice() {
        return priceForPerson;
    }
}
