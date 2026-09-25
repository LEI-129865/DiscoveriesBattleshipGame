package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface defining the behavior and management of a player's fleet.
 */
public interface IFleet {
    Integer BOARD_SIZE = 10;
    Integer FLEET_SIZE = 10;

    /**
     * Gets the complete list of ships comprising the fleet.
     * @return A list of IShip objects.
     */
    List<IShip> getShips();

    /**
     * Adds a ship to the fleet, validating board boundaries and collision rules.
     * @param s The ship to be added.
     * @return True if the ship was successfully added, False otherwise.
     */
    boolean addShip(IShip s);

    /**
     * Retrieves a list of ships belonging to a specific category.
     * @param category The desired category (e.g., "Galeao").
     * @return A list of ships matching the given category.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retrieves a list of ships that have not yet been sunk.
     * @return A list of ships still floating.
     */
    List<IShip> getFloatingShips();

    /**
     * Finds and returns a ship occupying the specified position.
     * @param pos The position to check on the board.
     * @return The ship found at that position, or null if none exists.
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the current status of the entire fleet to the console.
     */
    void printStatus();
}