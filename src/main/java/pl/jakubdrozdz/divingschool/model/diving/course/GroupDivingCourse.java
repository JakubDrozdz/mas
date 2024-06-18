package pl.jakubdrozdz.divingschool.model.diving.course;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.enumeration.CourseStatus;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("group")
public class GroupDivingCourse extends DivingCourse {
    private static int priceForPerson = 1150;
    private static int maxNumberOfParticipants = 8;

    public GroupDivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        super(courseType, detailedDescription, courseStatus, startDate, endDate);
    }

    public GroupDivingCourse(CourseType courseType, String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        super(courseType, detailedDescription, courseStatus, startDate, endDate, additionalCost);
    }

    public static void setPriceForPerson(int priceForPerson) {
        if(priceForPerson <= 0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        GroupDivingCourse.priceForPerson = priceForPerson;
    }

    public static void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
        if(maxNumberOfParticipants <= 2){
            throw new IllegalArgumentException("Max number of participants must be greater than 2");
        }
        GroupDivingCourse.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    @Override
    public int calculateTotalPrice() {
        //return numberOfAttendesFetchedViaAssocaition * (priceForPerson + additionalCost);
        return 0;
    }
}
