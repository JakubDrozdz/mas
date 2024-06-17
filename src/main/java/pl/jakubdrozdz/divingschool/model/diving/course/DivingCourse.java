package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.RegistrationRequest;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;
import pl.jakubdrozdz.divingschool.model.person.Instructor;

import java.time.LocalDate;
import java.util.Map;

//TODO: add validation to setters
@Getter

@Entity(name = "Diving_Course")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
public abstract class DivingCourse {
    @Id
    @GeneratedValue
    private Long courseId;
    protected String detailedDescription;
    protected CourseStatus courseStatus;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Integer additionalCost;
    @OneToMany(mappedBy = "divingCourse")
    protected Map<Integer, RegistrationRequest> registrationRequests;
    @ManyToOne
    @JoinColumn(name="course_instructor", nullable=false)
    protected Instructor courseInstructor;
    @ManyToOne
    @JoinColumn(name="course_type", nullable=false)
    protected CourseType courseType;


    protected DivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        setDetailedDescription(detailedDescription);
        setCourseStatus(courseStatus);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    protected DivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        this(detailedDescription, courseStatus, startDate, endDate);
        setAdditionalCost(additionalCost);
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAdditionalCost(Integer additionalCost) {
        this.additionalCost = additionalCost;
    }

    public void addRegistrationRequest(RegistrationRequest registrationRequest) {
        if(this.registrationRequests.containsKey(registrationRequest.getRegistrationRequestNumber())){
            //TODO: add detailed exception or do not throw?
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

    public static void removeDivingCourse(DivingCourse divingCourse){
        //if(extent.contains(parkingSpot)){
        //extent.remove(equipmentRent);
        CourseType courseTypeTmp = divingCourse.getCourseType();
        divingCourse.courseType = null;
        //courseTypeTmp.removeDivingCourse(divingCourse);
        //}
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
