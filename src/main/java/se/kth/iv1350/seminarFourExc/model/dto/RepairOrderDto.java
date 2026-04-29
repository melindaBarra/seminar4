/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.dto;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import se.kth.iv1350.seminarFourExc.model.RepairOrderState;
import se.kth.iv1350.seminarFourExc.model.RepairTask;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link RepairOrder} information. 
 * 
 * This class is used to safely transfer 
 * repair order data between different layers of the application 
 * without exposing the internal state of the domain model..
 * 
 */

public class RepairOrderDto {
    private final Integer orderId;
    private final String problemDescription;
    private final CustomerDto customerDto;
    private final  LocalDate date;
    private final LocalDate estimatedCompletionDate;
    private final RepairOrderState state;
    private final String diagnosticReport;
    private final List<RepairTaskDto> repairTasks;
    private final double baseCost;
    private final double finalCost;

    /**
     * Creates a new {@code RepairOrderDto} that represents the given {@code RepairOrder}.
     * <p>
     * Before the customer accepts this order, only some order information is initialized.
     * This includes information about the order's ID, problem description, state, date,
     * {@code ustomerDto} and {@code BikeDto}.
     * After acceptance, this DTO also includes the order's diagnostic report, proposed repair tasks and the
     * estimated completion date.
     * </p>
     *
     * @param repairOrder the repair order to create a DTO from. Must not be {@code null}.
     */
    public RepairOrderDto(RepairOrder repairOrder) {
        this.orderId = repairOrder.getOrderId();
        this.problemDescription = repairOrder.getProblemDescription();
        this.state = repairOrder.getState();
        this.date = repairOrder.getDate();
        this.customerDto = new CustomerDto(repairOrder.getCustomer());

        this.diagnosticReport = repairOrder.getDiagnosticReport();
        this.estimatedCompletionDate = repairOrder.getEstimatedCompletionDate();
        this.repairTasks = createRepairTaskDtos(repairOrder);
        
        this.baseCost = repairOrder.getBaseCost();
        this.finalCost = repairOrder.getFinalCost();

    }

    /**
     * Converts the list of {@code RepairTask} objects in the given {@code RepairOrder}
     * into a list of {@code RepairTaskDto} objects. 
     * <p>
     * If the repair order has no repair tasks
     * the returned list will be empty.
     * </p>
     *
     * @param repairOrder the repair order containing the domain repair tasks
     * @return a list of {@code RepairTaskDto} representing the repair tasks.
     */
    private List<RepairTaskDto> createRepairTaskDtos(RepairOrder repairOrder) {
        List<RepairTaskDto> taskDtos = new ArrayList<>();

        if (repairOrder.getRepairTasks() != null) {
            for (RepairTask task : repairOrder.getRepairTasks()) {
                taskDtos.add(new RepairTaskDto(task));
            }
        }
        
        return taskDtos;
    }

   public String getProblemDescription(){
       return this.problemDescription;
   }
  
   public Integer getOrderId(){
       return this.orderId;
   }
   
   public CustomerDto getCustomerDto(){
       return this.customerDto;
   }
   
   public RepairOrderState getState(){
       return this.state;
   }
   
    public double baseCost() {
        return this.baseCost;
    }
    
    public double finalCost() {
        return this.finalCost;
    }
    




}