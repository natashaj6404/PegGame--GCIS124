package main.java.Assignment1;

import java.util.Collection;

/**
 * PegGame interface
 */
public interface PegGame {

    /**
     * Get the current location of the empty hole
     * 
     * @return the location of the empty hole
     */
    public Collection<Location> getPossibleMoves();

    /**
     * determine the gamestate of the peg game and actions based off condition
     * 
     * @return the gamestate of the peg game
     */
    public GameState getGameState();

    /**
     * make a move in the peg game
     * 
     * @param move the move to make
     */
    public void makeMove(Move move) throws PegGameException;

}
