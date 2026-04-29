/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;

 /**
 * Represents the different states a {@link RepairTask} can be in. 
 * 
 * This enum defines the progression of a bike repair
 * from the start when the {@code RepairTask} is received until it is completed.
 * 
 * Complete (The repair task has been preformed and completed.)
 * Incomplete (The repair task has not yet been preformed and is not completed.)
 */

public enum RepairTaskState {
    COMPLETE,
    INCOMPLETE 
}