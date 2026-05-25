/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the class {@link Customer}.
 */
public class CustomerTest {
    private String customerPhoneNo = "0730311316";
    private String customerEmail = "mbarra@kth.se";
    private String customerName = "Nils";
    private Bike customerBike = new Bike("Monark", "Eva", "SVE1234567");
    private Customer customerNils;
    
   
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        customerNils = new Customer(customerPhoneNo, customerEmail, customerName, customerBike);
    }
    
    @AfterEach
    public void tearDown() {
        customerNils = null;
    }

    
    @Test
        public void testIfHashCodeIsSameForEachInstance() {

            long expResult = customerNils.getPhoneNo().hashCode();
            long result = customerNils.hashCode(); 
            assertEquals(result, expResult, "Wrong hashcode");
        }

    @Test
    public void testCustomerIsEqualToItself() {
        assertTrue(customerNils.equals(customerNils),
                "Customer is not equal to itself");
    }
    
    @Test
    public void testCustomerIsNotEqualToOtherCustomer() {
        Bike otherBike = new Bike("Monark", "Eva", "SVE0000000");
        Customer otherCustomer = new Customer("0000000000", "name@kth.se","otherCustomerName", otherBike);

        assertFalse(customerNils.equals(otherCustomer),
                "Customer was equal to other customer");
    }


    @Test
    public void testCustomerIsNotEqualToNull() {
        assertFalse(customerNils.equals(null), "Customer was equal to null");
    }
    
    @Test
    public void testCustomerIsNotEqualToOtherType() {
        assertFalse(customerNils.equals(new Object()), "Customer was equal to a object of another type");
    }

}
