package org.example;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MyField extends Field {
    //0(*) - пустая клетка
    //1(#) - пустая клетка, в нее был выстрел противника
    //5(@) - клетка принадлежит кораблю
    //6(!) - клетка принадлежит кораблю, в нее был выстрел противника

    //Метод, который добавляет корабль на поле
    public void AddShip(Ship ship) {
        for (int i = 0; i < ship.GetterSize(); i++) {
            this.SetterCell(ship.GetterCell(i), 5);
        }
    }

    //Метод, который удаляет корабль с поля
    public void DeleteShip(Ship ship) {
        for (int i = 0; i < ship.GetterSize(); i++) {
            this.SetterCell(ship.GetterCell(i), 0);
        }
    }

    @Override
    public void PrintField() {
        System.out.println("   A B C D E F G H I J");
        for (int i = 0; i < this.GetterSize(); i ++) {
            if (i < 9) {
                System.out.print(" " + (i+1));
            }
            else {
                System.out.print(i+1);
            }
            for (int j = 0; j < this.GetterSize(); j ++) {
                if (this.GetterCell(j, i) == 0) {
                    System.out.print(" *");
                }
                if (this.GetterCell(j, i) == 1) {
                    System.out.print(" #");
                }
                if (this.GetterCell(j, i) == 5) {
                    System.out.print(" @");
                }
                if (this.GetterCell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }

    //Метод, который преобразует поле после входящего выстрела
    public void TakingShot(Cell cell) {
        if (this.GetterCell(cell) == 0) {
            this.SetterCell(cell, 1);
        }
        if (this.GetterCell(cell) == 5) {
            this.SetterCell(cell, 6);
        }
    }

    //Метод, который проверяет есть ли на поле живые корабли
    public boolean SurvivorsShips() {
        for (int i = 0; i < this.GetterSize(); i++) {
            for (int j = 0; j < this.GetterSize(); j++) {
                if (this.GetterCell(i, j) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    //Метод, который проверяет, можно ли на поле поставить корабль с данными началом и концом
    public boolean CanAddShip(Cell cell1, Cell cell2) {
        int cell1_x = cell1.GetterCoordinateX();
        int cell1_y = cell1.GetterCoordinateY();
        int cell2_x = cell2.GetterCoordinateX();
        int cell2_y = cell2.GetterCoordinateY();

        if (cell1.CellsOnOneLine(cell2)) {
            Ship ship = new Ship(cell1, cell2);
            if (ship.GetterSize() <5) {
                if (ship.Vertical()) {
                    for (int i = cell1_x - 1; i <= cell1_x + 1; i++) {
                        for (int j = min(cell1_y, cell2_y) - 1; j <= max(cell1_y, cell2_y) + 1; j ++) {
                            if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                                if (this.GetterCell(i, j) != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
                else {
                    for (int j = cell1_y - 1; j < cell1_y + 2; j++) {
                        for (int i = min(cell1_x, cell2_x) - 1; i <= max(cell1_x, cell2_x) + 1; i ++) {
                            if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                                if (this.GetterCell(i, j) != 0) {
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
    public boolean CanDeleteShip(Cell anyCell) {
        if (this.GetterCell(anyCell) == 5) {
            return true;
        }
        return false;
    }
}
