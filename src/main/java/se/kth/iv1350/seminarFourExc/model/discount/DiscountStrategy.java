/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;

import se.kth.iv1350.seminarFourExc.model.RepairOrder;

    
/**
 * Represents a discount calculation strategy for a {@link RepairOrder}.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount amount for the given repair order.
     *
     * @param order the given repair order
     * @return the discount amount, not the final price
     */
    double calculateDiscount(RepairOrder order);
}