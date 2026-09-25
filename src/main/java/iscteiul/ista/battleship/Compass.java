package iscteiul.ista.battleship;

/**
 * Enumeration representing the cardinal points used for ship bearing.
 *
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Constructor for the compass bearing.
     * @param c The character representing the cardinal direction.
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Gets the character representation of the direction.
     * @return The direction character.
     */
    public char getDirection() {
        return c;
    }

    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character to its corresponding Compass bearing.
     * @param ch The character to convert ('n', 's', 'e', 'o').
     * @return The matching Compass bearing, or UNKNOWN if invalid.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}