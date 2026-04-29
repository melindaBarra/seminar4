/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.view;

import se.kth.iv1350.seminarFourExc.integration.Logger;
import se.kth.iv1350.seminarFourExc.model.RepairOrder;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;

/**
 * A view component that logs updates of a {@link RepairOrder}.
 *
 * This observer writes the string representation of a repair order
 * to a file using the provided {@link Logger} every time the order gets updated.
 */

public class RepairOrderLogger implements RepairOrderObserver {
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
    public void repairOrderUpdate(RepairOrder order) {
        logger.log(order.toString());
    }
}
