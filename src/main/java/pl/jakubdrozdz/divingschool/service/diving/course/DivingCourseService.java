package pl.jakubdrozdz.divingschool.service.diving.course;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourseDTO;
import pl.jakubdrozdz.divingschool.repository.diving.course.CourseTypeRepository;
import pl.jakubdrozdz.divingschool.repository.diving.course.DivingCourseRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DivingCourseService {
    private DivingCourseRepository divingCourseRepository;
    private CourseTypeRepository courseTypeRepository;

    public Set<DivingCourseDTO> getDivingCoursesDtosByCourseType(Long courseTypeId) {
        List<DivingCourse> divingCoursesByCourseType = divingCourseRepository.findDivingCoursesByCourseType(courseTypeRepository.findById(courseTypeId).get());
        return prepareDivingCourseDtos(divingCoursesByCourseType);
    }

    private Set<DivingCourseDTO> prepareDivingCourseDtos(List<DivingCourse> divingCourses) {
        return divingCourses.stream()
                .map(divingCourse -> new DivingCourseDTO(divingCourse.getCourseId(), calculateDivingCourseType(divingCourse.getClass()), divingCourse.getAdditionalCost()))
                .collect(Collectors.toSet());
    }

    private String calculateDivingCourseType(Class<? extends DivingCourse> divingCourseClass) {
        DiscriminatorValue val = divingCourseClass.getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }
}
