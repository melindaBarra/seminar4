/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminarFourExc.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    
    @Test
    public void testMain() {
        String[] args = null;
        Main.main(args);
    }
    
    /**
     * Verifies that the {@code main} method produces some of the expected
     * output from the program's startup.
     * 
     * In this test the one of the headings produce from View is used as expected
     * output ({@code expResult}).
     */
    @Test
    public void testMainPrintsSomethingFromView() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(null);

        String result = outContent.toString();
        String expResult = "---Bike repair workshop---";

        assertTrue(result.contains(expResult), "Main did not produce expected output from View.");
    }

}
