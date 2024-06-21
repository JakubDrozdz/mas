package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.PairDivingCourse;

/**
 * Interface used as DAO for PairDivingCourse class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface PairDivingCourseRepository extends JpaRepository<PairDivingCourse, Long> {
}
