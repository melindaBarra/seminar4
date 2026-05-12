/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.model.dto;
import se.kth.iv1350.seminarFourExc.model.Bike;
import se.kth.iv1350.seminarFourExc.model.ReadableBike;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link Bike} information. 
 * <p>
 * This class is used to safely transfer 
 * bike data between different layers of the application 
 * without exposing the internal state of the domain model.
 * </p>
 */
public class BikeDto implements ReadableBike{

    private final String brand;
    private final String modelName;
    private final String serialNo;

    /**
     * Creates a {@code BikeDto} based on the given {@code Bike} object.
     * 
     * @param bike the bike that the DTO is based on.
     */
    public BikeDto(Bike bike) {
        this.brand = bike.getBrand();
        this.modelName = bike.getModelName();
        this.serialNo = bike.getSerialNo();
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
        return serialNo;
    }
    
    


}

