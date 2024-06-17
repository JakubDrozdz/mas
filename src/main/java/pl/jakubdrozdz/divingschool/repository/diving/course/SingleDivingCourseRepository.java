package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.SingleDivingCourse;

@Repository
public interface SingleDivingCourseRepository extends JpaRepository<SingleDivingCourse, Long> {
}
