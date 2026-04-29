/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.dto;
import se.kth.iv1350.seminarFourExc.model.RepairTaskState;
import se.kth.iv1350.seminarFourExc.model.RepairTask;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link RepairTask} information. 
 * <p>
 * This class is used to safely transfer 
 * repair task data between different layers of the application 
 * without exposing the internal state of the domain model..
 * </p>
 */
public class RepairTaskDto {
    private final double cost;
    private RepairTaskState state = RepairTaskState.INCOMPLETE;
    private final String taskDescription;
    
    /**
     * Creates a new instance of {@code RepairTaskDto} that represents the given {@code RepairTask}.
     *
     * @param repairTask the repair order to create a DTO from. Must not be {@code null}.
     */
    
    public RepairTaskDto(RepairTask repairTask) {
        this.cost = repairTask.getCost();
        this.state = repairTask.getState();
        this.taskDescription = repairTask.getTaskDescription();
    }



    
    
}