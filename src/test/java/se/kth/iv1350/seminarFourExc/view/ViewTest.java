
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.model.dto.CustomerDto;
import se.kth.iv1350.seminarFourExc.integration.CustomerRegistry;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarFourExc.controller.Controller;
import se.kth.iv1350.seminarFourExc.integration.ExceptionFileLogger;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Test for {@link View}
 */
public class ViewTest {
    private String brand = "Monark";
    private String modelName = "Karin";
    private String serialNo = "SVE123";
    private String knownPhoneNo = "0731234567";
    private String name = "Nils";
    private String email = "nils@kth.se";
    private Bike bike = new Bike(brand, modelName, serialNo);
    private Customer customer = new Customer(knownPhoneNo, email, name, bike);
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

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
    public void testRunFakeExecutionSucessful() {
        View view = createViewWithKnownCustomer();
        
        view.runFakeExecution(knownPhoneNo);

        String result = outContent.toString();
        
        assertTrue(result.contains(name), "Missing customer's Name.");
        assertTrue(result.contains(email), "Missing customer's email.");
        assertTrue(result.contains(knownPhoneNo), "Missing customer's phone number.");
        assertTrue(result.contains(brand), "Missing the brand of the customer's bike.");
        assertTrue(result.contains(serialNo), "Missing the serial number of the customer's bike.");
        assertTrue(result.contains(modelName), "Missing the model name of the customer's bike.");

    }
    
    @Test
    public void testRunFakeExecutionPrintsAllHeadings() {
        View view = createViewWithKnownCustomer();
        
        view.runFakeExecution(knownPhoneNo);

        String result = outContent.toString();

        assertTrue(result.contains("---Bike repair workshop---"),
                "Missing workshop heading.");

        assertTrue(result.contains("Searching for customer."),
                "Missing customer search heading.");

        assertTrue(result.contains("--- Found customer ---"),
                "Missing 'found customer' heading.");

        assertTrue(result.contains("Registers the customer's problem description"),
                "Missing problem registration heading.");

        assertTrue(result.contains("Updates the repair order after diagnosis."),
                "Missing diagnosis update heading.");

        assertTrue(result.contains("Customer accepts the repair tasks"),
                "Missing acceptance heading.");

        assertTrue(result.contains("---Customer leaves the workshop---"),
                "Missing workshop exit heading.");
}

        
        
    @Test
    public void testRunFakeExecutionCustomerNotFound() {
        String unknownPhoneNo = "0000000000";
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();

        Controller contr = new Controller(customerRegistry, RepairOrderRegistry.getInstance(), new ExceptionFileLogger());

        View view = new View(contr);
        view.runFakeExecution(unknownPhoneNo);

        String result = outContent.toString();

        String expResult = "ERROR: No customer with the searched phone number '" +unknownPhoneNo +"' exists.";
        assertTrue(result.contains(expResult), "Error message does not contain the expected error message.");
    }
    
    /**
     * {@code phoneNo} is the phone number used to trigger a {@link DatabaseFailureException},
     * which in turn triggers an {@link OperationFailedException}.
     */
    @Test
    public void testRunFakeExecutionOperationFail() {
        String phoneNo = "9999999999";
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();

        Controller contr = new Controller(customerRegistry, RepairOrderRegistry.getInstance(), new ExceptionFileLogger());

        View view = new View(contr);
        view.runFakeExecution(phoneNo);

        String result = outContent.toString();
        String expResult = "ERROR: Something went wrong. Please try again later.";
        assertTrue(result.contains(expResult), "Error message does not contain the expected error message.");
    }
    

    @Test
    public void testRunFakeExecutionUnexpectedFailure() {
        String phoneNo = "0000000000";
        
        View view = new View(createAnonymousContr());
        view.runFakeExecution(phoneNo);

        String result = outContent.toString();
        String expResult = "ERROR: Unexpected failure.";
        assertTrue(result.contains(expResult), "Error message does not contain the expected cause.");
    }
    
    private View createViewWithKnownCustomer() {
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();

        customerRegistry.addCustomer(customer);

        Controller contr = new Controller(customerRegistry,
                RepairOrderRegistry.getInstance(),
                new ExceptionFileLogger());

        return new View(contr);
    }

    private Controller createAnonymousContr() {
        Controller fakeController = new Controller( CustomerRegistry.getInstance(), RepairOrderRegistry.getInstance(),
        new ExceptionFileLogger()) {
            @Override
            public CustomerDto searchCustomer(String phoneNo) {
                throw new RuntimeException("Unexpected fail");
            }
        };
        
        return fakeController;
    }
}


