/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarthree.model;

import se.kth.iv1350.seminarFourExc.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the class {@link Bike}.
 */
public class BikeTest {
    
    private String bikeSerialNo = "SVE1111111";
    private String bikeModelName = "Eva";
    private String bikeBrand = "Monark";
    private Bike bike;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        bike = new Bike(bikeBrand, bikeModelName, bikeSerialNo);
    }
    
    @AfterEach
    public void tearDown() {
        bike = null;
    }

    @Test
        public void testIfHashCodeIsSameForEachInstance() {

            long expResult = bike.getSerialNo().hashCode();
            long result = bike.hashCode(); 
            assertEquals(result, expResult, "Wrong hashcode");
        }

    @Test
    public void testBikeIsEqualToItself() {
        assertTrue(bike.equals(bike), "Bike is not equal to itself");
    }
    
    @Test
    public void testBikeIsNotEqualToOtherBike() {
        String otherSerialNo = "SVE0000000";
        Bike otherBike = new Bike(bikeBrand, bikeModelName, otherSerialNo);
        assertFalse(bike.equals(otherBike), "Bike was equal to other bike");

    }


    @Test
    public void testBikeIsNotEqualToNull() {
        assertFalse(bike.equals(null), "Bike was equal to null");
    }
    
    @Test
    public void testBikeIsNotEqualToOtherType() {
        assertFalse(bike.equals(new Object()), "Bike was equal to a object of another type");
    }

    
}
