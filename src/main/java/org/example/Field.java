package org.example;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Field {
    public static final int SIZE = 10;
    private int[][] field = new int[SIZE][SIZE];

    public Field() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = 0;
            }
        }
    }

    public int GetterSize() {
        return this.SIZE;
    }

    public int GetterCell(int x, int y) {
        return this.field[x][y];
    }

    public int GetterCell(Cell cell) {
        return this.field[cell.GetterCoordinateX()][cell.GetterCoordinateY()];
    }

    public void SetterCell(int x, int y, int n) {
        this.field[x][y] = n;
    }

    public void SetterCell(Cell cell, int n) {
        this.field[cell.GetterCoordinateX()][cell.GetterCoordinateY()] = n;
    }

    public void PrintField() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 1; i <= this.SIZE; i++) {
            if (i < 9) {
                System.out.println(" " + i + " * * * * * * * * * *");
            }
            else {
                System.out.println(i + " * * * * * * * * * *");
            }
        }
    }

    //Метод, при помощи которого клетки вокруг корабля обретают статус видимых-пустых
    public void Borders(Ship ship) {
        int begin_x = ship.GetterBegin().GetterCoordinateX();
        int begin_y = ship.GetterBegin().GetterCoordinateY();
        int end_x = ship.GetterEnd().GetterCoordinateX();
        int end_y = ship.GetterEnd().GetterCoordinateY();

        if(ship.Vertical()) {
            for (int i = begin_x - 1; i <= begin_x + 1; i++) {
                for (int j = min(begin_y, end_y) - 1; j <= max(begin_y, end_y) + 1; j ++) {
                    if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                        if ((this.GetterCell(i, j) != 5) && (this.GetterCell(i, j) != 6)) {
                            this.SetterCell(i, j, 1);
                        }
                    }
                }
            }
        }
        else {
            for (int j = begin_y - 1; j <= begin_y + 1; j++) {
                for (int i = min(begin_x, end_x) - 1; i <= max(begin_x, end_x) + 1; i ++) {
                    if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                        if ((this.GetterCell(i, j) != 5) && (this.GetterCell(i, j) != 6)) {
                            this.SetterCell(i, j, 1);
                        }
                    }
                }
            }
        }
    }
}
