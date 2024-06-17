package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
