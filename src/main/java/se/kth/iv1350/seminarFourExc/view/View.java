/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;
import se.kth.iv1350.seminarFourExc.controller.Controller;
import se.kth.iv1350.seminarFourExc.model.dto.CustomerDto;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.model.RepairTask;
import se.kth.iv1350.seminarFourExc.model.RepairOrderState;
import se.kth.iv1350.seminarFourExc.integration.CustomerNotFoundException;
import se.kth.iv1350.seminarFourExc.controller.OperationFailedException;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderFileLogger;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution
 * with calls to all system operations in the controller.
 */
public class View {
    private final Controller contr; 
    
    /**
     * Creates a new instance that uses the specified controller for all
     * calls to other layers.
     *
     * @param contr the controller used for all calls to other layers.
     */
    public View(Controller contr){
        this.contr = contr;
        RepairOrderView observerView = new RepairOrderView();
        RepairOrderLogger observerLogger = new RepairOrderLogger(new RepairOrderFileLogger());
        contr.addOrderObservers(List.of(observerView, observerLogger));
        contr.addRegistryObservers(List.of(observerView, observerLogger));

    }
    
    
    
    /**
     * Executes a hardcoded demonstration of the application's workflow.
     *
     * This method simulates a complete repair process by calling several system
     * operations in the {@link Controller}. This method assumes that the customer and bike
     * already exist in the {@link CustomerRegistry} since {@code searchCustomer} requires a
     * pre‑registered customer.
     *
     * @param customerPhoneNo the phone number of the customer used during the simulation.
     */
    public void runFakeExecution(String customerPhoneNo) {
        String problemDescr;
        RepairOrderDto repairOrderDto;
        
        System.out.println("---Bike repair workshop---\n");
        try {
            CustomerDto customerDto = contr.searchCustomer(customerPhoneNo);
            System.out.println("Searching for customer.\n");
            System.out.println("--- Found customer ---");
            printCustomer(customerDto);
            
            printDivider(); 

            problemDescr = "The customer's bike always gets a flat tire";
            System.out.println("Registers the customer's problem description and creates an order.\n");
            Integer orderId = contr.registerProblemDescription(problemDescr, customerPhoneNo);
            
           
            printDivider();
            
            String diagnosticReport = "A wheel is missing";
            List<RepairTask> tasks = new ArrayList<>();
            tasks.add(new RepairTask("Replace brake pads"));
            tasks.add(new RepairTask("Add a new wheel"));
            System.out.println("Updates the repair order after diagnosis.\n");
            contr.updateAfterDiagnostic(tasks, diagnosticReport, orderId);
            
            printDivider();
            
            System.out.println("Customer accepts the repair tasks and cost proposed by the receptionist.\n");
            repairOrderDto = contr.handleCustomerDecision(RepairOrderState.ACCEPTED, orderId);

            

            System.out.println("\n---Customer leaves the workshop---");
            
        } catch (CustomerNotFoundException e) {
            System.out.println("ERROR: No customer with the searched phone number '" +customerPhoneNo +"' exists.");
        } catch (OperationFailedException e) {
            System.out.println("ERROR: Something went wrong. Please try again later.");
        } catch (Exception exc) {
            System.out.println("ERROR: Unexpected failure.");
        }
        
    }
    
    private void printDivider() {
        System.out.println("\n-----------------------------------------------------\n");

    }
    
   private void printCustomer(CustomerDto customerDto) {
       String stringFormat = StringRepresentationUtil.customerToString(customerDto);
       System.out.println(stringFormat);
   }
    
}
