package main.java.Assignment1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ReadTxtTest {

    @Test
    public void testReadFromFile() {
        // Arrange
        String filename = "sixBySix.txt";
        ReadTxt readTxt = new ReadTxt(filename);

        // Act
        boolean[][] result;
        try {
            result = readTxt.readFromFile();

            // Assert
            assertNotNull(result);
            assertEquals(3, result.length);
            assertEquals(3, result[0].length);
            assertTrue(result[0][0]);
            assertFalse(result[0][1]);
            assertTrue(result[0][2]);
            assertFalse(result[1][0]);
            assertTrue(result[1][1]);
            assertFalse(result[1][2]);
            assertTrue(result[2][0]);
            assertFalse(result[2][1]);
            assertTrue(result[2][2]);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}