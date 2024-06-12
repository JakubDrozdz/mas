package pl.jakubdrozdz.divingschool.model;

import lombok.Getter;

@Getter
public class SingleDivingCourse extends DivingCourse {
    private static int priceForPerson = 1500;

    public static void setPriceForPerson(int priceForPerson) {
        SingleDivingCourse.priceForPerson = priceForPerson;
    }

    @Override
    public int calculateTotalPrice() {
        return priceForPerson;
    }
}
