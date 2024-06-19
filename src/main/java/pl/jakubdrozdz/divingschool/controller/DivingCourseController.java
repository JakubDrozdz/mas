package pl.jakubdrozdz.divingschool.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourse;
import pl.jakubdrozdz.divingschool.repository.diving.course.CourseTypeRepository;
import pl.jakubdrozdz.divingschool.repository.diving.course.DivingCourseRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class DivingCourseController {
    private DivingCourseRepository divingCourseRepository;
    private CourseTypeRepository courseTypeRepository;

    @GetMapping("/api/v1/courseType/{courseTypeId}/courses")
    public List<DivingCourse> getDivingCoursesByCourseType(@PathVariable Long courseTypeId) {
        return divingCourseRepository.findDivingCoursesByCourseType(courseTypeRepository.findById(courseTypeId).get());
    }
}
