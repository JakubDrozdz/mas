package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.PairDivingCourse;

@Repository
public interface PairDivingCourseRepository extends JpaRepository<PairDivingCourse, Long> {
}
