package pl.jakubdrozdz.divingschool.model.diving.course;

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


    protected DivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        setDetailedDescription(detailedDescription);
        setCourseStatus(courseStatus);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    protected DivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        this(detailedDescription, courseStatus, startDate, endDate);
        setAdditionalCost(additionalCost);
    }

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
        return null;
    }

    public abstract int calculateTotalPrice();
}
