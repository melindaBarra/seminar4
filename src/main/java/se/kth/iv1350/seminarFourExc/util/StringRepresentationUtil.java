/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.util;
import java.util.List;
import  se.kth.iv1350.seminarFourExc.model.ReadableBike;
import  se.kth.iv1350.seminarFourExc.model.ReadableCustomer;
import  se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.RepairTask;

/**
 * Utility class that provides helper methods for building string representations in the model-layer's classes.
 * 
 * The methods in this class support the implementation of {@code toString()} methods.
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
    public static String repairTaskToString(RepairTask task) {
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
        builder.append(customer.getBike());
        return builder.toString();
    }
    
    
    
    /**
     * Returns a string representation a specified {@link RepairOrder}. 
     * @param repairOrder the specified {@code RepairOrder}.
     * @return A string representation of the specified {@code RepairOrder}.
     */
        public static String repairOrderToString(RepairOrder repairOrder) {
        StringBuilder builder = new StringBuilder();

        StringRepresentationUtil.addField(builder, "Order ID",repairOrder.getOrderId());
        StringRepresentationUtil.addNewLineSeparator(builder);

        StringRepresentationUtil.addField(builder, "State", repairOrder.getState());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Date of order placement", repairOrder.getDate());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Date of esstimated completion", repairOrder.getEstimatedCompletionDate());
        StringRepresentationUtil.addNewLineSeparator(builder);

        StringRepresentationUtil.addField(builder, "Problem Description", repairOrder.getProblemDescription());
        StringRepresentationUtil.addNewLineSeparator(builder);

        
        StringRepresentationUtil.addField(builder, "Diagnostic report", repairOrder.getDiagnosticReport());
        StringRepresentationUtil.addNewLineSeparator(builder);

        
        appendRepairTasks(builder, repairOrder);
        
        StringRepresentationUtil.addField(builder, "Base cost", repairOrder.getBaseCost());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Final cost", repairOrder.getFinalCost());
        StringRepresentationUtil.addNewLineSeparator(builder);
        
        builder.append(repairOrder.getCustomer());
        
        return builder.toString();
    }
        
    /**
     * Adds all repair tasks that belongs to a {@code repairOrder  }
     * to the given string builder, each on a new line.
     * 
     * @param repairOrder order that stores all added repair tasks. 
     * @param builder the given StringBuilder.
     */
   private static void appendRepairTasks(StringBuilder builder, RepairOrder repairOrder) {
        List<RepairTask> tasks = repairOrder.getRepairTasks();

        if(!repairOrder.hasTasks()) {
            addField(builder, "Repair tasks", null);
            StringRepresentationUtil.addNewLineSeparator(builder);
            return;
        }
        
        for (RepairTask task : tasks) {
            builder.append(task);
            StringRepresentationUtil.addNewLineSeparator(builder);
        } 

    }
}
