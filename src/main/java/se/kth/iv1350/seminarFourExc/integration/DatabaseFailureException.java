/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

/**
 * Exception thrown when a {@link Customer} search fails because the phone number
 * used to look up the customer triggers a data base failure.
 */
public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String message) {
        super(message);
    }
}

