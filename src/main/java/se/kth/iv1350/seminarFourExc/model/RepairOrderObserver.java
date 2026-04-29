/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;

/**
 * An observer interface used by classes that want to be notified when a
 * {@link RepairOrder} has been created or updated.
 *
 * This interface is used by different views so they can react to changes in repair orders without the model
 * needing to know anything about how the views work.
 */
public interface RepairOrderObserver {
    void repairOrderUpdate(RepairOrder repairOrder);
}
