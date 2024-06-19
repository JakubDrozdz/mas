package pl.jakubdrozdz.divingschool.repository.certificate;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("SELECT DISTINCT c FROM Certificate c LEFT JOIN FETCH c.courseTypes")
    List<Certificate> findAllWithCourseTypes();
}
