package battleship.backend;

public class Field {
    public static final int SIZE = 10;
    private int[][] field = new int[SIZE][SIZE];

    /**
     * Constructor that creates an empty field
     */
    public Field() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = 0;
            }
        }
    }

    /**
     * Method that returns the field size
     * @return field size
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Method that returns values(0,1,5,6) at given coordinates
     * @param x
     * @param y
     * @return 0 if empty cell; 1 if empty cell that was shot at; 5 if cell with a ship;
     * 6 if cell with a ship that was shot at
     */
    public int getCell(int x, int y) {
        return field[x][y];
    }

    /**
     * Method that returns values(0,1,5,6) at given cell
     * @param cell
     * @return 0 if empty cell; 1 if empty cell that was shot at; 5 if cell with a ship;
     * 6 if cell with a ship that was shot at
     */
    public int getCell(Cell cell) {
        return field[cell.getCoordinateX()][cell.getCoordinateY()];
    }

    /**
     * Method set value at given coordinate
     * @param x
     * @param y
     * @param n
     */
    public void setCell(int x, int y, int n) {
        field[x][y] = n;
    }

    /**
     * Method set value at given cell
     * @param cell
     * @param n
     */
    public void setCell(Cell cell, int n) {
        field[cell.getCoordinateX()][cell.getCoordinateY()] = n;
    }

    /**
     * Method by which the cells around the ship acquire the status of visible-empty
     * @param ship
     */
    public void setBorders(Ship ship) {
        int beginX = ship.getBegin().getCoordinateX();
        int beginY = ship.getBegin().getCoordinateY();
        int endX = ship.getEnd().getCoordinateX();
        int endY = ship.getEnd().getCoordinateY();

        if (ship.isVertical()) {
            helperMethod1(beginX, beginY, endY, true);
        } else {
            helperMethod1(beginY, beginX, endX, false);
        }
    }

    /**
     * Helper method to setBorders
     * @param a
     * @param b
     * @param c
     * @param isVertical
     */
    public void helperMethod1(int a, int b, int c, boolean isVertical) {
        for (int i = a - 1; i <= a + 1; i++) {
            for (int j = Math.min(b, c) - 1; j <= Math.max(b, c) + 1; j++) {
                if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                    if (isVertical) {
                        if (getCell(i, j) != 5 && getCell(i, j) != 6) {
                            setCell(i, j, 1);
                        }
                    } else {
                        if (getCell(j, i) != 5 && getCell(j, i) != 6) {
                            setCell(j, i, 1);
                        }
                    }
                }
            }
        }
    }
}
