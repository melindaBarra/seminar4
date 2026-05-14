/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.controller;
import se.kth.iv1350.seminarFourExc.controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.dto.CustomerDto;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.integration.CustomerRegistry;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.model.RepairTask;
import se.kth.iv1350.seminarFourExc.model.RepairOrderState;
import se.kth.iv1350.seminarFourExc.integration.CustomerNotFoundException;
import se.kth.iv1350.seminarFourExc.integration.ExceptionFileLogger;
import java.util.List;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderFileLogger;
import se.kth.iv1350.seminarFourExc.view.RepairOrderLogger;
import se.kth.iv1350.seminarFourExc.view.RepairOrderView;
import testUtil.TestObserver;

/**
 * Test the class {@link Controller}.
 */

public class ControllerTest {
    private String customerPhoneNo = "0730000000";
    private String customerEmail = "nils@kth.se";
    private String customerName = "Nils";
    private String bikeSerialNo = "SVE1234567";
    private Bike customerBike = new Bike("Monark", "Karin", bikeSerialNo);
    private Customer customerNils = new Customer(customerPhoneNo, customerEmail, customerName, customerBike);

    private String problemDescr = "A problem description";
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Controller ctrl;
    private RepairTask task = new RepairTask("A task");
    private String diagnosticReport = "The bike is in bad condition";
    private ExceptionFileLogger excLogger = new ExceptionFileLogger();

    @BeforeEach
    public void setUp() {
        
        customerRegistry = CustomerRegistry.getInstance();
        customerRegistry.addCustomer(customerNils);
        
        repairOrderRegistry = RepairOrderRegistry.getInstance();
        ctrl = new Controller(customerRegistry, repairOrderRegistry, excLogger);
        RepairOrderView observerView = new RepairOrderView();
        RepairOrderLogger observerLogger = new RepairOrderLogger(new RepairOrderFileLogger());
        ctrl.addObservers(List.of(observerView, observerLogger));
    
    }

    @AfterEach
    public void tearDown() {
        customerRegistry = null;
    }


    /**
     * Verifies that a simulated database failure triggers a OperationFailedException.
     * @throws CustomerNotFoundException if {@code searchCustomer} did not find the 
     * searched customer in the {@code customerRegistry}.
     */
    @Test
    public void testSearchCustomerDatabaseFailure() throws CustomerNotFoundException{
        String failingPhoneNo = customerRegistry.TRIGGER_DB_FAILURE;

        try {
            ctrl.searchCustomer(failingPhoneNo);
            fail("Expected OperationFailedException to be thrown.");
        } catch (OperationFailedException e) {
            assertTrue(e.getMessage().contains("Something went wrong. Please try again later."),
                "Exception message does not describe the failure correctly.");
        }
    }
    
    
    /**
     * Verifies that a simulated database failure triggers a OperationFailedException.
     * @throws CustomerNotFoundException if {@code searchCustomer} did not find the 
     * searched customer in the {@code customerRegistry}.
     */
    @Test
    public void testRegProbDescriptionDatabaseFailure() throws CustomerNotFoundException{
        String failingPhoneNo = customerRegistry.TRIGGER_DB_FAILURE;

        try {
            ctrl.registerProblemDescription(problemDescr,failingPhoneNo);
            fail("Expected OperationFailedException to be thrown.");
        } catch (OperationFailedException e) {
            assertTrue(e.getMessage().contains("Something went wrong. Please try again later."),
                "Exception message does not describe the failure correctly.");
        }
    }
    
    
    /**
     * Verifies that a unknown phone number triggers a CustomerNotFoundException.
     * @throws OperationFailedException if the phone number triggers a database failure.  
     */
    @Test
    public void testSearchCustomerWithUnknownPhoneNo() throws OperationFailedException {
        String unknownPhone = "0000000000";

        try {
            ctrl.searchCustomer(unknownPhone);
            fail("Expected CustomerNotFoundException to be thrown.");
        } catch (CustomerNotFoundException e) {
            assertTrue(e.getMessage().contains(unknownPhone),
                "Exception message does not contain the phone number that caused the error.");
        }
    }

    /**
     * Verifies that a unknown phone number triggers a CustomerNotFoundException.
     * @throws OperationFailedException if the phone number triggers a database failure.  
     */
    @Test
    public void testRegisterProbDescriptionUnknownPhoneNo() throws OperationFailedException {
        String unknownPhone = "0000000000";

        try {
            ctrl.registerProblemDescription(problemDescr, unknownPhone);
            fail("Expected CustomerNotFoundException to be thrown.");
        } catch (CustomerNotFoundException e) {
            assertTrue(e.getMessage().contains(unknownPhone),
                "Exception message does not contain the phone number that caused the error.");
        }
    }

    
    
    @Test
    public void testSearchCustomerCorrectName() throws CustomerNotFoundException,OperationFailedException  {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        assertEquals(customerName, returnedCustomerDto.getName(),
                "Wrong name given by the returned DTO");
    }

    @Test
    public void testSearchCustomerCorrectPhoneNo() throws CustomerNotFoundException,OperationFailedException  {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        assertEquals(customerPhoneNo, returnedCustomerDto.getPhoneNo(),
                "Wrong phone number given by the returned DTO");
    }

    @Test
    public void testSearchCustomerCorrectEmail() throws CustomerNotFoundException,OperationFailedException  {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        assertEquals(customerEmail, returnedCustomerDto.getEmail(),
                "Wrong email given by the returned DTO");
    }

    @Test
    public void testSearchCustomerCorrectBike() throws CustomerNotFoundException, OperationFailedException {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        assertEquals(bikeSerialNo, returnedCustomerDto.getBike().getSerialNo(),
                "Wrong bike given by the returned DTO");
    }


    
    
    
    @Test
    public void testIfRepairOrderGetsRegistered() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder result = getRegisteredRepairOrder();
        assertNotNull(result, "No repair order was created.");
    }

    @Test
    public void testIfCorrectProblemDescriptionIsRegistered() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        assertEquals(problemDescr, registeredOrder.getProblemDescription(),
                "Wrong problem description was registered.");
    }
    
    /**
    * Test if calling the {@code registerProblemDescription} method results in a notification
    * to all registered observers. 
    * 
    * @throws CustomerNotFoundException if {@code searchCustomer} did not find the 
    * searched customer in the {@code customerRegistry}.
    * @throws OperationFailedException if the phone number triggers a database failure.  
    */  
    @Test
    public void registerProblemDescription_NotifiesObservers() throws CustomerNotFoundException, OperationFailedException {
        TestObserver observer = new TestObserver();
        ctrl.addObservers(List.of(observer));

        ctrl.registerProblemDescription(problemDescr, customerPhoneNo);

        assertTrue(observer.notified);
    }
    
    @Test
    public void testCorrectCustomerIsRegistered() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        assertEquals(customerNils, registeredOrder.getCustomer(),
                "Wrong customer was registered for the repair order.");
    }

    @Test
    public void testCorrectBikeIsRegistered() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        assertEquals(customerBike, registeredOrder.getCustomer().getBike(),
                "Wrong bike was registered for the repair order.");
    }

    
    
    @Test
    public void testGetRepairOrderDtoContainsCorrectOrder() throws CustomerNotFoundException,OperationFailedException {
        Integer expOrderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        RepairOrderDto orderDto = ctrl.getRepairOrderDto(expOrderId);
        Integer resultOrderId = orderDto.getOrderId();
        assertEquals(expOrderId, resultOrderId,
                "Wrong order ID in RepairOrderDto.");
    }
    
    
    
    
    @Test
    public void testUpdateAfterDiagnosticUpdatesTask() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder resultOrder = getUpdatedRepairOrder();
        String expTask = task.getTaskDescription();
        String resultTask =  resultOrder.getRepairTasks().get(0).getTaskDescription();

        assertEquals(expTask, resultTask,
                "Wrong repair task was added to repair order.");
    }

    @Test
    public void testUpdateAfterDiagnosticUpdatesReport() throws CustomerNotFoundException, OperationFailedException {
        RepairOrder resultOrder = getUpdatedRepairOrder();
        String expReport = resultOrder.getDiagnosticReport();
        assertEquals(diagnosticReport, expReport,
                "Wrong report was added to repair order.");
    }

    
    
    @Test
    public void testHandleCustomerDecisionReturnsDto() throws CustomerNotFoundException, OperationFailedException {
        RepairOrderDto resultDto = getRepairOrderDtoAfterAcceptance();
        assertNotNull(resultDto, "RepairOrderDto was not returned.");
    }

    @Test
    public void testIfHandleCustomerDecisionUpdatesState() throws CustomerNotFoundException, OperationFailedException {
        RepairOrderDto orderDto = getRepairOrderDtoAfterAcceptance();
        assertEquals(RepairOrderState.ACCEPTED, orderDto.getState());
    }


    private RepairOrder getRegisteredRepairOrder() throws CustomerNotFoundException, OperationFailedException  {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        return repairOrderRegistry.findRepairOrderById(orderId);
    }

    private RepairOrderDto getRepairOrderDtoAfterAcceptance() throws CustomerNotFoundException, OperationFailedException {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        return ctrl.handleCustomerDecision(RepairOrderState.ACCEPTED, orderId);
    }

    private RepairOrder getUpdatedRepairOrder() throws CustomerNotFoundException, OperationFailedException  {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        ctrl.updateAfterDiagnostic(List.of(task), diagnosticReport, orderId);
        return repairOrderRegistry.findRepairOrderById(orderId);
    }
}
