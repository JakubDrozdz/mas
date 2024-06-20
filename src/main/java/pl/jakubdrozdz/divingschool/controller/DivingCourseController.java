package pl.jakubdrozdz.divingschool.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.divingschool.model.certificate.CreateDivingCourseDTO;
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

    @PostMapping("/api/v1/divingCourse")
    public DivingCourseDTO postDivingCourse(@RequestBody CreateDivingCourseDTO createDivingCourseDTO) {
        return divingCourseService.postDivingCourse(createDivingCourseDTO);
    }
}
