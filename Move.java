package main.java.Assignment1;

public class Move {
    // instance variables
    public Location from;
    public Location to;

    // constructor
    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    // Default constructor
    public Location getFrom() {
        return this.from;
    }

    /**
     * Set the from location
     * 
     * @param from
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Set the destination
     * 
     * @param to
     */
    public Location getTo() {
        return this.to;
    }

    /**
     * toString method
     * 
     * @return the string representation of the move
     */
    @Override
    public String toString() {
        return "Move from " + this.from + " to " + this.to;
    }

    /**
     * hassCode method
     * 
     * @return the hashcode of the move
     */
    @Override
    public int hashCode() {
        return this.from.hashCode() + this.to.hashCode();
    }
}
