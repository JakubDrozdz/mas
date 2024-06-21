package pl.jakubdrozdz.divingschool.service.diving.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.repository.diving.course.CourseTypeRepository;
import pl.jakubdrozdz.divingschool.repository.diving.course.DivingCourseRepository;

/**
 * Class providing method available for DivingCourse objects
 *
 * @author Jakub Drozdz
 */
@Service
@AllArgsConstructor
public class CourseTypeService {
    private final CourseTypeRepository courseTypeRepository;
    private final DivingCourseRepository divingCourseRepository;

    //TODO: is needed?
    /**
     * Method used to create association between Diving Course and Course Type
     *
     * @param divingCourse instance of DivingCourse
     * @param courseType instance of CourseType
     */
    public void addDivingCourseToCourseType(DivingCourse divingCourse, CourseType courseType) {
        courseType.addDivingCourse(divingCourse);
        courseTypeRepository.save(courseType);
        divingCourseRepository.save(divingCourse);
    }
}
