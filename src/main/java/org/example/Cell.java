package org.example;

public class Cell {
    private int coordinate_x;
    private int coordinate_y;

    public Cell(int coordinateX, int coordinateY) {
        this.coordinate_x = coordinateX;
        this.coordinate_y = coordinateY;
    }

    public Cell(Cell cell) {
        this.coordinate_x = cell.coordinate_x;
        this.coordinate_y = cell.coordinate_y;
    }

    //Конструктор, с помощью которого создается клетка от строки вида 'буква''цифра', например: 'a1'
    public Cell(String input) {
        int number;
        if (input.length() == 3) {
            number = 10;
        }
        else {
            number = Character.digit(input.charAt(1), 10);
        }
        this.coordinate_y = number - 1;
        char letter = input.charAt(0);
        int code = (int) letter;
        if (code < 80) {
            this.coordinate_y = code - 65;
        }
        else {
            this.coordinate_x = code - 97;
        }
    }

    public int GetterCoordinateX() {
        return this.coordinate_x;
    }

    public int GetterCoordinateY() {
        return this.coordinate_y;
    }

    public void SetterCoordinateX(int x) {
        this.coordinate_x = x;
    }

    public void SetterCoordinateY(int y) {
        this.coordinate_y = y;
    }

    public boolean Equals(Cell cell) {
        if ((this.coordinate_x == cell.coordinate_x) && (this.coordinate_y == cell.coordinate_y)) {
            return true;
        }
        return false;
    }

    //Метод, который проверяет, стоят ли клетки на одной линии
    public boolean CellsOnOneLine(Cell cell) {
        if ((this.coordinate_x == cell.coordinate_x) || (this.coordinate_y == cell.coordinate_y)) {
            return true;
        }
        return false;
    }
}
