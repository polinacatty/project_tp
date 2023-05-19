package battleship.backend;

public class Cell {
    private int coordinateX;
    private int coordinateY;

    public Cell(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Cell(Cell cell) {
        coordinateX = cell.coordinateX;
        coordinateY = cell.coordinateY;
    }

    //Конструктор, с помощью которого создается клетка от строки вида 'буква''цифра', например: 'a1'
    public Cell(String input) {
        int number;
        if (input.length() == 3) {
            number = 10;
        } else {
            number = Character.digit(input.charAt(1), 10);
        }
        coordinateY = number - 1;
        char letter = input.charAt(0);
        int code = (int) letter;
        if (code < 80) {
            coordinateY = code - 65;
        } else {
            coordinateX = code - 97;
        }
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int x) {
        coordinateX = x;
    }

    public void setCoordinateY(int y) {
        coordinateY = y;
    }

    public boolean equals(Cell cell) {
        return (coordinateX == cell.coordinateX && coordinateY == cell.coordinateY);
    }

    //Метод, который проверяет, стоят ли клетки на одной линии
    public boolean isCellsOnOneLine(Cell cell) {
        return (coordinateX == cell.coordinateX || coordinateY == cell.coordinateY);
    }
}
