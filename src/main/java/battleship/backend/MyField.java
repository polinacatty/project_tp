package battleship.backend;

public class MyField extends Field {

    public MyField() {
        super();
    }

    /**
     * Method that adds a ship to the field
     * @param ship
     */
    public void addShip(Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            setCell(ship.getCell(i), 5);
        }
    }

    /**
     * Method that delete a ship to the field
     * @param ship
     */
    public void deleteShip(Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            setCell(ship.getCell(i), 0);
        }
    }

    /**
     * Method that transforms the field after an incoming shot
     * @param cell
     */
    public void takeShot(Cell cell) {
        if (getCell(cell) == 0) {
            setCell(cell, 1);
        }
        if (getCell(cell) == 5) {
            setCell(cell, 6);
        }
    }

    /**
     * Method that checks if there are live ships on the field
     * @return true if there are survivor ship and false if there are no survivor ship
     */
    public boolean survivorsShips() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (getCell(i, j) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that checks if it is possible to put a ship on the field with the given start and end
     * @param cell1
     * @param cell2
     * @return true if you can put the ship and false if you can't put the ship
     */
    public boolean canAddShip(Cell cell1, Cell cell2) {
        int cell1X = cell1.getCoordinateX();
        int cell1Y = cell1.getCoordinateY();
        int cell2X = cell2.getCoordinateX();
        int cell2Y = cell2.getCoordinateY();

        if (cell1.isCellsOnOneLine(cell2)) {
            Ship ship = new Ship(cell1, cell2);
            if (ship.getSize() < 5) {
                if (ship.isVertical()) {
                    return helperMethod(cell1X, cell1Y, cell2Y, true);
                } else {
                    return helperMethod(cell1Y, cell1X, cell2X, false);
                }
            }
        }
        return false;
    }

    /**
     * Helper method to canAddShip
     * @param a
     * @param b
     * @param c
     * @param isVertical
     */
    public boolean helperMethod(int a, int b, int c, boolean isVertical) {
        for (int i = a - 1; i <= a + 1; i++) {
            for (int j = Math.min(b, c) - 1; j <= Math.max(b, c) + 1; j++) {
                if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                    if (isVertical) {
                        if (getCell(i, j) != 0) {
                            return false;
                        }
                    } else {
                        if (getCell(j, i) != 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method that checks if the ship can be deleted at a given point
     * @param anyCell
     * @return true if the ship can be deleted and false if the ship cannot be deleted
     */
    public boolean canDeleteShip(Cell anyCell) {
        return getCell(anyCell) == 5;
    }
}
