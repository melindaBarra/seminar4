/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.controller;
import java.util.List;
import java.util.ArrayList;
import se.kth.iv1350.seminarFourExc.integration.CustomerRegistry;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.model.dto.CustomerDto;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.model.RepairTask;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarFourExc.model.RepairOrderState;
import se.kth.iv1350.seminarFourExc.integration.CustomerNotFoundException;
import se.kth.iv1350.seminarFourExc.integration.DatabaseFailureException;
import se.kth.iv1350.seminarFourExc.integration.Logger;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;

/**
 * This is the application's only controller. All method calls to the model
 * must pass through this controller.
 * 
 */

public class Controller {
    private final Logger logger;
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final List<RepairOrderObserver> observers = new ArrayList<>();
    
    /**
     * Creates an instance of {@link Controller}.
     *
     * @param customerRegistry the registry used for storing and retrieving instances of {@link Customer}.
     * @param repairOrderRegistry the registry that handles instances of {@link RepairOrder}.
     * @param logger a instance that implements the {@link Logger} interface.
     */
    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Logger logger) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.logger = logger;
    }


    /**
     * Registers multiple observers that will be notified whenever a repair order changes.
     * The observers are received from the view layer.
     * 
     * @param observers The new observers. Must not be {@code null}.
     */
    public void addObservers(List<RepairOrderObserver> observers) {
        this.observers.addAll(observers);
    }
    
    
    /**
     * Searches for a {@link Customer} in the {@code customerRegistry}.
     *
     * @param phoneNo the customer's phone number used as a lookup key.
     * @throws CustomerNotFoundException if the searched customer was not found
     * in the {@code customerRegistry}.
     * @throws OperationFailedException if {@code phoneNo} triggers a data base exception.
     * @return a new {@link CustomerDto} based on the found customer instance.
     */
    public CustomerDto searchCustomer(String phoneNo)
            throws CustomerNotFoundException, OperationFailedException {

        try {
            Customer customer = customerRegistry.findCustomerByPhoneNo(phoneNo);
            return new CustomerDto(customer);
        } catch (DatabaseFailureException e) {
            logger.log(e.getMessage());
            throw new OperationFailedException("Something went wrong. Please try again later.", e);
        }
    }


    /**
     * Creates a {@link RepairOrder} based on the customer's problem description.
     * The created repair order is then added to the {@code repairOrderRegistry}.
     *
     * @param problemDescr the problem description provided by the customer.
     * @param phoneNo the customer's phone number.
     * @throws CustomerNotFoundException if the customer who wishes to place the repair order cannot
     * be found in the {@code customerRegistry}.
     * @throws OperationFailedException if {@code phoneNo} triggers a data base exception.
     * @return the order ID of the created {@code repairOrder}.
     */
    public Integer registerProblemDescription(String problemDescr, String phoneNo) throws CustomerNotFoundException,
            OperationFailedException{
        try {
            Customer customer = customerRegistry.findCustomerByPhoneNo(phoneNo);
            RepairOrder repairOrder = new RepairOrder(problemDescr, customer);
            repairOrder.addObservers(observers);
            repairOrder.notifyObservers();
            repairOrderRegistry.addRepairOrder(repairOrder);
            return repairOrder.getOrderId();
        } catch (DatabaseFailureException e) {
            logger.log(e.getMessage());
            throw new OperationFailedException("Something went wrong. Please try again later.", e);
        }
        
    }
    
    /**
     * Returns a {@code repairOrderDto} containing the information needed in order
     * to write a diagnostic report for a {@code Bike}.
     *
     * This method provides a DTO representation of the repair order that exposes only
     * the fields relevant to the technician's work.
     *
     * @param orderId the identifier of the repair order.
     * @return a DTO with repair order data.
     */
    public RepairOrderDto getRepairOrderDto(Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        RepairOrderDto repairOrderDto = new RepairOrderDto(repairOrder);
        return repairOrderDto;
    }

    /**
     * Updates an existing {@link RepairOrder} after a technician completes the diagnostic phase.
     *
     * The repair order is updated by adding the determined repair tasks and the diagnostic report
     * written by the technician. The order's state is updated accordingly.
     *
     * @param orderId the identifier of the repair order to update.
     * @param tasks the list of {@link RepairTask} instances.
     * @param diagnosticReport the diagnostic report written by the technician.
     */
    public void updateAfterDiagnostic(List<RepairTask> tasks, String diagnosticReport, Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        repairOrder.updateRepairOrderAfterDiagnosis(tasks, diagnosticReport);
    }
    
    /**
     * Handles the customer's decision regarding the proposed repair tasks.
     *
     * If the repair order is accepted, the order state is updated and a receipt
     * containing all relevant repair order information is returned to the view layer.
     *
     * This application does not support the alternative flow in which the customer
     * rejects the repair order.
     *
     * @param decision the customer's decision (accepted or rejected).
     * @param orderId  the identifier of the repair order.
     * @return a DTO representing the accepted repair order.
     */
    public RepairOrderDto handleCustomerDecision(RepairOrderState decision, Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        repairOrder.updateAfterAcceptance();
        RepairOrderDto acceptedOrderDto = new RepairOrderDto(repairOrder);
        return acceptedOrderDto;
    }
    
}
