/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model;

/**
 * Represents a bike owned by a {@link Customer}.
 * <p>
 * A bike is identified by a unique serial number, which consists of
 * three uppercase letters followed by seven digits for example: {@code ABC1234567}.
 * A bike intance also stores information about the bike's model name and brand.
 * </p>
*/
public class Bike implements ReadableBike{
    private final String brand;
    private final String modelName;
    private final String serialNo;

    
    /**
     * Creates a instance of {@code Bike}.
     * 
     * @param brand the brand of the bike.
     * @param modelName the bike's model name.
     * @param serialNo the bike's serial number.
    */
    public Bike(String brand, String modelName, String serialNo) {
        this.brand = brand;
        this.modelName = modelName;
        this.serialNo = serialNo;
    }
   
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getSerialNo() {
        return this.serialNo;
    }
    
    /**
     * Computes this bike's hash code based on its unique serial number.
     *
     * <p>
     * This makes the hash code consistent with the {@link #equals(Object)} method,
     * since two bikes with the same serial number will produce the same hash code.
     * </p>
     *
     * @return the hash code for this bike.
     */
    @Override
    public int hashCode() {
        return this.serialNo.hashCode();
    }
    
    /**
     * Compares this bike to another object for equality.
     * <p>
     * Two bikes are considered equal if the other object is a {@link Bike}
     * and both bikes have the same serial number.
     * @param obj the object to compare with this bike.
     * @return {@code true} if the bikes have the same serial number, else {@code false}.
    */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bike)) {
            return false;
        }
        Bike other = (Bike) obj;
        return this.serialNo.equals(other.serialNo);
    }
    
      
}
