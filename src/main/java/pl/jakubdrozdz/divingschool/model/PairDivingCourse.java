package pl.jakubdrozdz.divingschool.model;

import lombok.Getter;

@Getter
public class PairDivingCourse extends DivingCourse {
    private static int priceForPerson = 1350;

    public static void setPriceForPerson(int priceForPerson) {
        PairDivingCourse.priceForPerson = priceForPerson;
    }

    @Override
    public int calculateTotalPrice() {
        return 2 * (priceForPerson + additionalCost);
    }
}
