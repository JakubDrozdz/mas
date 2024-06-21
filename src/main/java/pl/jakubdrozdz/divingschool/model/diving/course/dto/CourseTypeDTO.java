package pl.jakubdrozdz.divingschool.model.diving.course.dto;

/**
 * Record used as DTO object for CourseType class
 *
 * @param courseTypeId ID of CourseType
 * @param name name of CourseType
 * @param minParticipantAge minimum participant age of CourseType
 * @param maxParticipantAge maximum participant age of CourseType
 * @param requiredCertificate name of required certificate for CourseType
 *
 * @author Jakub Drozdz
 */
public record CourseTypeDTO(Long courseTypeId, String name, int minParticipantAge, int maxParticipantAge, String requiredCertificate) {
}
