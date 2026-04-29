/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

/**
 * A extension of {@link FileLogger} used for logging exceptions.
 * 
 * This logger writes all received log messages to a dedicated file named
 * {@code repairOrderLog.txt}. The file is created in the current working
 * directory and any existing file with the same name will be overwritten.
 */
public class RepairOrderFileLogger extends FileLogger {
    public RepairOrderFileLogger() {
        super("repairOrderLog.txt");
    }
}
