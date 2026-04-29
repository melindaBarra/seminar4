/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints log messages to a file. The log file will be in the current directory and will be named
 * according to a specified {@code String}.
 */
public class FileLogger implements Logger {
    private PrintWriter logStream;

    /**
     * Creates a new instance and also creates a new log file. An existing log file will be deleted.
     * @param fileName the name of the file that is created. 
     */
    public FileLogger(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     *
     * @param message The string that will be printed to the log file.
     */
    @Override
    public void log(String message) {
        logStream.println(message);
    }
}