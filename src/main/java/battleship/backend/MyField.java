package battleship.backend;

public class MyField extends Field {
    //0(*) - пустая клетка
    //1(#) - пустая клетка, в нее был выстрел противника
    //5(@) - клетка принадлежит кораблю
    //6(!) - клетка принадлежит кораблю, в нее был выстрел противника

    public MyField() {
        super();
    }

    //Метод, который добавляет корабль на поле
    public void addShip(Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            setCell(ship.getCell(i), 5);
        }
    }

    //Метод, который удаляет корабль с поля
    public void deleteShip(Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            setCell(ship.getCell(i), 0);
        }
    }

    //Метод, который преобразует поле после входящего выстрела
    public void takeShot(Cell cell) {
        if (getCell(cell) == 0) {
            setCell(cell, 1);
        }
        if (getCell(cell) == 5) {
            setCell(cell, 6);
        }
    }

    //Метод, который проверяет есть ли на поле живые корабли
    public boolean survivorsShips() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (getCell(i, j) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    //Метод, который проверяет, можно ли на поле поставить корабль с данными началом и концом
    public boolean canAddShip(Cell cell1, Cell cell2) {
        int cell1X = cell1.getCoordinateX();
        int cell1Y = cell1.getCoordinateY();
        int cell2X = cell2.getCoordinateX();
        int cell2Y = cell2.getCoordinateY();

        if (cell1.isCellsOnOneLine(cell2)) {
            Ship ship = new Ship(cell1, cell2);
            if (ship.getSize() < 5) {
                if (ship.isVertical()) {
                    for (int i = cell1X - 1; i <= cell1X + 1; i++) {
                        for (int j = Math.min(cell1Y, cell2Y) - 1; j <= Math.max(cell1Y, cell2Y) + 1; j++) {
                            if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                                if (getCell(i, j) != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                } else {
                    for (int j = cell1Y - 1; j <= cell1Y + 1; j++) {
                        for (int i = Math.min(cell1X, cell2X) - 1; i <= Math.max(cell1X, cell2X) + 1; i++) {
                            if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                                if (getCell(i, j) != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //Метод, который проверяет, можно ли удалить корабль по заданной точке
    public boolean canDeleteShip(Cell anyCell) {
        return (getCell(anyCell) == 5);
    }
}
