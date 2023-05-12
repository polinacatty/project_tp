package org.example;

public class MyField extends Field {
    //0(*) - пустая клетка
    //1(#) - пустая клетка, в нее был выстрел противника
    //5(@) - клетка принадлежит кораблю
    //6(!) - клетка принадлежит кораблю, в нее был выстрел противника

    public MyField() { }
    
    //Метод, который добавляет корабль на поле
    public void addShip(Ship ship) {
        for (int i = 0; i < ship.getterSize(); i++) {
            this.setterCell(ship.getterCell(i), 5);
        }
    }

    //Метод, который удаляет корабль с поля
    public void deleteShip(Ship ship) {
        for (int i = 0; i < ship.getterSize(); i++) {
            this.setterCell(ship.getterCell(i), 0);
        }
    }

    @Override
    public void printField() {
        System.out.println("   A B C D E F G H I J");
        for (int i = 0; i < this.getterSize(); i++) {
            if (i < 9) {
                System.out.print(" " + (i + 1));
            } else {
                System.out.print(i + 1);
            }
            for (int j = 0; j < this.getterSize(); j++) {
                if (this.getterCell(j, i) == 0) {
                    System.out.print(" *");
                }
                if (this.getterCell(j, i) == 1) {
                    System.out.print(" #");
                }
                if (this.getterCell(j, i) == 5) {
                    System.out.print(" @");
                }
                if (this.getterCell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }

    //Метод, который преобразует поле после входящего выстрела
    public void takingShot(Cell cell) {
        if (this.getterCell(cell) == 0) {
            this.setterCell(cell, 1);
        }
        if (this.getterCell(cell) == 5) {
            this.setterCell(cell, 6);
        }
    }

    //Метод, который проверяет есть ли на поле живые корабли
    public boolean survivorsShips() {
        for (int i = 0; i < this.getterSize(); i++) {
            for (int j = 0; j < this.getterSize(); j++) {
                if (this.getterCell(i, j) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    //Метод, который проверяет, можно ли на поле поставить корабль с данными началом и концом
    public boolean canAddShip(Cell cell1, Cell cell2) {
        int cell1X = cell1.getterCoordinateX();
        int cell1Y = cell1.getterCoordinateY();
        int cell2X = cell2.getterCoordinateX();
        int cell2Y = cell2.getterCoordinateY();

        if (cell1.cellsOnOneLine(cell2)) {
            Ship ship = new Ship(cell1, cell2);
            if (ship.getterSize() < 5) {
                if (ship.vertical()) {
                    for (int i = cell1X - 1; i <= cell1X + 1; i++) {
                        for (int j = Math.min(cell1Y, cell2Y) - 1; j <= Math.max(cell1Y, cell2Y) + 1; j++) {
                            if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                                if (this.getterCell(i, j) != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                } else {
                    for (int j = cell1Y - 1; j < cell1Y + 2; j++) {
                        for (int i = Math.min(cell1X, cell2X) - 1; i <= Math.max(cell1X, cell2X) + 1; i++) {
                            if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                                if (this.getterCell(i, j) != 0) {
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
        if (this.getterCell(anyCell) == 5) {
            return true;
        }
        return false;
    }
}
