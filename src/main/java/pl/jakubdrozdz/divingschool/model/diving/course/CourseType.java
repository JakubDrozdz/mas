package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Course_Type")
public class CourseType {
    @Id
    @GeneratedValue
    private Long courseTypeId;
    @Column(unique = true)
    private String name;
    private int minParticipantAge;
    private int maxParticipantAge;
    @ManyToOne
    @JoinColumn(name = "required_certificate")
    private Certificate requiredCertificate;
    @ManyToOne
    @JoinColumn(name="granted_certificate")
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
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setMinParticipantAge(int minParticipantAge) {
        if(minParticipantAge <= 0 || minParticipantAge >= 100){
            throw new IllegalArgumentException("Min participant age must be between 0 and 100");
        }
        this.minParticipantAge = minParticipantAge;
    }

    public void setMaxParticipantAge(int maxParticipantAge) {
        if(maxParticipantAge <= 0 || maxParticipantAge >= 100){
            throw new IllegalArgumentException("Max participant age must be between 0 and 100");
        } else if (maxParticipantAge <= minParticipantAge) {
            throw new IllegalArgumentException("Max participant age must be greater than min participant age");
        }
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
        if(divingCourse == null) {
            throw new IllegalArgumentException("Cannot assign null value");
        } else if (!divingCourse.getCourseType().equals(this)) {
            throw new IllegalArgumentException("Cannot assign diving course from another Course Type");
        }
        divingCourses.add(divingCourse);
    }

    public void removeDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null || !divingCourses.contains(divingCourse)){
            throw new IllegalArgumentException();
        }

        //DivingCourse.removeDivingCourse(divingCourse);
        divingCourses.remove(divingCourse);
    }
}
