package pl.jakubdrozdz.divingschool.model.diving.course;

public record CourseTypeDTO(Long courseTypeId, String name, int minParticipantAge, int maxParticipantAge, String requiredCertificate) {
}
