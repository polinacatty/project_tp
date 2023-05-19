package battleship.backend;

public class Ship {
    private Cell begin;
    private Cell end;
    private int size;
    private Cell[] ship = new Cell[10];

    /**
     * constructor of the ship by starting and ending cells
     * @param begin
     * @param end
     */
    public Ship(Cell begin, Cell end) {
        this.begin = begin;
        this.end = end;
        int beginX = begin.getCoordinateX();
        int beginY = begin.getCoordinateY();
        int endX = end.getCoordinateX();
        int endY = end.getCoordinateY();

        if (beginX == endX) {
            size = Math.abs(beginY - endY) + 1;
            for (int i = Math.min(beginY, endY); i <= Math.max(beginY, endY); i++) {
                int j = i - Math.min(beginY, endY);
                ship[j] = new Cell(beginX, i);
            }
        } else {
            size = Math.abs(beginX - endX) + 1;
            for (int i = Math.min(beginX, endX); i <= Math.max(beginX, endX); i++) {
                int j = i - Math.min(beginX, endX);
                ship[j] = new Cell(i, beginY);
            }
        }
    }

    /**
     * Method that returns the size of the ship
     * @return ship size
     */
    public int getSize() {
        return size;
    }

    /**
     * method that returns the ship's begin cell
     * @return ship's begin cell
     */
    public Cell getBegin() {
        return begin;
    }
    /**
     * method that returns the ship's end cell
     * @return ship's end cell
     */
    public Cell getEnd() {
        return end;
    }

    /**
     * Method that checks if the given cell is contained in the ship
     * @param cell
     * @return true if the point is contained in the ship and false if not
     */
    public boolean cellInShip(Cell cell) {
        for (int i = 0; i < size; i++) {
            if (ship[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns the ship's cell by index
     * @param i
     * @return ship's cell by index
     */
    public Cell getCell(int i) {
        return ship[i];
    }

    /**
     * A method that determines if the ship is vertical or horizontal
     * @return true if the ship is vertical and false if the ship is horizontal
     */
    public boolean isVertical() {
        return begin.getCoordinateX() == end.getCoordinateX();
    }
}
