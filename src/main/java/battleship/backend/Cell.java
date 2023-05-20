package battleship.backend;

public class Cell {
    private int coordinateX;
    private int coordinateY;

    /**
     * Constructor that creates a cell from coordinates
     * @param coordinateX
     * @param coordinateY
     */
    public Cell(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Constructor that creates a cell from another cell
     * @param cell
     */
    public Cell(Cell cell) {
        coordinateX = cell.coordinateX;
        coordinateY = cell.coordinateY;
    }

    /**
     * Constructor that creates a cell from a string of the form 'letter' 'number', for example: 'a1'
     * @param input
     */
    public Cell(String input) {
        int number;
        if (input.length() == 3) {
            number = 10;
        } else {
            number = Character.digit(input.charAt(1), 10);
        }
        coordinateY = number - 1;
        char letter = input.charAt(0);
        int code = (int) letter;
        if (code < 80) {
            coordinateY = code - 65;
        } else {
            coordinateX = code - 97;
        }
    }

    /**
     * Method that gets the x coordinate
     * @return coordinateX
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Method that gets the y coordinate
     * @return coordinateY
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Method that sets the x coordinate
     * @param x
     */
    public void setCoordinateX(int x) {
        coordinateX = x;
    }

    /**
     * Method that sets the y coordinate
     * @param y
     */
    public void setCoordinateY(int y) {
        coordinateY = y;
    }

    /**
     * Method that compares two points
     * @param cell
     * @return true if points are equal and false if points are not equal
     */
    public boolean equals(Cell cell) {
        return coordinateX == cell.coordinateX && coordinateY == cell.coordinateY;
    }

    /**
     * Method that checks if the cells are on the same line
     * @param cell
     * @return true if the cells are on the same line and false if the cells are not on the same line
     */
    public boolean isCellsOnOneLine(Cell cell) {
        return coordinateX == cell.coordinateX || coordinateY == cell.coordinateY;
    }
}
