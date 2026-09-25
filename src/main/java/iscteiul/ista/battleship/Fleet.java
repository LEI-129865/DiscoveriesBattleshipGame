package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing the management of a player's fleet.
 */
public class Fleet implements IFleet {

    /**
     * Prints the details of a given list of ships to the console.
     *
     * @param ships The list of ships to print.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Default constructor. Initializes a new empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    @Override
    public List<IShip> getShips() {
        return ships;
    }

    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);
        return shipsLike;
    }

    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);
        return floatingShips;
    }

    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks if the ship is entirely within the boundaries of the board.
     *
     * @param s The ship to verify.
     * @return True if the ship is within bounds, False otherwise.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks if there is a collision risk (overlap or adjacency) with existing ships in the fleet.
     *
     * @param s The ship to verify.
     * @return True if there is a collision risk, False if it is safe to place.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Prints the fleet's status, separating ships by category and floating state.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all ships in the fleet belonging to a specific category.
     *
     * @param category The category of ships to print.
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships in the fleet that have not yet been sunk.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships registered in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }
}