package org.example;

public class Field {
    private int size = 10;


    private int[] Count_Ships = {4, 3, 2, 1};
    private int[][] field = new int[size][size];

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

    public void Setter_size(int size) {
        this.size = size;
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

    }

}
