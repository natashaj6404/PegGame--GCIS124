package main.java.Assignment1;

import java.util.Scanner;
public class Project1Main {
    /**
     * This is the main method that will read in a file and play the game.
     * 
     * @param args the command line arguments
     * 
     * @throws FileNotFoundException if the file is not found
     * @throws PegGameException if the move is invalid
     * 
     * @return the current state of the game
     */
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a filename: ");
        String filename = scanner.nextLine(); 
        
        ReadTxt reader = new ReadTxt(filename); // Create an instance of ReadTxt
        boolean[][] board = reader.readFromFile(); // Read the file and store the information in a 2d array

        cmd_line game = new cmd_line(board); // Create an instance of cmd_line

        game.playGame(game); // Play the game
    
        scanner.close();
    }
}
