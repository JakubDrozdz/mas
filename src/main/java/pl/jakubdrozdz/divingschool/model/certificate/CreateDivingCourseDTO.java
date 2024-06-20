package pl.jakubdrozdz.divingschool.model.certificate;

import pl.jakubdrozdz.divingschool.model.diving.course.*;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

public record CreateDivingCourseDTO(String detailedDescription, int courseStatus, LocalDate startDate, LocalDate endDate, int additionalCost, int courseType, String divingCourseType) {
    public DivingCourse toDivingCourse(CourseType courseType) {
        return switch (divingCourseType) {
            case "single" ->
                    new SingleDivingCourse(courseType, detailedDescription, CourseStatus.values()[courseStatus], startDate, endDate, additionalCost);
            case "pair" ->
                    new PairDivingCourse(courseType, detailedDescription, CourseStatus.values()[courseStatus], startDate, endDate, additionalCost);
            case "group" ->
                    new GroupDivingCourse(courseType, detailedDescription, CourseStatus.values()[courseStatus], startDate, endDate, additionalCost);
            default ->
                    throw new IllegalArgumentException("Diving course of type " + divingCourseType + " is not supported");
        };
    }
}
