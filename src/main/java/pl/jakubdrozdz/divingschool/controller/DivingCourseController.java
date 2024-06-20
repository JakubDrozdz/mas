package pl.jakubdrozdz.divingschool.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubdrozdz.divingschool.model.diving.course.DivingCourseDTO;
import pl.jakubdrozdz.divingschool.service.diving.course.DivingCourseService;

import java.util.Set;

@RestController
@AllArgsConstructor
public class DivingCourseController {
    private DivingCourseService divingCourseService;

    @GetMapping("/api/v1/courseType/{courseTypeId}/courses")
    public Set<DivingCourseDTO> getDivingCoursesByCourseType(@PathVariable Long courseTypeId) {
        return divingCourseService.getDivingCoursesDtosByCourseType(courseTypeId);
    }

    @GetMapping("/api/v1/divingCourse/{divingCourseId}")
    public DivingCourseDTO getDivingCourseById(@PathVariable Long divingCourseId) {
        return divingCourseService.getDivingCourseDto(divingCourseId);
    }
}
