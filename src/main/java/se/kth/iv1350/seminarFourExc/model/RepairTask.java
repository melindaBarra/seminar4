/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;

/**
 * Represents a single task within a {@link RepairOrder}. 
 * <p>
 * A repair order may consist of multiple repair tasks, where each task describs a 
 * specific action that must be performed on the customer's bike. 
 * A {@code RepairTask} contains a name, a task description, a cost (set to {@code DEFAULT_COST) 
 * and a state indicating whether the task is {@code RepairTaskState.INCOMPLETE} or {@code RepairTaskState.COMPLETE}.
 * </p>
 */
public class RepairTask {
    private final int DEFAULT_COST = 150;
    private double cost;
    private RepairTaskState state = RepairTaskState.INCOMPLETE;
    private String taskDescription;
    
    /**
     * Creates a new instance of {@code RepairTask} with a specified task description.
     * The task is initialized with the state {@code RepairTaskState.INCOMPLETE}.
     * 
     * @param taskDescription A description of the repair task.
    */
    public RepairTask(String taskDescription) {
        this.taskDescription = taskDescription;
        this.state = RepairTaskState.INCOMPLETE;
        this.cost = DEFAULT_COST;
    }
    
    /**
     * Creates a new instance of {@code RepairTask} with a specified task description and cost.
     * The task is initialized with the state {@code RepairTaskState.INCOMPLETE}.
     * 
     * @param taskDescription A description of the repair task.
     * @param cost the specified cost.
    */
    public RepairTask(String taskDescription, double cost) {
        this.taskDescription = taskDescription;
        this.state = RepairTaskState.INCOMPLETE;
        this.cost = cost;
    }
    
    public double getCost() {
        return this.cost;
    }
    
    public RepairTaskState getState() {
        return this.state;
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
}
