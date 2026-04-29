/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

/**
 * Exception thrown when a {@link Customer} search fails because the phone number
 * used to look up the customer does not exist in the {@link CustomerRegistry}.
 */

public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new instance indicating that no customer with
     * the specified phone number exists
     * in the CustomerRegistry.
     *
     * @param phoneNo The specified phone number used for lookup.
     */
    public CustomerNotFoundException(String phoneNo) {
        super("No found customer with phone number: " + phoneNo);
    }
}
