package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.SingleDivingCourse;

/**
 * Interface used as DAO for SingleDivingCourse class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface SingleDivingCourseRepository extends JpaRepository<SingleDivingCourse, Long> {
}
