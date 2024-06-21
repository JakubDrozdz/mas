package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;

/**
 * Interface used as DAO for CourseType class
 *
 * @author Jakub Drozdz
 */
@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Long> {
}
