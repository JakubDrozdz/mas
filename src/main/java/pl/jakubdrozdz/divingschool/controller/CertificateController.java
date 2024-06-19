package pl.jakubdrozdz.divingschool.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubdrozdz.divingschool.model.certificate.CertificateDTO;
import pl.jakubdrozdz.divingschool.service.CertificateService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CertificateController {
    private CertificateService certificateService;

    @GetMapping("/api/v1/certificate")
    public List<CertificateDTO> getCertificates() {
        return certificateService.getCertificatesWithCourseTypes();
    }
}
