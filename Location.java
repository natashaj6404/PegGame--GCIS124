package main.java.Assignment1;

public class Location {

    // instance variables
    public int row;
    public int col;

    // constructor
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Default constructor
    public Location() {
        this.row = 0;
        this.col = 0;
    }

    /**
     * Get the row of the location
     * 
     * @return the row of the location
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the column of the location
     * 
     * @return the column of the location
     */
    public int getCol() {
        return this.col;
    }

    /**
     * toString method
     * 
     * @return the string representation of the location
     */
    @Override
    public String toString() {
        return "Row: " + this.row + " Col: " + this.col;
    }

    /**
     * determine if two locations are equal
     * 
     * @param obj the object to compare
     * 
     * @return true if the locations are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !Location.class.isAssignableFrom(obj.getClass())) 
        {
            return false;
        }

        final Location other = (Location) obj;
        if (this.row != other.row) 
        {
            return false;
        }
        if (this.col != other.col) 
        {
            return false;
        }
        return true;
    }

}
