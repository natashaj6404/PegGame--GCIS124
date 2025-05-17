package main.java.Assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {

  
    @Test
    void testHashCode() {
        Location from1 = new Location(1, 3);
        Location to1 = new Location(3, 4);
        Location from2 = new Location(1, 3);
        Location to2 = new Location(3, 4);

        // Creating two idential moves with the same starting location that is 'from' and final location which is two and then comapring them

        Move move1 = new Move(from1, to1); 
        Move move2 = new Move(from2, to2);

        assertEquals(move1.hashCode(), move2.hashCode()); //the hash codes for equal object should be identical 
    }
}

