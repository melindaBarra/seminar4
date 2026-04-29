/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;


/**
 * A view component that print updates of a {@link RepairOrder}.
 *
 * This observer writes the string representation of a repair order
 * to {@code System.out} every time the order gets updated.
 */
public class RepairOrderView implements RepairOrderObserver {

    @Override
    public void repairOrderUpdate(RepairOrder repairOrder) {
        System.out.println("--- Updated Repair Order ---");
        System.out.println(repairOrder);
    }

}

