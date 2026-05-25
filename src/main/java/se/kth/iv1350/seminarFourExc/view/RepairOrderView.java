/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistryObserver;



/**
 * A view component that print updates of a {@link RepairOrder}.
 *
 * This observer writes the string representation of a repair order
 * to {@code System.out} every time the order gets updated.
 */
public class RepairOrderView implements RepairOrderObserver, RepairOrderRegistryObserver {
    
    @Override
    public void repairOrderUpdate(RepairOrderDto repairOrderDto) {
        printUpdatedOrder(repairOrderDto);
    }

    @Override
    public void newRepairOrderCreated(RepairOrderDto repairOrderDto) {
        printUpdatedOrder(repairOrderDto);
    }

    private void printUpdatedOrder(RepairOrderDto repairOrderDto) {
        System.out.println("--- Updated Repair Order ---");
        String stringFormat = StringRepresentationUtil.repairOrderToString(repairOrderDto);
        System.out.println(stringFormat);
    }

}

