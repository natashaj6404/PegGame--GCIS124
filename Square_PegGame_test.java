package main.java.Assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

public class Square_PegGame_test {

    @Test void testGetPossibleMoves() {
        Square_PegGame game = new Square_PegGame();
        game.addPeg();
        Collection<Location> possibleMoves = game.getPossibleMoves();
        assertEquals(1, possibleMoves.size(), "There should be one possible move after adding a peg");
    }

    @Test void testMakeMove() {
        Square_PegGame game = new Square_PegGame();
        game.addPeg();
        game.makeMove(new Move(new Location(0, 0), new Location(0, 2)));
        Collection<Location> possibleMoves = game.getPossibleMoves();
        assertEquals(0, possibleMoves.size(), "There should be no possible moves after making a move");
    }


}
