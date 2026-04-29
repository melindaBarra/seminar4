/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.controller;

/**
 * An exception that is thrown when an operation in the controller cannot be
 * completed because something went wrong in a lower layer.
 */

public class OperationFailedException extends Exception {
    /**
     * Creates an exception that wraps the original technical error so the controller can
     * report a simpler message to the user while still storing the real cause
     * for debugging.
     *
     * @param message a short description of what went wrong.
     * @param cause the underlying exception that caused the failure.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}

