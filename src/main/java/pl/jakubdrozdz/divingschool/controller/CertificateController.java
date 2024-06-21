package pl.jakubdrozdz.divingschool.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateDTO;
import pl.jakubdrozdz.divingschool.service.CertificateService;

import java.util.List;

/**
 * Class providing API endpoints for operations on Certificate class
 *
 * @author Jakub Drozdz
 */
@RestController
@AllArgsConstructor
public class CertificateController {
    private CertificateService certificateService;

    /**
     * Method used to fetch all certificates and return List of mapped CertificateDTO
     *
     * @return List of mapped CertificateDTO
     */
    @GetMapping("/api/v1/certificate")
    public List<CertificateDTO> getCertificates() {
        return certificateService.getCertificatesWithCourseTypes();
    }
}
