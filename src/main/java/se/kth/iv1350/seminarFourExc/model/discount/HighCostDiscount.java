/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;

import se.kth.iv1350.seminarFourExc.model.RepairOrder;



/**
 * Gives a discount using {@code DISCOUNT_RATE} if the total cost of a {@link RepairOrder} exceeds the
 * {@code THRESHOLD} amount.
 */
class HighCostDiscount implements DiscountStrategy {
    private static final double THRESHOLD = 200;
    private static final double DISCOUNT_RATE = 0.15;
    
    HighCostDiscount() {}

    @Override
    public double calculateDiscount(RepairOrder repairOrder) {
        if(repairOrder.getBaseCost() >= THRESHOLD) {
            return repairOrder.getBaseCost() * DISCOUNT_RATE;
        }
        
        return 0;
    }
    
    public static double getThreshold() {
        return THRESHOLD;
    }
    
    public static double getDiscountRate() {
        return DISCOUNT_RATE;
    }
}
