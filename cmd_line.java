package main.java.Assignment1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class cmd_line implements PegGame {
    
    //instance variables
    public int BOARD_SIZE;
    public int pegs;
   
    //2D array of locations and boolean array of empty holes
    //false = empty, true = filled
    public boolean board[][];

    //game status
    public GameState current_status;

    //current position of chosen peg
    int r1;
    int c1;
    int r2;
    int c2;

    public Location Chosen_pos;

    /**
     * This constructor will create a new game with the given board size and empty hole configuration.
     * 
     * @param board_size the size of the board
     */
    public cmd_line(boolean[][] boardboard){
        this.BOARD_SIZE = boardboard.length;
        this.board = boardboard;
        int peg = 0;
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                if (board[i][j] == true){
                    peg = peg + 1;
                }
            }
        }
        this.pegs = peg;
        this.Chosen_pos = new Location(r1, c1);
        this.current_status = GameState.NOT_STARTED;
    }

    public Location getCurrentPosition(){
        return this.Chosen_pos;
    }

    public Location setCurrentPosition(Location loc){
        this.Chosen_pos = loc;
        return this.Chosen_pos;
    }

    /**
     * This method will return the current state of the game.
     * 
     * @return the current state of the game
     */
    public boolean[][] getBoard()
    {
        return board;
    }

    /**
     * This method will return a collection of all possible moves that can be made on the board.
     * 
     * @return a collection of all possible moves that can be made on the board
     */
    public Collection<Location> getPossibleMoves()
    {
        Collection<Location> possibleMoves = new ArrayList<>();

        Chosen_pos = getCurrentPosition();

        // 8 locations to check for possible moves
        Location left_loc = new Location(Chosen_pos.getRow(), Chosen_pos.getCol() - 2);
        Location right_loc = new Location(Chosen_pos.getRow(), Chosen_pos.getCol() + 2);
        Location up_loc = new Location(Chosen_pos.getRow() - 2, Chosen_pos.getCol());
        Location down_loc = new Location(Chosen_pos.getRow() + 2, Chosen_pos.getCol());
        Location upleft_loc = new Location(Chosen_pos.getRow() - 2, Chosen_pos.getCol() - 2);
        Location upright_loc = new Location(Chosen_pos.getRow() - 2, Chosen_pos.getCol() + 2);
        Location downleft_loc = new Location(Chosen_pos.getRow() + 2, Chosen_pos.getCol() - 2);
        Location downright_loc = new Location(Chosen_pos.getRow() + 2, Chosen_pos.getCol() + 2);

        Location[] possible_locs = {left_loc, right_loc, up_loc, down_loc, upleft_loc, upright_loc, downleft_loc, downright_loc};

        //checks if the possible moves are valid
        for (int i = 0; i < possible_locs.length; i++)
        {
            if (possible_locs[i].getRow() >= 0 && possible_locs[i].getCol() >= 0 &&
            possible_locs[i].getRow() < BOARD_SIZE && possible_locs[i].getCol() < BOARD_SIZE &&
            board[possible_locs[i].getRow()][possible_locs[i].getCol()] == false && 
            board[(Chosen_pos.getRow() + possible_locs[i].getRow()) / 2][(Chosen_pos.getCol() + possible_locs[i].getCol()) / 2] == true)
            {
                possibleMoves.add(possible_locs[i]);
            }
        }

        return possibleMoves;
    }

    /**
     * This method will return the current state of the game.
     * 
     * @return the current state of the game
     */
    public GameState getGameState()
    {

        if (pegs == 1)
        {
            current_status = GameState.WIN;
        }
        else if (pegs >1 && getPossibleMoves().isEmpty())
        {
            current_status = GameState.STALEMATE;
        }
        else
        {
            current_status = GameState.IN_PROGRESS;
        }

        return current_status;
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
            else if (move.getTo().getRow() == m.getRow() && move.getTo().getCol() == m.getCol())
            {
                throw new PegGameException("Invalid move");
            }
        }
    }

    /**
     * this method wiil print out the board
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

    /**
     * This method will return the current state of the game.
     * 
     * @param args the command-line arguments
     */
    public void playGame(cmd_line game) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        System.out.println("\nWelcome to the Peg Game!");
        System.out.println("\nThe goal is to jump pegs over other pegs to remove them from the board.");
        System.out.println("\nThe game is over when no more moves can be made.");
        System.out.println("Good luck!");

        while (!quit && game.current_status != GameState.WIN && game.current_status != GameState.STALEMATE) {
            // introduce the game
            game.current_status = GameState.IN_PROGRESS;

            System.out.println("\nThe current board is:"); // print the board
            System.out.println(game.toString());
  
            System.out.print("\nEnter a command: (move row_1 column_1 row_2 column_2 or quit)\n");
            String command = scanner.nextLine();

            String[] parts = command.split(" ");
            String cmd = parts[0];

            if (cmd.equals("move")) {
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);

                setCurrentPosition(new Location(r1, c1));
                Move move = new Move(getCurrentPosition(), new Location(r2, c2));
                
                try {
                    game.makeMove(move);
                } catch (PegGameException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            } else if (cmd.equals("quit")) {
                quit = true;
                scanner.close();
            } else {
                System.out.println("Invalid command");
            }
        }
        scanner.close();
    }
}
