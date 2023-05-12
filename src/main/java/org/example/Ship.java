package org.example;

//import static java.lang.Math.Math.max;
//import static java.lang.Math.Math.min;
//import static java.lang.Math.Math.abs;

public class Ship {
    private Cell begin;
    private Cell end;
    private int size;
    private Cell[] ship = new Cell[10];

    public Ship(Cell begin, Cell end) {
        this.begin = begin;
        this.end = end;
        int beginX = begin.getterCoordinateX();
        int beginY = begin.getterCoordinateY();
        int endX = end.getterCoordinateX();
        int endY = end.getterCoordinateY();

        if (beginX == endX) {
            this.size = Math.abs(beginY - endY) + 1;
            for (int i = Math.min(beginY, endY); i <= Math.max(beginY, endY); i++) {
                int j = i - Math.min(beginY, endY);
                this.ship[j] = new Cell(beginX, i);
            }
        } else {
            this.size = Math.abs(beginX - endX) + 1;
            for (int i = Math.min(beginX, endX); i <= Math.max(beginX, endX); i++) {
                int j = i - Math.min(beginX, endX);
                this.ship[j] = new Cell(i, beginY);
            }
        }
    }

    public int getterSize() {
        return this.size;
    }

    public Cell getterBegin() {
        return this.begin;
    }

    public Cell getterEnd() {
        return this.end;
    }

    //Метод, который проверяет, содержится ли данная клетка в корабле
    public boolean cellInShip(Cell cell) {
        for (int i = 0; i < this.size; i++) {
            if (this.ship[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    //Метод, который возвращает клетку корабля по индексу
    public Cell getterCell(int i) {
        return this.ship[i];
    }

    //Метод, который определяет вертикальный корабль или горизонтальный.
    //Возвращает true, если корабль вертикальный, false - если горизонтальный
    public boolean vertical() {
        if (this.begin.getterCoordinateX() == this.end.getterCoordinateX()) {
            return true;
        }
        return false;
    }
}
