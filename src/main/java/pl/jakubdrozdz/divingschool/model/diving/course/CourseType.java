package pl.jakubdrozdz.divingschool.model.diving.course;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.certificate.Certificate;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class for CourseType entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Course_Type")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseTypeId;
    @Column(unique = true)
    private String name;
    private int minParticipantAge;
    private int maxParticipantAge;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "required_certificate")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "certificateId")
    @JsonIdentityReference(alwaysAsId = true)
    @Setter
    private Certificate requiredCertificate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="granted_certificate")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "certificateId")
    @JsonIdentityReference(alwaysAsId = true)
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

    //TODO: move to service?
    public static CourseType addCourseType(){
        return null;
    }

    /**
     * Method used to create association with Certificate class
     *
     * @param certificate instance of Certificate class
     */
    public void addGrantedCertificate(Certificate certificate) {
        if(this.grantedCertificate == null) {
            this.grantedCertificate = certificate;
            certificate.addCourseType(this);
        }
    }

    /**
     * Method used to create association with DivingCourse class
     *
     * @param divingCourse instance of DivingCourse class
     */
    public void addDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null) {
            throw new IllegalArgumentException("Cannot assign null value");
        } else if (!divingCourse.getCourseType().equals(this)) {
            throw new IllegalArgumentException("Cannot assign diving course from another Course Type");
        }
        divingCourses.add(divingCourse);
    }

    //TODO: move to service?
    public void removeDivingCourse(DivingCourse divingCourse) {
        if(divingCourse == null || !divingCourses.contains(divingCourse)){
            throw new IllegalArgumentException();
        }

        //DivingCourse.removeDivingCourse(divingCourse);
        divingCourses.remove(divingCourse);
    }
}
