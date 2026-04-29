/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;

 /**
 * Represents the different states a {@link RepairOrder} can be in. 
 * This enum defines the progression of a bike repair
 * from the start when the {@code RepairOrder} is received until it is completed or cancelled.
 * 
 * Newly created (no diagnostic report or repair tasks)
 * Ready for approval (a technician has entered diagnostic report and repair tasks)
 * Rejected (the customer didn’t want to do the proposed repair tasks)
 * Accepted (the customer accepted the proposed repair tasks)
 * Completed (the reparation has been done, but the customer hasn’t payed yet)
 * Payed (the customer has payed).
 */

public enum RepairOrderState {
    NEWLY_CREATED,
    READY_FOR_APPROVAL,
    REJECTED,
    ACCEPTED,
    COMPLETED,
    PAYED  
}

