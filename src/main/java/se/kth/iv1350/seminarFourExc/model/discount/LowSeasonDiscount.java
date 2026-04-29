/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;
import java.time.Month;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;

/**
 * Gives a discount using {@code DISCOUNT_RATE} if the repair order is placed
 * during low‑season months: November to February.
 */
class LowSeasonDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.20;
    
    LowSeasonDiscount() {}
    
    /**
     * Returns a discount using {@code DISCOUNT_RATE} if the repair order is placed
     * during low‑season months. Else no discount is given. 
     */
    @Override
    public double calculateDiscount(RepairOrder repairOrder) {
        Month month = repairOrder.getDate().getMonth();

        boolean isLowSeason =
                month == Month.NOVEMBER ||
                month == Month.DECEMBER ||
                month == Month.JANUARY ||
                month == Month.FEBRUARY;

        if(isLowSeason) {
            return repairOrder.getBaseCost() * DISCOUNT_RATE;
        }

        return 0;
    }
    
    public static double getDiscountRate() {
        return DISCOUNT_RATE;
    }
}
