package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.CourseAttendee;

@Repository
public interface CourseAttendeeRepository extends JpaRepository<CourseAttendee, Long> {
}
