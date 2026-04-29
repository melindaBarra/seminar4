/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;


/**
 * Factory responsible for selecting an {@link DiscountStrategy}
 * based on a {@link RepairOrder}.
 */
public class DiscountFactory {
    private static final DiscountFactory INSTANCE = new DiscountFactory();
    
    /**
     * In order to implement a Singleton pattern this constructor is set to private,
     * ensuring that only one instance of this class exists.
     */
    private DiscountFactory() {}
    
    
    /**
     * Returns the single instance of {@code RepairOrderRegistry}.
     *
     * @return the only existing {@code DiscountFactory} instance.
     */
    public static DiscountFactory getInstance() {
        return INSTANCE;
    }
    /**
     * Creates and returns a discount strategy for the given {@code RepairOrder}.
     *
     * @param repairOrder the given repair order
     * @return the selected discount strategy
     */
    public DiscountStrategy getDiscountStrategy(RepairOrder repairOrder) {
        double cost = repairOrder.getBaseCost();

        if (cost >= HighCostDiscount.getThreshold()) {
            return new HighCostDiscount();
        }
        
        return new LowSeasonDiscount();
    }
}