/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.integration;

/**
 * A simple logging interface used by {@link Controller} to store messages from the application.
 * 
 * Different implementations decide how the message is stored or displayed. 
 * The controller only depends on this interface, which makes it easy to change the way of logging
 * without change other parts of the application.
 * 
 */
public interface Logger {
    void log(String message);
}
