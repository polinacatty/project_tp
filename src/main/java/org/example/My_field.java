package org.example;

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

    //считает количество кораблей заданного размера
    public int Count_ships_of_size(int size) {
        return 0;
    }
    @Override
    public void Print_field() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 1; i <= this.Getter_size(); i ++) {
            System.out.println(i);
            for (int j = 0; j < this.Getter_size(); j ++) {
                if (this.Getter_cell(j, i) == 0) {
                    System.out.println(" *");
                }
                if (this.Getter_cell(j, i) == 1) {
                    System.out.println(" #");
                }
                if (this.Getter_cell(j, i) == 5) {
                    System.out.println(" @");
                }
                if (this.Getter_cell(j, i) == 6) {
                    System.out.println(" !");
                }
            }
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



}
