package pl.jakubdrozdz.divingschool.model.certificate;

import pl.jakubdrozdz.divingschool.model.diving.course.CourseTypeDTO;

import java.util.Set;

public record CertificateDTO(Long certificateId, String certificateName, Set<CourseTypeDTO> courseTypes) {
}
