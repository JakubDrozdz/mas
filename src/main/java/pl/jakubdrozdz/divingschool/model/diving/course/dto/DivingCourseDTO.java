package pl.jakubdrozdz.divingschool.model.diving.course.dto;

/**
 * Record used as DTO object for DivingCourse for GET endpoints
 *
 * @param courseId ID of DivingCourse
 * @param detailedDescription description of DivingCourse
 * @param divingCourseType discriminator of DivingCourse
 * @param additionalCost amount of additional cost for DivingCourse
 *
 * @author Jakub Drozdz
 */
public record DivingCourseDTO(Long courseId, String detailedDescription, String divingCourseType, int additionalCost) {
}
