/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.startup;

import se.kth.iv1350.seminarFourExc.controller.Controller;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarFourExc.integration.CustomerRegistry;
import se.kth.iv1350.seminarFourExc.integration.ExceptionFileLogger;
import se.kth.iv1350.seminarFourExc.view.View;
/**
 * This class contains the applications main-method, which is used
 * to start the application. 
 */
public class Main {
    /**
     * The main method used to start the application. 
     * 
     * Initializes a demo setup containing a customer, a bike and
     * the required registries. This demo data is required so that
     * parts of the program that need existing domain objects can run.
     * 
     * To trigger a {@link CustomerNotFoundException}, use any phone number
     * that is different from {@code demoCustomerPhoneNo} and {@code "9999999999"},
     * which is the phone number used to trigger a {@link DatabaseFailureException}.
     * One of these phone numbers is provided in the call to {@code runFakeExecution}. 
     * 
     * @param args The application does not take any command lines.
     */
    public static void main(String[] args){
        
        String demoBikeSerialNo = "SVE1234567";
        Bike demoBike = new Bike("Monark", "Karin", demoBikeSerialNo);
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        String demoCustomerPhoneNo = "0731234567";
        Customer demoCustomer = new Customer(demoCustomerPhoneNo, "nils@kth.se", "Nils", demoBike);
        customerRegistry.addCustomer(demoCustomer);
        
        ExceptionFileLogger excFileLogger = new ExceptionFileLogger();
        
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.getInstance();
        Controller contr = new Controller(customerRegistry, repairOrderRegistry, excFileLogger);
        View view = new View(contr);
        view.runFakeExecution("9999999999");
    }
    
}


