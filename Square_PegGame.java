package main.java.Assignment1;

import java.util.Collection;
import java.util.ArrayList;

public class Square_PegGame implements PegGame
{
    public int BOARD_SIZE;
    public int pegs = BOARD_SIZE * BOARD_SIZE - 1;
   
    //2D array of if board is filled or not
    //false = empty, true = filled
    public boolean board[][];

    //chosen point
    public int r1;
    public int c1;

    public Location Chosen_pos = new Location(r1, c1);

    public GameState current_status;

    public Square_PegGame(){
        this.BOARD_SIZE = 5;
        this.board = new boolean[BOARD_SIZE][BOARD_SIZE];
        this.pegs = BOARD_SIZE * BOARD_SIZE - 1;
    }

    public int getTotalPegs(){
        return pegs;
    }

    public int addPeg(){
        pegs++;
        return pegs; 
    }

    public int removePeg(){
        pegs--;
        return pegs;
    }

    /**
     * This method will return a collection of all possible moves that can be made on the board.
     * 
     * @return a collection of all possible moves that can be made on the board
     */
    public Collection<Location> getPossibleMoves()
    {
        // 8 locations to check for possible moves
        Location left_loc = new Location(Chosen_pos.row, Chosen_pos.col - 2);
        Location right_loc = new Location(Chosen_pos.row, Chosen_pos.col + 2);
        Location up_loc = new Location(Chosen_pos.row - 2, Chosen_pos.col);
        Location down_loc = new Location(Chosen_pos.row + 2, Chosen_pos.col);
        Location upleft_loc = new Location(Chosen_pos.row - 2, Chosen_pos.col - 2);
        Location upright_loc = new Location(Chosen_pos.row - 2, Chosen_pos.col + 2);
        Location downleft_loc = new Location(Chosen_pos.row + 2, Chosen_pos.col - 2);
        Location downright_loc = new Location(Chosen_pos.row + 2, Chosen_pos.col + 2);
        
        Location[] possible_locs = {left_loc, right_loc, up_loc, down_loc, upleft_loc, upright_loc, downleft_loc, downright_loc};


        ArrayList<Location> possible_moves = new ArrayList<Location>();

        //checks if the possible moves are valid
        for (int i = 0; i < possible_locs.length; i++)
        {
            if (possible_locs[i].getRow() >= 0 && possible_locs[i].getCol() >= 0 ||
            possible_locs[i].getRow() < BOARD_SIZE && possible_locs[i].getCol() < BOARD_SIZE ||
            board[possible_locs[i].getRow()][possible_locs[i].getCol()] == false)
            {
                possible_moves.add(possible_locs[i]);
            }
        }

        //returns the possible moves as a collection
        return possible_moves;
    }

    /**
     * This method will return the current state of the game.
     * 
     * @return the current state of the game
     */
    public GameState getGameState()
    {
        if (pegs == 1){
            current_status = GameState.WIN;
            return current_status;
        }
        //Stalemate when no possible moves are left
        else if (pegs > 1 && getPossibleMoves().isEmpty() == true){
            current_status = GameState.STALEMATE;
            return current_status;
        }
        else{
            return current_status;
        }
    }

    /**
     * This method will make a move on the board.
     * 
     * @param move the move to make
     * @throws PegGameException if the move is invalid
     */
    public void makeMove(Move move) throws PegGameException
    {
        Collection<Location> possible_moves = getPossibleMoves(); 

        for (Location m : possible_moves)
        {
            if (move.getTo().getRow() == m.getRow() && move.getTo().getCol() == m.getCol())
            {
                Location from = move.getFrom();
                Location to = move.getTo();
                Location middle = new Location((from.getRow() + to.getRow()) / 2, (from.getCol() + to.getCol()) / 2);
                
                board[from.getRow()][from.getCol()] = false;
                board[to.getRow()][to.getCol()] = true;
                board[middle.getRow()][middle.getCol()] = false;
                
                pegs--;
            }
        }
    }
    
    /**
     * You will need to implement a toString() that can be used to display the board. Use "-" (hyphen) to represent empty holes and "o" (lowercase o) to represent pegs on
     * the board.
     * 
     * @return a string representation of the board
     */
    @Override
    public String toString()
    {
        String board_string = "";
        for (int i = 0; i < BOARD_SIZE+1; i++) {
            if (i == 0) 
            { // print the column numbers
                board_string += "  ";
                for (int j = 0; j < BOARD_SIZE; j++) {
                    board_string += " " + j;
                }
            } 
            else 
            { // print the row numbers and the board
                board_string += i-1 + " |";
                for (int j = 0; j < BOARD_SIZE; j++) 
                {
                    if (board[i-1][j] == true) {
                        board_string += "o ";
                    } else if (board[i-1][j] == false) {
                        board_string += "- ";
                    }
                }
            }
            board_string += "\n";
        }
        board_string += "(o = peg, - = empty hole)";
        return board_string;
    }
}
