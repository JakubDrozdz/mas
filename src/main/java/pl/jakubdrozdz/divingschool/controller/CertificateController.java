package pl.jakubdrozdz.divingschool.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;
import pl.jakubdrozdz.divingschool.repository.certificate.CertificateRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class CertificateController {
    private CertificateRepository certificateRepository;

    @GetMapping("/api/v1/certificate")
    public List<Certificate> getCertificates() {
        return certificateRepository.findAll();
    }
}
