package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;

import java.util.List;

/**
 * Interface used as DAO for DivingCourse class
 *
 * @author Jakub Drozdz
 */
@Repository
public interface DivingCourseRepository extends JpaRepository<DivingCourse, Long> {
    List<DivingCourse> findDivingCoursesByCourseType(CourseType courseType);
}
