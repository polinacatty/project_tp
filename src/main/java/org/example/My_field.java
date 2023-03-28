package org.example;

import static java.lang.Math.*;

public class My_field extends Field {
    //0(*) - пустая клетка
    //1(#) - пустая клетка, в нее был выстрел противника
    //5(@) - клетка принадлежит кораблю
    //6(!) - клетка принадлежит кораблю, в нее был выстрел противника


    public void Add_ship(Ship ship) {
        for (int i = 0; i < ship.Getter_size(); i++) {
            this.Setter_cell(ship.Getter_cell(i), 5);
        }
    }

    public void Delete_ship(Ship ship) {
        for (int i = 0; i < ship.Getter_size(); i++) {
            this.Setter_cell(ship.Getter_cell(i), 0);
        }
    }

    @Override
    public void Print_field() {
        System.out.println("   A B C D E F G H I J");
        for (int i = 0; i < this.Getter_size(); i ++) {
            if (i < 9) {
                System.out.print(" " + (i+1));
            }
            else {
                System.out.print(i+1);
            }
            for (int j = 0; j < this.Getter_size(); j ++) {
                if (this.Getter_cell(j, i) == 0) {
                    System.out.print(" *");
                }
                if (this.Getter_cell(j, i) == 1) {
                    System.out.print(" #");
                }
                if (this.Getter_cell(j, i) == 5) {
                    System.out.print(" @");
                }
                if (this.Getter_cell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }

    //преобразует поле во время входящего выстрела
    //(пишет что-то типа "в вас попали" / "противник промахнулся")
    public void Taking_shot(Cell cell) {
        if (this.Getter_cell(cell) == 0) {
            this.Setter_cell(cell, 1);
        }
        if (this.Getter_cell(cell) == 5) {
            this.Setter_cell(cell, 6);
        }
    }

    //проверяет есть ли живые корабли
    public boolean Survivors_ships() {

        for (int i = 0; i < this.Getter_size(); i++) {
            for (int j = 0; j < this.Getter_size(); j++) {
                if (this.Getter_cell(i, j) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Can_add_ship(Cell cell_1, Cell cell_2) {
        if (cell_1.Cells_on_one_line(cell_2)) {
            Ship ship = new Ship(cell_1, cell_2);
            if (ship.Getter_size() <5) {
                if(ship.Vertical()) {
                    for (int i = cell_1.Getter_coordinate_X() - 1; i < cell_1.Getter_coordinate_X() + 2; i++) {
                        for (int j = min(cell_1.Getter_coordinate_Y(), cell_2.Getter_coordinate_Y()) - 1; j <= max(cell_1.Getter_coordinate_Y(), cell_2.Getter_coordinate_Y()) + 1; j ++) {
                            if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                                if (this.Getter_cell(i, j) != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
                else {
                    for (int j = cell_1.Getter_coordinate_Y() - 1; j < cell_1.Getter_coordinate_Y() + 2; j++) {
                        for (int i = min(cell_1.Getter_coordinate_X(), cell_2.Getter_coordinate_X()) - 1; i <= max(cell_1.Getter_coordinate_X(), cell_2.Getter_coordinate_X()) + 1; i ++) {
                            if ((i>=0) && (j>=0) && (i<10) && (j<10)) {
                                if (this.Getter_cell(i, j) != 0) {
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

    public boolean Can_delete_ship(Cell any_cell) {
        if (this.Getter_cell(any_cell) == 5) {
            return true;
        }
        return false;
    }

}
