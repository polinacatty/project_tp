package org.example;

public class Cell {
    private int coordinate_X;
    private int coordinate_Y;

    public Cell(int coordinate_X, int coordinate_Y) {
        this.coordinate_X = coordinate_X;
        this.coordinate_Y = coordinate_Y;
    }

    public Cell(Cell cell) {
        this.coordinate_X = cell.coordinate_X;
        this.coordinate_Y = cell.coordinate_Y;
    }

    public Cell(String input) {
        int number = Character.digit(input.charAt(1), 10);
        this.coordinate_Y = number - 1;
        char letter = input.charAt(0);
        int code = (int) letter;
        if (code < 80) {
            this.coordinate_X = code - 65;
        }
        else {
            this.coordinate_X = code - 97;
        }
    }

    public int Getter_coordinate_X() {
        return this.coordinate_X;
    }

    public int Getter_coordinate_Y() {
        return this.coordinate_Y;
    }

    public void Setter_coordinate_X(int x) {
        this.coordinate_X = x;
    }

    public void Setter_coordinate_Y(int y) {
        this.coordinate_Y = y;
    }

    //@Override
    public boolean equals(Cell cell) {
        if ((this.coordinate_X == cell.coordinate_X) && (this.coordinate_Y == cell.coordinate_Y)) {
            return true;
        }
        return false;
    }
}

