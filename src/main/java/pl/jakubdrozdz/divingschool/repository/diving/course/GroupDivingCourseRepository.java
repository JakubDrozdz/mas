package pl.jakubdrozdz.divingschool.repository.diving.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.diving.course.GroupDivingCourse;

@Repository
public interface GroupDivingCourseRepository extends JpaRepository<GroupDivingCourse, Long> {
}
