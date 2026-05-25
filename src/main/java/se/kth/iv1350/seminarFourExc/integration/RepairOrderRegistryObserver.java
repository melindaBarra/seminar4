/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;


/**
 * An observer interface used by classes that want to be notified when a
 * {@link RepairOrderRegistry} has been updated.
 */
public interface RepairOrderRegistryObserver {
    void newRepairOrderCreated(RepairOrderDto repairOrderDto);
}
