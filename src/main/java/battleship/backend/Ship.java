package battleship.backend;

public class Ship {
    private Cell begin;
    private Cell end;
    private int size;
    private Cell[] ship = new Cell[10];

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

    public int getSize() {
        return size;
    }

    public Cell getBegin() {
        return begin;
    }

    public Cell getEnd() {
        return end;
    }

    //Метод, который проверяет, содержится ли данная клетка в корабле
    public boolean cellInShip(Cell cell) {
        for (int i = 0; i < size; i++) {
            if (ship[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    //Метод, который возвращает клетку корабля по индексу
    public Cell getCell(int i) {
        return ship[i];
    }

    //Метод, который определяет вертикальный корабль или горизонтальный.
    //Возвращает true, если корабль вертикальный, false - если горизонтальный
    public boolean isVertical() {
        return begin.getCoordinateX() == end.getCoordinateX();
    }
}
