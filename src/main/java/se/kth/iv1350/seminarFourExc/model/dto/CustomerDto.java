/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.dto;
import se.kth.iv1350.seminarFourExc.model.Customer;
import se.kth.iv1350.seminarFourExc.model.ReadableCustomer;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * customer information. 
 * <p>
 * This class is used to safely transfer 
 * customer data between different layers of the application 
 * without exposing the internal state of the domain model.
 * </p>
 */
public class CustomerDto implements ReadableCustomer{
    private final String phoneNo;
    private final String email;
    private final String name;
    private final BikeDto bikeDto;
    
   /**
    * Creates a instance of {@code CustomerDto}.
    * @param customer is the {@link Customer} which the CustomerDto is based on. 
   */
   public CustomerDto(Customer customer ) {
        this.phoneNo = customer.getPhoneNo();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.bikeDto = new BikeDto(customer.getBike());
   }

   @Override
   public String getName(){
       return this.name;
   }
   
   @Override
   public String getEmail(){
       return this.email;
   }
   
   @Override
   public String getPhoneNo(){
       return this.phoneNo;
   }
   
   @Override
   public BikeDto getBike() {
       return this.bikeDto;
   }
    



}
