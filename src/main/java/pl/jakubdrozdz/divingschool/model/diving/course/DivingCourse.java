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
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;
import pl.jakubdrozdz.divingschool.model.person.Instructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Diving_Course")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
public abstract class DivingCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long courseId;
    protected String detailedDescription;
    @Setter
    protected CourseStatus courseStatus;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Integer additionalCost;
    @OneToMany(mappedBy = "divingCourse")
    protected Map<Integer, RegistrationRequest> registrationRequests;
    @ManyToOne
    @JoinColumn(name="course_instructor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "diverId")
    @JsonIdentityReference(alwaysAsId = true)
    protected Instructor courseInstructor;
    @ManyToOne
    @JoinColumn(name="course_type", nullable=false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseTypeId")
    @JsonIdentityReference(alwaysAsId = true)
    protected CourseType courseType;


    protected DivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        registrationRequests = new HashMap<>();
        setDetailedDescription(detailedDescription);
        setCourseStatus(courseStatus);
        setStartDate(startDate);
        setEndDate(endDate);
        setCourseType(courseType);
    }

    protected DivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        this(courseType, detailedDescription, courseStatus, startDate, endDate);
        setAdditionalCost(additionalCost);
    }

    public void setDetailedDescription(String detailedDescription) {
        if(StringUtils.isBlank(detailedDescription)){
            throw new IllegalArgumentException("Detailed description cannot be empty");
        } else if (detailedDescription.length() > 500) {
            throw new IllegalArgumentException("Detailed description is longer than 500 characters");
        }
        this.detailedDescription = detailedDescription;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null || startDate.isBefore(LocalDate.of(1900,1,1))){
            throw new IllegalArgumentException("Start date cannot be null and before 1900-01-01");
        } else if (endDate != null && !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate == null || endDate.isBefore(LocalDate.of(1900,1,1))){
            throw new IllegalArgumentException("End date cannot be null and before 1900-01-01");
        } else if (startDate != null && !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.endDate = endDate;
    }

    public void setAdditionalCost(int additionalCost) {
        if(additionalCost < 0) {
            throw new IllegalArgumentException("Additional cost cannot be negative");
        }
        this.additionalCost = additionalCost;
    }

    public void setCourseType(CourseType courseType) {
        if(courseType == null && this.courseType == null){
            throw new IllegalArgumentException("Course Type cannot be null");
        } else if (courseType != null && this.courseType != null) {
            throw new IllegalArgumentException("Course Type is already set");
        } else if (courseType == null) {
            this.courseType.removeDivingCourse(this);
        } else {
            this.courseType = courseType;
            this.courseType.addDivingCourse(this);
        }

    }

    public void addRegistrationRequest(RegistrationRequest registrationRequest) {
        if(this.registrationRequests.containsKey(registrationRequest.getRegistrationRequestNumber())){
            throw new IllegalArgumentException("Cannot change registration request");
        }
        registrationRequests.put(registrationRequest.getRegistrationRequestNumber(), registrationRequest);
    }

    public void addInstructor(Instructor instructor) {
        if (this.courseInstructor == null && instructor != null) {
            this.courseInstructor = instructor;
            this.courseInstructor.assignToDivingCourse(this);
        } else if(this.courseInstructor != instructor) {
            this.courseInstructor.removeFromDivingCourse(this);
            this.courseInstructor = instructor;
            this.courseInstructor.assignToDivingCourse(this);
        }
    }

    public static void printAllCourses() {

    }

    public DivingCourse changeCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }

    public static DivingCourse addDivingCourse() {
        return null;
    }

    public abstract int calculateTotalPrice();
}
