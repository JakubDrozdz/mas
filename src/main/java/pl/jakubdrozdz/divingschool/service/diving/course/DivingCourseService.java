package pl.jakubdrozdz.divingschool.service.diving.course;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.certificate.CreateDivingCourseDTO;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;
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
        //TODO: handle check if isPresent
        List<DivingCourse> divingCoursesByCourseType = divingCourseRepository.findDivingCoursesByCourseType(courseTypeRepository.findById(courseTypeId).get());
        return prepareDivingCourseDtos(divingCoursesByCourseType);
    }

    private Set<DivingCourseDTO> prepareDivingCourseDtos(List<DivingCourse> divingCourses) {
        return divingCourses.stream()
                .map(divingCourse -> new DivingCourseDTO(divingCourse.getCourseId(), divingCourse.getDetailedDescription(), calculateDivingCourseType(divingCourse.getClass()), divingCourse.getAdditionalCost()))
                .collect(Collectors.toSet());
    }

    private String calculateDivingCourseType(Class<? extends DivingCourse> divingCourseClass) {
        DiscriminatorValue val = divingCourseClass.getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }

    public DivingCourseDTO getDivingCourseDto(Long divingCourseId) {
        return prepareDivingCourseDtos(
                List.of(divingCourseRepository.findById(divingCourseId).orElseThrow(() -> new IllegalArgumentException("Diving course with ID " + divingCourseId + " does not exist")))
        ).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Diving course with ID " + divingCourseId + " cannot be converted to DivingCourseDTO"));
    }

    public DivingCourseDTO postDivingCourse(CreateDivingCourseDTO createDivingCourseDTO) {
        CourseType courseType = courseTypeRepository.findById((long) createDivingCourseDTO.courseType()).orElseThrow(() -> new IllegalArgumentException("Course type not found"));
        DivingCourse divingCourse = createDivingCourseDTO.toDivingCourse(courseType);
        divingCourse = divingCourseRepository.save(divingCourse);
        courseTypeRepository.save(courseType);
        return new DivingCourseDTO(divingCourse.getCourseId(), divingCourse.getDetailedDescription(), divingCourse.getCourseType().getName(), divingCourse.getAdditionalCost());
    }
}
