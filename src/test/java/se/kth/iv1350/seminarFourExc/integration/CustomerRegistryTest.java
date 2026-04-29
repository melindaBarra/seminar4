/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

import se.kth.iv1350.seminarFourExc.integration.CustomerRegistry;
import se.kth.iv1350.seminarFourExc.integration.DatabaseFailureException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.integration.CustomerNotFoundException;

/**
 *
 * @author melin
 */
public class CustomerRegistryTest {
    private String customerPhoneNo = "0730000000";
    private String customerEmail = "nils@kth.se";
    private String customerName = "Nils";
    private Bike customerBike = new Bike("Monark", "Karin", "SVE1234567");
    private Customer customerNils = new Customer(customerPhoneNo, customerEmail, customerName, customerBike);
    private CustomerRegistry customerRegistry;

    
    public CustomerRegistryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        customerRegistry = CustomerRegistry.getInstance();
        customerRegistry.addCustomer(customerNils);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Verifies that a unknown phone number triggers a CustomerNotFoundException.
     * @throws DatabaseFailureException if the phone number triggers a database failure.  
     */
    @Test
    public void testFindCustomerWithUnknownPhoneNo() throws DatabaseFailureException {
        String unknownPhone = "0000000000";

        try {
            customerRegistry.findCustomerByPhoneNo(unknownPhone);
            fail("Expected CustomerNotFoundException to be thrown.");
        } catch (CustomerNotFoundException e) {
            assertTrue(e.getMessage().contains(unknownPhone),
                    "Exception message does not contain the phone number that caused the error.");
        }
    }
    
    /**
     * Verifies that a simulated database failure triggers a DatabaseFailureException.
     * @throws CustomerNotFoundException if {@code findCustomerByPhoneNo} did not find the 
     * searched customer in the {@code customerRegistry}.
     */
    @Test
    public void testNoDatabaseAccess() throws CustomerNotFoundException {
        String failingPhoneNo = CustomerRegistry.TRIGGER_DB_FAILURE;

        try {
            customerRegistry.findCustomerByPhoneNo(failingPhoneNo);
            fail("Expected DatabaseFailureException to be thrown.");
        } catch (DatabaseFailureException e) {
            assertTrue(e.getMessage().contains(failingPhoneNo),
                    "Exception message does not contain the phone number that caused the error.");
        }
    }
}
