package main;

/* Note: Class can also be written as
*  @param symbol
*  However ignored this method because I wanted to understand
*  how the code works properly - I think this method is newer
*/

public class Player {
    // Creating player value variable
    private String symbol;

    // Player object
    public Player(String symbol) {
        this.symbol = symbol;
    }

    // Getter for Player symbol e.g. X or O
    public String getSymbol() {
        return symbol;
    }
}
