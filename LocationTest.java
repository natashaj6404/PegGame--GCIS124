package main.java.Assignment1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class LocationTest {

    @Test
    void testToString() {
        Location expected = new Location(4, 5);
        String Actual = "Row: 4 Col: 5";
        assertEquals(Actual, expected.toString()); // the two strings should be the same 
        
    }


}

