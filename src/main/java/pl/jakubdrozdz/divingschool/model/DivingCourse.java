package pl.jakubdrozdz.divingschool.model;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

//TODO: add validation to setters
@Getter
public abstract class DivingCourse {
    protected String detailedDescription;
    protected CourseStatus courseStatus;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Integer additionalCost;
    //Registration Map<Integer, Registration

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAdditionalCost(Integer additionalCost) {
        this.additionalCost = additionalCost;
    }

    public static void printAllCourses() {

    }

    public DivingCourse changeCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }

    public static DivingCourse addDivingCourse() {
        return new SingleDivingCourse();
    }

    public abstract int calculateTotalPrice();
}
