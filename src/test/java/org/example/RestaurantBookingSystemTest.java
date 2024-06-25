package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RestaurantBookingSystemTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void testMakeBookingSuccessful() {
        String input = "1\n1\n2\nJohn\ny\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        RestaurantBookingSystem.main(new String[]{});
        String output = outputStream.toString();
        assertTrue(output.contains("Table booked successfully!"));
    }

    @Test
    public void testMakeBookingCancel() {
        String input = "1\n1\n2\nJohn\nn\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        RestaurantBookingSystem.main(new String[]{});
        String output = outputStream.toString();
        assertTrue(output.contains("Booking cancelled."));
    }

    @Test
    public void testViewAvailableTables() {
        String input = "3\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        RestaurantBookingSystem.main(new String[]{});
        String output = outputStream.toString();
        assertTrue(output.contains("Available tables:"));
    }

    @Test
    public void testExitSystem() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        RestaurantBookingSystem.main(new String[]{});
        String output = outputStream.toString();
        assertTrue(output.contains("Thank you for using our Restaurant Booking System. Goodbye!"));
    }
}
