/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testUtil;
import se.kth.iv1350.seminarFourExc.model.RepairOrderObserver;
import se.kth.iv1350.seminarFourExc.model.dto.RepairOrderDto;



/**
 * This class is used for unit test in {@link RepairOrderTest}.
 */
public class TestObserver implements RepairOrderObserver {
    public boolean notified = false;

    @Override
    public void repairOrderUpdate(RepairOrderDto orderDto) {
        notified = true;
    }
}
