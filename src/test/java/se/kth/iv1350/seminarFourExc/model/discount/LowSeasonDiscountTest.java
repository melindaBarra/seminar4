/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.discount;

import java.util.List;
import java.time.LocalDate;
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
 *
 * test {@link LowSeasonDiscount}
 */
public class LowSeasonDiscountTest {
    
    private Bike customerBike = new Bike("Monark", "Karin", "SVE1234567");
    private Customer customerNils = new Customer("0701234567", "nils@kth.com", "Nils", customerBike);
    private String problemDescr = "The bike is worn out";
    private RepairTask task = new RepairTask("Fix breaks.");
    private String diagnosticReport = "Chain snapped";
    private RepairOrder order;
    private LowSeasonDiscount discountStrategy;  

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        order = new RepairOrder(problemDescr, customerNils); 
        discountStrategy = new LowSeasonDiscount();
    }

    
    @AfterEach
    public void tearDown() {
        order = null;
        discountStrategy = null;
    }
    
    @Test
    void testCalculateDiscountNotLowSeason() {
        order.setDate(LocalDate.of(2026, 8, 1));
        order.updateRepairOrderAfterDiagnosis(List.of(task), diagnosticReport);

        double result = discountStrategy.calculateDiscount(order);
        
        assertEquals(0, result, "The strategy did not calcualte correct discount");
    }
    
    @Test
    void testCalculateDiscountLowSeason() {
        order.setDate(LocalDate.of(2026, 12, 1));
        order.updateRepairOrderAfterDiagnosis(List.of(task), diagnosticReport);

        double expResult = order.getBaseCost() * discountStrategy.getDiscountRate() ;
        double result = discountStrategy.calculateDiscount(order);
        assertEquals(expResult, result, "The strategy did not calcualte correct discount");
    }


    
}
