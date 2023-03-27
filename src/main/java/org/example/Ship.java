package org.example;

import static java.lang.Math.*;

public class Ship {
    private Cell begin;
    private Cell end;
    private boolean state;
    private int size;
    private Cell[] ship = new Cell[size];

    public Ship(Cell begin, Cell end) {
        this.begin = begin;
        this.end = end;
        this.state = true;
        if (begin.Getter_coordinate_X() == end.Getter_coordinate_X()) {
            this.size = abs(begin.Getter_coordinate_Y() - end.Getter_coordinate_Y());
            for (int i = min(begin.Getter_coordinate_Y(), end.Getter_coordinate_Y()); i <= max(begin.Getter_coordinate_Y(), end.Getter_coordinate_Y()); i++) {
                int j = i - min(begin.Getter_coordinate_Y(), end.Getter_coordinate_Y());
                this.ship[j] = new Cell(begin.Getter_coordinate_X(), i);
            }
        }
        else {
            this.size = abs(begin.Getter_coordinate_X() - end.Getter_coordinate_X());
            for (int i = min(begin.Getter_coordinate_X(), end.Getter_coordinate_X()); i <= max(begin.Getter_coordinate_X(), end.Getter_coordinate_X()); i++) {
                int j = i - min(begin.Getter_coordinate_X(), end.Getter_coordinate_X());
                this.ship[j] = new Cell(i, begin.Getter_coordinate_Y());
            }
        }
    }

    public int Getter_size() {
        return this.size;
    }

    public Cell Getter_begin() {
        return this.begin;
    }

    public Cell Getter_end() {
        return this.end;
    }

    public boolean State() {
        return this.state;
    }

    public void Return_state___() {
        this.state = false;
    }

    public boolean Cell_in_ship(Cell cell) {
        for (int i = 0; i < this.size; i++) {
            if (this.ship[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    public Cell Getter_cell(int i) {
        return this.ship[i];
    }

}