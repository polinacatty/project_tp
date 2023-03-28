package org.example;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Field {
    private int size = 10;


    private int[] Count_Ships = {4, 3, 2, 1};
    private int[][] field = new int[10][10];

    public Field() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = 0;
            }
        }
    }


    public int Getter_size() {
        return this.size;
    }

    public int Getter_cell(int x, int y) {
        return this.field[x][y];
    }

    public int Getter_cell(Cell cell) {
        return this.field[cell.Getter_coordinate_X()][cell.Getter_coordinate_Y()];
    }

    public void Setter_cell(int x, int y, int n) {
        this.field[x][y] = n;
    }

    public void Setter_cell(Cell cell, int n) {
        this.field[cell.Getter_coordinate_X()][cell.Getter_coordinate_Y()] = n;
    }

    public void Print_field() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 1; i <= this.size; i++) {
            if (i < 9) {
                System.out.println(" " + i + " * * * * * * * * * *");
            }
            else {
                System.out.println(i + " * * * * * * * * * *");
            }
        }
    }

    public void Borders(Ship ship) {
        Cell begin = new Cell(ship.Getter_begin());
        Cell end = new Cell(ship.Getter_end());
        if(ship.Vertical()) {
            for (int i = begin.Getter_coordinate_X() - 1; i < begin.Getter_coordinate_X() + 2; i++) {
                for (int j = min(begin.Getter_coordinate_Y(), end.Getter_coordinate_Y()) - 1; j <= max(begin.Getter_coordinate_Y(), end.Getter_coordinate_Y()) + 1; j ++) {
                    if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                        if ((this.Getter_cell(i, j) != 5) && (this.Getter_cell(i, j) != 6)) {
                            this.Setter_cell(i, j, 1);
                        }
                    }
                }
            }
        }
        else {
            for (int j = begin.Getter_coordinate_Y() - 1; j < begin.Getter_coordinate_Y() + 2; j++) {
                for (int i = min(begin.Getter_coordinate_X(), end.Getter_coordinate_X()) - 1; i <= max(begin.Getter_coordinate_X(), end.Getter_coordinate_X()) + 1; i ++) {
                    if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                        if ((this.Getter_cell(i, j) != 5) && (this.Getter_cell(i, j) != 6)) {
                            this.Setter_cell(i, j, 1);
                        }
                    }
                }
            }
        }
    }

}
