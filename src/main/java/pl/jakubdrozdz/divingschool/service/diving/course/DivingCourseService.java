package pl.jakubdrozdz.divingschool.service.diving.course;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.divingschool.model.diving.course.dto.CreateDivingCourseDTO;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.model.diving.course.dto.DivingCourseDTO;
import pl.jakubdrozdz.divingschool.repository.diving.course.CourseTypeRepository;
import pl.jakubdrozdz.divingschool.repository.diving.course.DivingCourseRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class providing methods available for DivingCourse objects
 *
 * @author Jakub Drozdz
 */
@Service
@AllArgsConstructor
public class DivingCourseService {
    private DivingCourseRepository divingCourseRepository;
    private CourseTypeRepository courseTypeRepository;

    /**
     * Method responsible for fetching diving courses by provided course type and mapping to DivingCourseDTO
     *
     * @param courseTypeId id of CourseType object
     * @return Set of DicingCourseDTOs
     */
    public Set<DivingCourseDTO> getDivingCoursesDtosByCourseType(Long courseTypeId) {
        //TODO: handle check if isPresent
        List<DivingCourse> divingCoursesByCourseType = divingCourseRepository.findDivingCoursesByCourseType(courseTypeRepository.findById(courseTypeId).get());
        return prepareDivingCourseDtos(divingCoursesByCourseType);
    }

    /**
     * Method used to map List of DivingCourse to Set of DivingCourseDTOs
     *
     * @param divingCourses List of DivingCourse objects
     * @return Set of DivingCourseDTOs
     */
    private Set<DivingCourseDTO> prepareDivingCourseDtos(List<DivingCourse> divingCourses) {
        return divingCourses.stream()
                .map(divingCourse -> new DivingCourseDTO(divingCourse.getCourseId(), divingCourse.getDetailedDescription(), calculateDivingCourseType(divingCourse.getClass()), divingCourse.getAdditionalCost()))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Method used to determine diving course type (single, pair or group)
     *
     * @param divingCourseClass instance of class of DivingCourse object
     * @return discriminator value of concrete DivingCourse class
     */
    private String calculateDivingCourseType(Class<? extends DivingCourse> divingCourseClass) {
        DiscriminatorValue val = divingCourseClass.getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }

    /**
     * Method used to map DivingCourse of specified ID to DivingCourseDTO
     *
     * @param divingCourseId id of DivingCourse object
     * @return instance of DivingCourseDTO
     */
    public DivingCourseDTO getDivingCourseDto(Long divingCourseId) {
        return prepareDivingCourseDtos(
                List.of(divingCourseRepository.findById(divingCourseId).orElseThrow(() -> new IllegalArgumentException("Diving course with ID " + divingCourseId + " does not exist")))
        ).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Diving course with ID " + divingCourseId + " cannot be converted to DivingCourseDTO"));
    }

    /**
     * Method used to save DivingCourse from passed CreateDivingCourseDTO
     *
     * @param createDivingCourseDTO instance of CreateDivingCourseDTO to be persisted
     * @return DivingCourseDTO from persisted DIvingCourse
     */
    public DivingCourseDTO saveDivingCourse(CreateDivingCourseDTO createDivingCourseDTO) {
        CourseType courseType = courseTypeRepository.findById((long) createDivingCourseDTO.courseType()).orElseThrow(() -> new IllegalArgumentException("Course type not found"));
        DivingCourse divingCourse = createDivingCourseDTO.toDivingCourse(courseType);
        divingCourse = divingCourseRepository.save(divingCourse);
        courseTypeRepository.save(courseType);
        return new DivingCourseDTO(divingCourse.getCourseId(), divingCourse.getDetailedDescription(), divingCourse.getCourseType().getName(), divingCourse.getAdditionalCost());
    }
}
