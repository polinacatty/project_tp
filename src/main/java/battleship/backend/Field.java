package battleship.backend;

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

    public int getSize() {
        return SIZE;
    }

    public int getCell(int x, int y) {
        return field[x][y];
    }

    public int getCell(Cell cell) {
        return field[cell.getCoordinateX()][cell.getCoordinateY()];
    }

    public void setCell(int x, int y, int n) {
        field[x][y] = n;
    }

    public void setCell(Cell cell, int n) {
        field[cell.getCoordinateX()][cell.getCoordinateY()] = n;
    }

    //Метод, при помощи которого клетки вокруг корабля обретают статус видимых-пустых
    public void setBorders(Ship ship) {
        int beginX = ship.getBegin().getCoordinateX();
        int beginY = ship.getBegin().getCoordinateY();
        int endX = ship.getEnd().getCoordinateX();
        int endY = ship.getEnd().getCoordinateY();

        if (ship.isVertical()) {
            for (int i = beginX - 1; i <= beginX + 1; i++) {
                for (int j = Math.min(beginY, endY) - 1; j <= Math.max(beginY, endY) + 1; j++) {
                    if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                        if (getCell(i, j) != 5 && getCell(i, j) != 6) {
                            setCell(i, j, 1);
                        }
                    }
                }
            }
        } else {
            for (int j = beginY - 1; j <= beginY + 1; j++) {
                for (int i = Math.min(beginX, endX) - 1; i <= Math.max(beginX, endX) + 1; i++) {
                    if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                        if (getCell(i, j) != 5 && getCell(i, j) != 6) {
                            setCell(i, j, 1);
                        }
                    }
                }
            }
        }
    }
}
