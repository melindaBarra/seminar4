/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;

import se.kth.iv1350.seminarFourExc.integration.Logger;
import se.kth.iv1350.seminarFourExc.integration.RepairOrderRegistryObserver;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;

/**
 * A view component that logs updates of a {@link RepairOrder}.
 *
 * This observer writes the string representation of a repair order
 * to a file using the provided {@link Logger} every time the order gets updated.
 */

public class RepairOrderLogger implements RepairOrderObserver, RepairOrderRegistryObserver {
    private final Logger logger;

    /**
     * Creates an instance of {@link RepairOrderLogger}.
     *
     * @param logger a instance that implements the {@link Logger} interface.
     */
    public RepairOrderLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void repairOrderUpdate(RepairOrderDto repairOrderDto) {
        logUpdatedOrder(repairOrderDto);
    }
    
    @Override
    public void newRepairOrderCreated(RepairOrderDto repairOrderDto) {
        logUpdatedOrder(repairOrderDto);
    }
    
    private void logUpdatedOrder(RepairOrderDto repairOrderDto) {
        String stringFormat = StringRepresentationUtil.repairOrderToString(repairOrderDto);
        logger.log(stringFormat);
    }
    
}
