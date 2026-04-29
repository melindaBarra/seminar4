/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.RepairTask;

/**
 * Tests {@link HighCostDiscount}
 */
public class HighCostDiscountTest {
    
    private Bike customerBike = new Bike("Monark", "Karin", "SVE1234567");
    private Customer customerNils = new Customer("0701234567", "nils@kth.com", "Nils", customerBike);
    private String problemDescr = "The bike is worn out";
    private RepairTask task;
    private String diagnosticReport = "Chain snapped";
    private RepairOrder order;
    private HighCostDiscount discountStrategy;
    

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        order = new RepairOrder(problemDescr, customerNils); 
        discountStrategy = new HighCostDiscount();
    }

    
    @AfterEach
    public void tearDown() {
        order = null;
        discountStrategy = null;
    }
    
    
    @Test
    void testCalculateDiscountOverThreshold() {
        double costOverThreshold = discountStrategy.getThreshold() + 1;
        task = new RepairTask("Fix breaks.", costOverThreshold);
        order.updateRepairOrderAfterDiagnosis(List.of(task), diagnosticReport);

        double expResult = costOverThreshold *  discountStrategy.getDiscountRate();
        double result = discountStrategy.calculateDiscount(order);

        assertEquals(expResult, result, "The strategy did not calcualte correct discount");
    }
    
    @Test
    void testCalculateDiscountExactThreshold() {
        double cost = discountStrategy.getThreshold();
        task = new RepairTask("Fix breaks.", cost);
        order.updateRepairOrderAfterDiagnosis(List.of(task), diagnosticReport);

        double expResult = cost *  discountStrategy.getDiscountRate();
        double result = discountStrategy.calculateDiscount(order);

        assertEquals(expResult, result, "The strategy did not calcualte correct discount");
    }
        
    @Test
    void testCalculateDiscountBelowThreshold() {
        double costBelowThreshold = discountStrategy.getThreshold() - 1;
        task = new RepairTask("Fix breaks.", costBelowThreshold);
        order.updateRepairOrderAfterDiagnosis(List.of(task), diagnosticReport);

        double result = discountStrategy.calculateDiscount(order);

        assertEquals(0, result, "The strategy did not calcualte correct discount");
    }

    
}
