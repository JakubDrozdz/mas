package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.CourseAttendee;

/**
 * Interface used as DAO for CourseAttendee class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface CourseAttendeeRepository extends JpaRepository<CourseAttendee, Long> {
}
