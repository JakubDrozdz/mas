package pl.jakubdrozdz.divingschool.model.diving.course.dto;

import pl.jakubdrozdz.divingschool.model.diving.course.*;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

/**
 * Record used as DTO object for DivingCourse for POST endpoint
 *
 * @param detailedDescription description of DivingCourse
 * @param courseStatus ordinal of Course Status
 * @param startDate start date of DivingCourse
 * @param endDate end date of DivingCourse
 * @param additionalCost amount of additional cost for DivingCourse
 * @param courseType ID of CourseType associated with DivingCourse
 * @param divingCourseType discriminator of DivingCourse (single/pair/group)
 *
 * @author Jakub Drozdz
 */
public record CreateDivingCourseDTO(String detailedDescription, int courseStatus, LocalDate startDate, LocalDate endDate, int additionalCost, int courseType, String divingCourseType) {
    /**
     * Method used to map CreateDivingCourseDTO to DivingCourse.
     *
     * @param courseType instance of CourseType associated with DivingCourse
     * @return instance of DivingCOurse
     */
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
