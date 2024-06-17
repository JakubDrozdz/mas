package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;

import java.util.HashSet;
import java.util.Set;

@Getter

@Entity(name = "Course_Type")
public class CourseType {
    @Id
    @GeneratedValue
    private Long courseTypeId;
    private String name;
    private int minParticipantAge;
    private int maxParticipantAge;
    @ManyToOne
    @JoinColumn(name = "required_certificate")
    private Certificate requiredCertificate;
    @ManyToOne
    @JoinColumn(name="granted_certificate", nullable=false)
    private Certificate grantedCertificate;
    @OneToMany(mappedBy = "courseType")
    private Set<DivingCourse> divingCourses;

    public CourseType(String name, int minParticipantAge, int maxParticipantAge) {
        divingCourses = new HashSet<>();
        setName(name);
        setMinParticipantAge(minParticipantAge);
        setMaxParticipantAge(maxParticipantAge);
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

    public void addGrantedCertificate(Certificate certificate) {
        if(this.grantedCertificate == null) {
            this.grantedCertificate = certificate;
            certificate.addCourseType(this);
        }
    }

    public void addDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null || !divingCourse.getCourseType().equals(this)) {
            //TODO
            throw new IllegalArgumentException();
        }
        divingCourses.add(divingCourse);
    }

    public void removeDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null || !divingCourses.contains(divingCourse)){
            throw new IllegalArgumentException();
        }

        DivingCourse.removeDivingCourse(divingCourse);
        divingCourses.remove(divingCourse);
    }
}
