package battleship.backend;

import java.util.ArrayList;

public class Player {
    private String name;
    private MyField myField = new MyField();
    private EnemyField enemyField = new EnemyField();
    private ArrayList<Ship> ships = new ArrayList<>();

    /**
     * player name constructor
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Method that returns a player name
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns a field
     * @return player field
     */
    public MyField getMyField() {
        return myField;
    }

    /**
     * Method that returns an array of ships
     * @return arrays of ships
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Method that returns an enemy field
     * @return enemy field
     */
    public EnemyField getEnemyField() {
        return enemyField;
    }

    /**
     * Method that checks if all ships are placed
     * @return true if all ships are placed and false if not
     */
    public boolean allShipsArePlaced() {
        int count1DeckShips = 0;
        int count2DeckShips = 0;
        int count3DeckShips = 0;
        int count4DeckShips = 0;

        for (Ship i : ships) {
            if (i.getSize() == 1) {
                count1DeckShips += 1;
            }
            if (i.getSize() == 2) {
                count2DeckShips += 1;
            }
            if (i.getSize() == 3) {
                count3DeckShips += 1;
            }
            if (i.getSize() == 4) {
                count4DeckShips += 1;
            }
        }
        return count1DeckShips == 1 && count2DeckShips == 1 && count3DeckShips == 0 && count4DeckShips == 0;
    }

    /**
     * Method that checks if the player has hit an enemy ship
     * @param cell
     * @param enemy
     * @return true if hit and false if not hit
     */
    private boolean hit(Cell cell, Player enemy) {
        return enemy.myField.getCell(cell) == 5 || enemy.myField.getCell(cell) == 6;
    }

    /**
     * Method that determines the ship by a given point
     * @param cell
     * @return the ship that owns this cell
     */
    public Ship knowShipForCell(Cell cell) {
        for (Ship i: ships) {
            if (i.cellInShip(cell)) {
                return i;
            }
        }
        return ships.get(0);
    }

    /**
     * Method that checks if the ship containing the given point is dead
     * @param cell
     * @return true if dead and false if alive
     */
    public boolean shipIsDead(Cell cell) {
        Ship ship = knowShipForCell(cell);
        for (int j = 0; j < ship.getSize(); j++) {
            if (myField.getCell(ship.getCell(j)) == 5) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that converts the opponent's own field and the opponent's field, which is displayed by the given player
     * @param cell
     * @param enemy
     * @return true if there was a hit and false if there was no hit
     */
    public boolean attack(Cell cell, Player enemy) {
        enemy.myField.takeShot(cell);
        enemyField.shot(cell, hit(cell, enemy));
        return hit(cell, enemy);
    }

    /**
     * a method that checks if a ship can be added and adds it
     * @param begin
     * @param end
     * @return true if a ship has been added and false if a ship cannot be added
     */
    public boolean tryAddShip(Cell begin, Cell end) {
        if (myField.canAddShip(begin, end)) {
            Ship ship = new Ship(begin, end);
            myField.addShip(ship);
            ships.add(ship);
            return true;
        }
        return false;
    }

    /**
     * a method that checks if a ship can be deleted and delete it
     * @param anyCell
     * @return true if a ship has been deleted and false if a ship cannot be deleted
     */
    public boolean tryDeleteShip(Cell anyCell) {
        if (this.myField.canDeleteShip(anyCell)) {
            for (int i = 0; i < this.ships.size(); i++) {
                if (this.ships.get(i).cellInShip(anyCell)) {
                    this.myField.deleteShip(this.ships.get(i));
                    this.ships.remove(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method that checks if the player is alive
     * @return true if player is alive and false if dead
     */
    public boolean isAlive() {
        return myField.survivorsShips();
    }
}
