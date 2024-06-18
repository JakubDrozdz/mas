package pl.jakubdrozdz.divingschool.service.diving.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.repository.diving.course.CourseTypeRepository;
import pl.jakubdrozdz.divingschool.repository.diving.course.DivingCourseRepository;

@Service
@AllArgsConstructor
public class CourseTypeService {
    private final CourseTypeRepository courseTypeRepository;
    private final DivingCourseRepository divingCourseRepository;

    public void addDivingCourseToCourseType(DivingCourse divingCourse, CourseType courseType) {
        courseType.addDivingCourse(divingCourse);
        courseTypeRepository.save(courseType);
        divingCourseRepository.save(divingCourse);
    }

    public void removeCourseType(CourseType courseType) {
        if(!courseTypeRepository.findAll().contains(courseType)){
            throw new IllegalArgumentException("Course type not found");
        }
        divingCourseRepository.deleteAll(courseType.getDivingCourses());
        courseTypeRepository.delete(courseType);
    }
}
