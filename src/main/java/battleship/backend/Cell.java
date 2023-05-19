package battleship.backend;

public class Cell {
    private int coordinateX;
    private int coordinateY;

    public Cell(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Cell(Cell cell) {
        this.coordinateX = cell.coordinateX;
        this.coordinateY = cell.coordinateY;
    }

    //Конструктор, с помощью которого создается клетка от строки вида 'буква''цифра', например: 'a1'
    public Cell(String input) {
        int number;
        if (input.length() == 3) {
            number = 10;
        } else {
            number = Character.digit(input.charAt(1), 10);
        }
        this.coordinateY = number - 1;
        char letter = input.charAt(0);
        int code = (int) letter;
        if (code < 80) {
            this.coordinateY = code - 65;
        } else {
            this.coordinateX = code - 97;
        }
    }

    public int getCoordinateX() {
        return this.coordinateX;
    }

    public int getCoordinateY() {
        return this.coordinateY;
    }

    public void setCoordinateX(int x) {
        this.coordinateX = x;
    }

    public void setCoordinateY(int y) {
        this.coordinateY = y;
    }

    public boolean equals(Cell cell) {
        if (this.coordinateX == cell.coordinateX && this.coordinateY == cell.coordinateY) {
            return true;
        }
        return false;
    }

    //Метод, который проверяет, стоят ли клетки на одной линии
    public boolean cellsOnOneLine(Cell cell) {
        if (this.coordinateX == cell.coordinateX || this.coordinateY == cell.coordinateY) {
            return true;
        }
        return false;
    }
}
