/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;
import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.seminarFourExc.model.Customer;

/**
 * A singleton registry containing details of all customers who have ever consulted the workshop.
 * Customers are stored using their phone number as the lookup key.
 * 
 * This class static field {@code TRIGGER_DB_FAILURE} stores a hard coded phone number
 * used to simulate a database failure where the database cannot be accessed.
 */
public class CustomerRegistry {
    public static final String TRIGGER_DB_FAILURE = "9999999999";
    private final Map<String, Customer> customers = new HashMap<>();
    private static final CustomerRegistry INSTANCE = new CustomerRegistry();
   
    /**
     * In order to implement a Singleton pattern this constructor is set to private,
     * ensuring that only one instance of this class exists.
     */
    private CustomerRegistry() {}
    
    
    /**
     * Returns the single instance of {@code CustomerRegistry}.
     *
     * @return the only existing {@code CustomerRegistry} instance.
     */
    public static CustomerRegistry getInstance() {
        return INSTANCE;
    }
    
    /**
     * Adds a {@link Customer} to this customer registry.
     * @param customer the {@code Customer} to be added to this registry. 
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.getPhoneNo(), customer);
    }

    /**
     * Searches for a customer by phone number in the customer registry.
     *
     * Because the application does not handle alternative flows,
     * all given phone numbers are expected to belong to an already registered customer.
     * Therefore, this method is expected to always return a customer reference and never {@code null}.
     * 
     * @param phoneNo the searched customer's phone number, used as a lookup key.
     * @throws CustomerNotFoundException if the searched customer was not found in this {@code customerRegistry}.
     * @throws DatabaseFailureException if {@code phoneNo} triggers a data base failure.
     * @return the matching customer.
    */
    public Customer findCustomerByPhoneNo(String phoneNo) throws CustomerNotFoundException, DatabaseFailureException {
        
        if(phoneNo.equals(TRIGGER_DB_FAILURE)) {
            throw new DatabaseFailureException("Simulated database failure triggered by using phone number: \"" 
                                                + phoneNo + "\" as lookup key.");
        }
        
        Customer customer = customers.get(phoneNo);
     
        if (customer == null) {
            throw new CustomerNotFoundException(phoneNo);
        }
        return customer;
    }

}

