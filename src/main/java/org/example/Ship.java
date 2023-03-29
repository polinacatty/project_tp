package org.example;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Ship {
    private Cell begin;
    private Cell end;
    private int size;
    private Cell[] ship = new Cell[10];

    public Ship(Cell begin, Cell end) {
        this.begin = begin;
        this.end = end;
        int begin_x = begin.GetterCoordinateX();
        int begin_y = begin.GetterCoordinateY();
        int end_x = end.GetterCoordinateX();
        int end_y = end.GetterCoordinateY();

        if (begin_x == end_x) {
            this.size = abs(begin_y - end_y) + 1;
            for (int i = min(begin_y, end_y); i <= max(begin_y, end_y); i++) {
                int j = i - min(begin_y, end_y);
                this.ship[j] = new Cell(begin_x, i);
            }
        }
        else {
            this.size = abs(begin_x - end_x) + 1;
            for (int i = min(begin_x, end_x); i <= max(begin_x, end_x); i++) {
                int j = i - min(begin_x, end_x);
                this.ship[j] = new Cell(i, begin_y);
            }
        }
    }

    public int GetterSize() {
        return this.size;
    }

    public Cell GetterBegin() {
        return this.begin;
    }

    public Cell GetterEnd() {
        return this.end;
    }

    //Метод, который проверяет, содержится ли данная клетка в корабле
    public boolean CellInShip(Cell cell) {
        for (int i = 0; i < this.size; i++) {
            if (this.ship[i].Equals(cell)) {
                return true;
            }
        }
        return false;
    }

    //Метод, который возвращает клетку корабля по индексу
    public Cell GetterCell(int i) {
        return this.ship[i];
    }

    //Метод, который определяет вертикальный корабль или горизонтальный.
    //Возвращает true, если корабль вертикальный, false - если горизонтальный
    public boolean Vertical() {
        if (this.begin.GetterCoordinateX() == this.end.GetterCoordinateX()) {
            return true;
        }
        return false;
    }
}
