/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;
import java.util.List;
import  se.kth.iv1350.seminarFourExc.model.ReadableBike;
import  se.kth.iv1350.seminarFourExc.model.ReadableCustomer;
import  se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.model.dto.RepairTaskDto;

/**
 * Utility class used by the View layer to building string representations of DTOs and domain objects.
 * 
 */
public final class StringRepresentationUtil {

    /**
     * Prevents creating an instance of {@code StringRepresentationUtil}. 
     */
    private StringRepresentationUtil() {
    }

    /**
     * Appends a field and its value to a provided StringBuilder.
     * Uses the format name:value. Null values are written as "Not specified yet".
     *
     * @param builder the StringBuilder to append to.
     * @param name the field name.
     * @param value the field value.
     */
    private static void addField(StringBuilder builder, String name, Object value) {
        builder.append(name);
        builder.append(": ");
        builder.append(value == null ? "Not specified yet" : value.toString());
    }


    /**
     * Appends a standard separator ("\n") between fields in a string representation.
     *
     * @param builder the StringBuilder to append to.
     */
    private static void addNewLineSeparator(StringBuilder builder) {
        builder.append("\n");
    }
    
    /**
     * Appends a standard separator (", ") between fields in a string representation.
     *
     * @param builder the StringBuilder to append to.
     */
    private static void addCommaSeparator(StringBuilder builder) {
        builder.append(", ");
    }
    
    /**
     * Creates a string representation of the specified {@link ReadableBike}.
     *
     * The returned string includes the bike data provided by any object that
     * implements the {@link ReadableBike} interface, including both
     * domain objects and DTOs.
     *
     * @param bike the customer to represent as a string. Must not be {@code null}.
     * @return a string representation of the specified bike.
     */
    public static String bikeToString(ReadableBike bike) {
        StringBuilder builder = new StringBuilder("Bike");
        builder.append("[");
        StringRepresentationUtil.addField(builder, "Brand", bike.getBrand());
        StringRepresentationUtil.addCommaSeparator(builder);
        StringRepresentationUtil.addField(builder, "Model name", bike.getModelName());
        StringRepresentationUtil.addCommaSeparator(builder);
        StringRepresentationUtil.addField(builder, "SerialNo", bike.getSerialNo());
        builder.append("]");
        return builder.toString();
    }
    
    

    /**
     * Returns a string representation a specified {@link RepairTask}. 
     * @param task the specified {@code RepairTask}.
     * @return A string representation of the specified {@code RepairTask}.
     */
    public static String repairTaskToString(RepairTaskDto task) {
        StringBuilder builder = new StringBuilder("Repair task");
        builder.append("[");

        StringRepresentationUtil.addField(builder, "Task description", task.getTaskDescription());
        StringRepresentationUtil.addCommaSeparator(builder);

        StringRepresentationUtil.addField(builder, "Cost", task.getCost());
        StringRepresentationUtil.addCommaSeparator(builder);

        StringRepresentationUtil.addField(builder, "State", task.getState());
        builder.append("]");

        return builder.toString();
    }
    
    
    /**
     * Creates a string representation of the specified {@link ReadableCustomer}.
     *
     * The returned string includes the customer data provided by any object that
     * implements the {@link ReadableBike} interface, including both
     * domain objects and DTOs.
     *
     * @param customer the customer to represent as a string. Must not be {@code null}.
     * @return a string representation of the specified customer.
     */
    public static String customerToString(ReadableCustomer customer) {
        StringBuilder builder = new StringBuilder("Customer");
        builder.append("[");
        StringRepresentationUtil.addField(builder, "Name", customer.getName());
        StringRepresentationUtil.addCommaSeparator(builder);
        StringRepresentationUtil.addField(builder, "Email", customer.getEmail());
        StringRepresentationUtil.addCommaSeparator(builder);
        StringRepresentationUtil.addField(builder, "PhoneNo", customer.getPhoneNo());
        builder.append("]\n");
        builder.append(bikeToString(customer.getBike()));
        return builder.toString();
    }
    
    
    
    /**
     * Returns a string representation a specified {@link RepairOrderDto}. 
     * @param repairOrderDto the specified {@code RepairOrderDto}.
     * @return A string representation of the specified {@code RepairOrderDto}.
     */
        public static String repairOrderToString(RepairOrderDto repairOrderDto) {
        StringBuilder builder = new StringBuilder();

        StringRepresentationUtil.addField(builder, "Order ID",repairOrderDto.getOrderId());
        StringRepresentationUtil.addNewLineSeparator(builder);

        StringRepresentationUtil.addField(builder, "State", repairOrderDto.getState());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Date of order placement", repairOrderDto.getDate());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Date of estimated completion", repairOrderDto.getEstimatedCompletionDate());
        StringRepresentationUtil.addNewLineSeparator(builder);

        StringRepresentationUtil.addField(builder, "Problem Description", repairOrderDto.getProblemDescription());
        StringRepresentationUtil.addNewLineSeparator(builder);

        
        StringRepresentationUtil.addField(builder, "Diagnostic report", repairOrderDto.getDiagnosticReport());
        StringRepresentationUtil.addNewLineSeparator(builder);

        
        appendRepairTasks(builder, repairOrderDto);
        
        StringRepresentationUtil.addField(builder, "Base cost", repairOrderDto.getBaseCost());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Final cost", repairOrderDto.getFinalCost());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        builder.append(customerToString(repairOrderDto.getCustomer()));
        
        return builder.toString();
    }
        
    /**
     * Adds all repair tasks that belongs to a {@code repairOrder  }
     * to the given string builder, each on a new line.
     * 
     * @param repairOrderDto order that stores all added repair tasks. 
     * @param builder the given StringBuilder.
     */
   private static void appendRepairTasks(StringBuilder builder, RepairOrderDto repairOrder) {
        List<RepairTaskDto> tasks = repairOrder.getRepairTasks();

        if(tasks.isEmpty()) {
            addField(builder, "Repair tasks", null);
            StringRepresentationUtil.addNewLineSeparator(builder);
            return;
        }
        
        for (RepairTaskDto task : tasks) {
            builder.append(repairTaskToString(task));
            StringRepresentationUtil.addNewLineSeparator(builder);
        } 

    }
}
