/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;


/**
 * A singleton registry that stores all repair orders ever created.
 * A {@link RepairOder} is never deleted from this registry.
*/
public class RepairOrderRegistry {
    private final Map<Integer, RepairOrder> repairOrders = new HashMap<>();
    private static final RepairOrderRegistry INSTANCE = new RepairOrderRegistry();
    private final List<RepairOrderRegistryObserver> observers = new ArrayList<>();



   
    /**
     * In order to implement a Singleton pattern this constructor is set to private,
     * ensuring that only one instance of this class exists.
     */
    private RepairOrderRegistry() {}
    
    
    /**
     * Returns the single instance of {@code RepairOrderRegistry}.
     *
     * @return the only existing {@code RepairOrderRegistry} instance.
     */
    public static RepairOrderRegistry getInstance() {
        return INSTANCE;
    }
    
    /**
     * Adds a {@link RepairOrder} to this repair order registry.
     * @param repairOrder the {@code RepairOrder} wished to be added to this registry. 
     */
    public void addRepairOrder(RepairOrder repairOrder) {
        repairOrders.put(repairOrder.getOrderId(), repairOrder);
        notifyObservers(new RepairOrderDto(repairOrder));
    }
    
    /**
     * Searches for a repair order by its order ID in the repair order registry.
     *
     * @param orderId the ID of the repair order to search for.
     * @return the {@link RepairOrder} that matches the order ID. 
    */
    public RepairOrder findRepairOrderById(Integer orderId) {
        return repairOrders.get(orderId);
    }
    
    
    /**
     * Registers multiple observers that will be notified whenever a this {@code repairOrderRegistry} changes.
     * The observers are received from the controller layer.
     * 
     * @param observers The new observers. Must not be {@code null}.
     */
    public void addObservers(List<RepairOrderRegistryObserver> observers) {
        this.observers.addAll(observers);
    }


    /**
     * Notifies all registered observers that this {@code repairOrderRegistry} has been updated.
     */   
    private void notifyObservers(RepairOrderDto repairOrderDto) {
        for(RepairOrderRegistryObserver observer : observers) {
            observer.newRepairOrderCreated(repairOrderDto);
        }
    }
    
 
}
