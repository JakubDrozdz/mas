package pl.jakubdrozdz.divingschool.model.certificate.dto;

import pl.jakubdrozdz.divingschool.model.diving.course.dto.CourseTypeDTO;

import java.util.Set;

/**
 * Record used as DTO object for Certificate class
 *
 * @param certificateId ID of Certificate object
 * @param certificateName name of Certificate object
 * @param courseTypes Set of CourseTypeDTOs
 *
 * @author Jakub Drozdz
 */
public record CertificateDTO(Long certificateId, String certificateName, Set<CourseTypeDTO> courseTypes) {
}
