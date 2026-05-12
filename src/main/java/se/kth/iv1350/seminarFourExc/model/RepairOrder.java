/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;
import se.kth.iv1350.seminarFourExc.model.discount.DiscountFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.security.SecureRandom;

/**
 * Represents a repair order created when a {@link Customer} reports a problem.
 * 
 * The order contains customer and bike information, the reported problem,
 * a diagnostic report and the list of repair tasks. It progresses through
 * different {@link RepairOrderState} values until the repair is completed.
 * 
 * {@code STANDARD_COMPLETION} defines the default number of days needed to
 * complete a repair, and {@code MAX_ORDER_ID_VALUE} is used when generating
 * random order IDs.
 */

public class RepairOrder {
    private static final int STANDARD_COMPLETION = 5;
    private static final int MAX_ORDER_ID_VALUE = 1000;
    
    private final SecureRandom idGenerator = new SecureRandom();
    private final Integer orderId;
    private final String problemDescription;
    private String diagnosticReport;
    private final Customer customer;
    private LocalDate date;
    private LocalDate estimatedCompletionDate;
    private RepairOrderState state;
    private double baseCost;
    private double finalCost;
    private final List<RepairTask> repairTasks = new ArrayList<>();
    
    private final List<RepairOrderObserver> observers = new ArrayList<>();
    
    /**
     * Creates an instance of {@code RepairOrder}.
     * 
     * @param problemDescription  a description of the bike's problems (provided by the customer).
     * @param customer the {@code Customer} who requested the repair.
    */
    public RepairOrder(String problemDescription, Customer customer) {
        this.problemDescription = problemDescription;
        this.customer = customer;
        this.date = LocalDate.now();
        this.orderId = generateOrderId();
        this.state = RepairOrderState.NEWLY_CREATED;
        this.baseCost = 0;
        this.finalCost = baseCost;
 
    }


    /**
     * Registers multiple observers that will be notified whenever a repair order changes.
     * The observers are received from the controller layer.
     * 
     * @param observers The new observers. Must not be {@code null}.
     */
    public void addObservers(List<RepairOrderObserver> observers) {
        this.observers.addAll(observers);
    }

    
    /**
     * Notifies all registered observers that this {@code repairOrder} has been updated.
     */   
    public void notifyObservers() {
        for(RepairOrderObserver observer : observers) {
            observer.repairOrderUpdate(this);
        }
    }
    
    
    /**
     * Adds all repair tasks given by a technician to this repair order.
     * This method is called after the diagnostic phase when the
     * technician has identified multiple tasks that need to be performed.
     *
     * @param tasks the list of repair tasks to add.
     */
    private void addRepairTasks(List<RepairTask> tasks) {
        repairTasks.addAll(tasks);
    }

    /**
     * Generates a unique identifier for this repair order.
     * The value is randomly generated and used to distinguish
     * this order from all other repair orders in the system.
     *
     * @return a randomly generated order ID
     */
    private Integer generateOrderId() {
        return idGenerator.nextInt(MAX_ORDER_ID_VALUE);
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public String getProblemDescription() {
        return this.problemDescription;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }
    
    /**
     * This method should only be called for the purpose of testing
     * {@link LowSeasonDiscount} and {@link DiscountFactory}
     */
    public RepairOrderState getState() {
        return this.state;
    }
        

    public LocalDate getEstimatedCompletionDate() {
        return this.estimatedCompletionDate;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public String getDiagnosticReport() {
        return this.diagnosticReport;
    }
    
    public List<RepairTask> getRepairTasks() {
        return this.repairTasks;
    }
    
    public double getFinalCost() {
        return this.finalCost;
    }
    
    public double getBaseCost() {
        return this.baseCost;
    }
    
    public int getStandardCompletion() {
        return this.STANDARD_COMPLETION;
    }
    
    /**
     * Checkes if the order's list of {@code RepairTask} is empty.
     * This method is used for the string representation of a RepairOrder.
     * @return true if the order contains one or more repair tasks, else false.
     */  
   public boolean hasTasks() {
        return !repairTasks.isEmpty();
   }
    
    /**
     * Updates this {@code RepairOrder} after the diagnostic phase has been completed.
     * 
     * The repair order is updated by adding repair tasks, attaching the
     * provided diagnostic report, calculating the cost and changing the order's state.
     *
     * @param tasks the list of repair tasks identified during the diagnostic.
     * @param diagnosticReport the diagnostic report created by the technician.
     */
   public void updateRepairOrderAfterDiagnosis(List<RepairTask> tasks, String diagnosticReport) {
        addRepairTasks(tasks);
        this.diagnosticReport = diagnosticReport;
        this.baseCost = calculateBaseCost();
        this.finalCost = calculateFinalCost();
        this.state = RepairOrderState.READY_FOR_APPROVAL;
        notifyObservers();
    }
   
   /**
    * Updates this repair order after the customer has accepted the repair.
    * The order state is set to {@code ACCEPTED} and an estimated completion
    * date and a final cost is calculated.
    */
   public void updateAfterAcceptance() {
       this.state = RepairOrderState.ACCEPTED;
       estimateCompletionDate();
       notifyObservers();
   }
   

    /**
      * Calculates and sets the estimated completion date for this repair order.
      * The date is based on the date when the repair was accepted and the
      * standard number of days needed for a repair
      * defined by {@code STANDARD_COMPLETION}.
      */
    private void estimateCompletionDate() {
        LocalDate dateOfAcceptance = LocalDate.now();
        this.estimatedCompletionDate = dateOfAcceptance.plusDays(STANDARD_COMPLETION);
    }
    
    
    /**
     * Calculates the total cost of every individual {@code RepairTask} associated with
     * this {@code RepairOrder}.
     *
     * @return the total cost.
     */
    private double calculateBaseCost() {
        double total = 0;
        
        for(RepairTask task : repairTasks ) {
            total += task.getCost();
        }

        return total;
    }
    
    /**
     * Calculates the final cost including a possible discount.
     *
     * @return the final cost.
     */
    private double calculateFinalCost() {
       DiscountFactory factory = DiscountFactory.getInstance();
       double discount = factory.getDiscountStrategy(this).calculateDiscount(this);
       return this.baseCost - discount;
    }

    
    
}
