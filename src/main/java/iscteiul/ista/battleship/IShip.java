package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface defining the core properties and behaviors of a ship.
 */
public interface IShip {
    /**
     * Gets the category or type of the ship.
     * @return A string representing the category (e.g., "Barca").
     */
    String getCategory();

    /**
     * Gets the size of the ship in terms of occupied board positions.
     * @return The size of the ship.
     */
    Integer getSize();

    /**
     * Gets the list of all positions currently occupied by the ship.
     * @return A list of IPosition objects.
     */
    List<IPosition> getPositions();

    /**
     * Gets the starting position (top-leftmost point) of the ship.
     * @return The starting position.
     */
    IPosition getPosition();

    /**
     * Gets the current bearing (orientation) of the ship.
     * @return The ship's bearing.
     */
    Compass getBearing();

    /**
     * Checks if the ship is still floating (not all positions have been hit).
     * @return True if the ship is still floating, False if sunk.
     */
    boolean stillFloating();

    /**
     * Gets the index of the highest row occupied by the ship.
     * @return The row index.
     */
    int getTopMostPos();

    /**
     * Gets the index of the lowest row occupied by the ship.
     * @return The row index.
     */
    int getBottomMostPos();

    /**
     * Gets the index of the leftmost column occupied by the ship.
     * @return The column index.
     */
    int getLeftMostPos();

    /**
     * Gets the index of the rightmost column occupied by the ship.
     * @return The column index.
     */
    int getRightMostPos();

    /**
     * Checks if the ship occupies a specific position.
     * @param pos The position to check.
     * @return True if the ship occupies the position, False otherwise.
     */
    boolean occupies(IPosition pos);

    /**
     * Checks if this ship is too close to (colliding or adjacent to) another ship.
     * @param other The other ship to compare against.
     * @return True if there is a collision risk, False otherwise.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks if the ship is too close to a specific position.
     * @param pos The position to check.
     * @return True if adjacent or overlapping, False otherwise.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot at a specific position on the ship.
     * @param pos The position that was hit.
     */
    void shoot(IPosition pos);
}