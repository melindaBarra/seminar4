/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;
import org.junit.jupiter.api.Disabled; 
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarFourExc.model.discount.DiscountFactory;
import testUtil.TestObserver;


/**
 * Test the class {@link RepairOrder}.
 */
public class RepairOrderTest {
    private Bike customerBike = new Bike("Monark", "Karin", "SVE1234567");
    private Customer customerNils = new Customer("0701234567", "nils@kth.com", "Nils", customerBike);
    private String problemDescr = "The bike is worn out";
    private RepairTask taskToAdd = new RepairTask("Replace chain.");
    private RepairTask additionalTaskToAdd = new RepairTask("Fix breaks.");
    private String diagnosticReport = "Chain snapped";
    private TestObserver observer = new TestObserver();
    private RepairOrder order;

  
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    

    @BeforeEach
    public void setUp() {
        order = new RepairOrder(problemDescr, customerNils);
        order.addObservers(List.of(observer));
     
    }

    
    @AfterEach
    public void tearDown() {
        order = null;
    }
    
    
    @Test
    public void testStateChangedToNewlyCreated() {
        RepairOrderState expResult = RepairOrderState.NEWLY_CREATED;
        RepairOrderState result = order.getState();

        assertEquals(expResult, result, "State was set correctly.");
    }
    
    @Test
    public void testOrderIdIsGenerated() {
        Integer id = order.getOrderId();
        assertNotNull(id, "Id was not generated");
    }
    
    @Test
    public void testNewOrderIdIsGenerated() {
        Integer id = order.getOrderId();
        
        String otherProbDescr = "Wheel is missing";
        RepairOrder otherOrder = new RepairOrder(otherProbDescr, customerNils);
        Integer otherId = otherOrder.getOrderId();
        
        assertNotEquals(id, otherId, "A new ID was not generated");
    }


   @Test
   public void testIfMultipleTasksWereAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
       int expResult = 2;
       int result = order.getRepairTasks().size();

       assertEquals(expResult, result, "The correct number of tasks were not added.");
   }
   
   @Test
   public void testIfOneTasksWasAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd), diagnosticReport);
       int expResult = 1;
       int result = order.getRepairTasks().size();

       assertEquals(expResult, result, "The correct number of tasks were not added.");
   }

   @Test
   public void testIfProblemDescriptionIsAdded() {
       String result = order.getProblemDescription();

       assertEquals(problemDescr, result, "Problem description was not set correctly.");
   }
  
   @Test
   public void testIfDiagnosticReportIsAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
       String result = order.getDiagnosticReport();

       assertEquals(diagnosticReport, result, "Diagnostic report was not set correctly.");
   }

    @Test
    public void testStateChangedToReadyForApproval() {
        order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
        RepairOrderState expResult = RepairOrderState.READY_FOR_APPROVAL;
        RepairOrderState result = order.getState();

        assertEquals(expResult, result, "State was not updated correctly.");
    }
    
    
    
    @Test
    public void testBaseCostAfterDiagnosis() {
        double expBaseCost = taskToAdd.getCost() + additionalTaskToAdd.getCost();
        order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
        double resultBaseCost = order.getBaseCost();
        assertEquals(expBaseCost, resultBaseCost, "Base cost was not calculated correctly.");
    }
    
    @Test
    public void testFinalCostAfterDiagnosis() {
        DiscountFactory factory = DiscountFactory.getInstance();
        double expBaseCost = taskToAdd.getCost() + additionalTaskToAdd.getCost();
        
        order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
        double discount = factory.getDiscountStrategy(order).calculateDiscount(order);

        double expFinalCost = expBaseCost - discount;
        
        double resultFinalCost = order.getFinalCost();
        assertEquals(expFinalCost, resultFinalCost, "Final cost was not calculated correctly.");
    }
    

   @Test
   public void testStateChangedToAccepted() {
       order.updateAfterAcceptance();
       RepairOrderState expResult = RepairOrderState.ACCEPTED;
       RepairOrderState result = order.getState();
       assertEquals(expResult, result, "State was not updated to correctly.");
   }

   @Test
   public void testEstimatedCompletionDate() {
       LocalDate expDate = LocalDate.now().plusDays(order.getStandardCompletion());
       order.updateAfterAcceptance();
       LocalDate resultDate = order.getEstimatedCompletionDate();
       assertEquals(expDate, resultDate, "Estimated completion date was incorrect.");
   }
   
   
   
   /**
    * Test if calling the {@code updateAfterAcceptance} method results in a notification
    * to all registered observers. 
    */
    @Disabled("Notification method in the RepairOrder class is set to private")
    @Test
    public void testUpdateAfterAcceptanceNotifies() {
        order.updateAfterAcceptance();
        assertTrue(observer.notified);
    }
    
    
   /**
    * Test if calling the {@code updateRepairOrderAfterDiagnosis} method results in a notification
    * to all registered observers. 
    */
    @Disabled("Notification method in the RepairOrder class is set to private")
    @Test
    public void testUpdateAfterDiagnosisNotifies() {
        order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
        assertTrue(observer.notified);
    }

   

    
    
}
