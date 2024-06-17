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

    public GroupDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate) {
        super(detailedDescription, courseStatus, startDate, endDate);
    }

    public GroupDivingCourse(String detailedDescription, CourseStatus courseStatus, LocalDate startDate, LocalDate endDate, Integer additionalCost) {
        super(detailedDescription, courseStatus, startDate, endDate, additionalCost);
    }

    public static void setPriceForPerson(int priceForPerson) {
        GroupDivingCourse.priceForPerson = priceForPerson;
    }

    public static void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
        GroupDivingCourse.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    @Override
    public int calculateTotalPrice() {
        //return numberOfAttendesFetchedViaAssocaition * (priceForPerson + additionalCost);
        return 0;
    }
}
