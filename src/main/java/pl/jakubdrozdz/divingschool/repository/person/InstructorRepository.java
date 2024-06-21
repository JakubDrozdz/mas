package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.Instructor;

/**
 * Interface used as DAO for Instructor class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
