package org.example;

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

    public int getterSize() {
        return this.SIZE;
    }

    public int getterCell(int x, int y) {
        return this.field[x][y];
    }

    public int getterCell(Cell cell) {
        return this.field[cell.getterCoordinateX()][cell.getterCoordinateY()];
    }

    public void setterCell(int x, int y, int n) {
        this.field[x][y] = n;
    }

    public void setterCell(Cell cell, int n) {
        this.field[cell.getterCoordinateX()][cell.getterCoordinateY()] = n;
    }

    public void printField() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 1; i <= this.SIZE; i++) {
            if (i < 9) {
                System.out.println(" " + i + " * * * * * * * * * *");
            } else {
                System.out.println(i + " * * * * * * * * * *");
            }
        }
    }

    //Метод, при помощи которого клетки вокруг корабля обретают статус видимых-пустых
    public void borders(Ship ship) {
        int beginX = ship.getterBegin().getterCoordinateX();
        int beginY = ship.getterBegin().getterCoordinateY();
        int endX = ship.getterEnd().getterCoordinateX();
        int endY = ship.getterEnd().getterCoordinateY();

        if (ship.vertical()) {
            for (int i = beginX - 1; i <= beginX + 1; i++) {
                for (int j = Math.min(beginY, endY) - 1; j <= Math.max(beginY, endY) + 1; j++) {
                    if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                        if (this.getterCell(i, j) != 5 && this.getterCell(i, j) != 6) {
                            this.setterCell(i, j, 1);
                        }
                    }
                }
            }
        } else {
            for (int j = beginY - 1; j <= beginY + 1; j++) {
                for (int i = Math.min(beginX, endX) - 1; i <= Math.max(beginX, endX) + 1; i++) {
                    if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                        if (this.getterCell(i, j) != 5 && this.getterCell(i, j) != 6) {
                            this.setterCell(i, j, 1);
                        }
                    }
                }
            }
        }
    }
}
