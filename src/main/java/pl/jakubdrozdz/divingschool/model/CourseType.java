package pl.jakubdrozdz.divingschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
@Getter
public class CourseType {
    private String name;
    private int minParticipantAge;
    private int maxParticipantAge;
    private Certificate requiredCertificate;

    public CourseType(String name, int minParticipantAge, int maxParticipantAge) {
        setName(name);
        setMaxParticipantAge(minParticipantAge);
        setMinParticipantAge(minParticipantAge);
    }

    public CourseType(String name, int minParticipantAge, int maxParticipantAge, Certificate requiredCertificate) {
        this(name, minParticipantAge, maxParticipantAge);
        setRequiredCertificate(requiredCertificate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinParticipantAge(int minParticipantAge) {
        this.minParticipantAge = minParticipantAge;
    }

    public void setMaxParticipantAge(int maxParticipantAge) {
        this.maxParticipantAge = maxParticipantAge;
    }

    public void setRequiredCertificate(Certificate requiredCertificate) {
        this.requiredCertificate = requiredCertificate;
    }

    public static CourseType addCourseType(){
        return null;
    }
}
