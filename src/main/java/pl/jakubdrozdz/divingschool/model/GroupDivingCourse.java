package pl.jakubdrozdz.divingschool.model;

import lombok.Getter;

@Getter
public class GroupDivingCourse extends DivingCourse {
    private static int priceForPerson = 1150;
    private static int maxNumberOfParticipants = 8;

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
