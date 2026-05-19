/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.Customer;

/**
 * Test {@link RepairOrderView}
 */
public class RepairOrderViewTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private Bike bike = new Bike("Monark", "Karin", "SVE123");
    private Customer customer = new Customer("0731234567", "mail@mail.se", "Nils", bike);
    private String problemDescr = "Flat tire";
    private RepairOrder repairOrder = new RepairOrder(problemDescr, customer);

    
    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(originalSysOut);
        outContent = null;
    }
    
    @Test
    public void testRepairOrderUpdatePrintsHeading() {
        String result = invokeUpdateAndReadOutput(repairOrder);
        String expResult = "--- Updated Repair Order ---";
        assertTrue(result.contains(expResult), "Missing updated repair order heading.");
    }
    
    @Test
    public void testRepairOrderUpdatePrintsOrderData() {
 
        String result = invokeUpdateAndReadOutput(repairOrder);
        String expResult = StringRepresentationUtil.repairOrderToString(repairOrder);

        assertTrue(result.contains(expResult), "Updated repair order data was printed incorrectly.");
    }
    
    /**
     * Calls {@code repairOrderUpdate} on the given order and returns everything
     * printed to {@code System.out} during the call.
    */
   private String invokeUpdateAndReadOutput(RepairOrder repairOrder) {
       RepairOrderView view = new RepairOrderView();
       view.repairOrderUpdate(repairOrder);
       return outContent.toString();
   }


    
}



