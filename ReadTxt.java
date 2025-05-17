package main.java.Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadTxt {
    
    private String filename;
    
    /**
     * This is the constructor for the ReadTxt class.
     * 
     * @param filename the name of the file to be read
     */
    public ReadTxt(String filename){
        this.filename= filename;
    }

    /**
     * This reads from a file and stores the information in a 2d array based on what iss in the file.
     * 'o' is true and ''.' is false
     * 
     * @return the 2d array that is read from the file
     */
    public boolean[][] readFromFile() throws IOException{
        FileReader fileReader = new FileReader(filename);
        @SuppressWarnings("resource")
        BufferedReader bufferReader = new BufferedReader(fileReader); 
        int size = Integer.parseInt(bufferReader.readLine());
            
        boolean[][] board = new boolean[size][size]; 

        for (int i = 0; i < size; i++) {
            String line = bufferReader.readLine();
            for (int j = 0; j < size; j++) {
                if (line.charAt(j) == 'o') 
                {
                    board[i][j] = true;
                } 
                else if(line.charAt(j) == '.')
                {
                    board[i][j] = false;
                }
                else
                {
                    throw new IOException("Invalid character in file");
                }
            }
        }
        bufferReader.close();
        fileReader.close();
        return board;
    }
}