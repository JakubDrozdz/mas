package pl.jakubdrozdz.divingschool.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateDTO;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseTypeDTO;
import pl.jakubdrozdz.divingschool.repository.certificate.CertificateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CertificateService {
    private CertificateRepository certificateRepository;

    public List<CertificateDTO> getCertificatesWithCourseTypes() {
        List<Certificate> allCertificatesWithCourseTypes = certificateRepository.findAllWithCourseTypes();
        List<CertificateDTO> certificateDTOs = new ArrayList<>();
        for (Certificate certificate : allCertificatesWithCourseTypes) {
            certificateDTOs.add(new CertificateDTO(certificate.getCertificateId(), certificate.getCertificateName(), getCourseTypesDtos(certificate)));
        }
        return certificateDTOs;
    }

    private Set<CourseTypeDTO> getCourseTypesDtos(Certificate certificate) {
        return certificate.getCourseTypes()
                .stream()
                .map(courseType ->
                        new CourseTypeDTO(courseType.getCourseTypeId(),
                                courseType.getName(),
                                courseType.getMinParticipantAge(),
                                courseType.getMaxParticipantAge(),
                                courseType.getRequiredCertificate() != null ? courseType.getRequiredCertificate().getCertificateName() : null
                        )
                )
                .collect(Collectors.toSet());
    }
}
