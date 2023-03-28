package org.example;

public class Enemy_field extends Field {

    //0(*) - еще не открытая клетка
    //1(#) - открытая клетка, пустая
    //6(!) - открытая клетка, принадлежащая кораблю

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
                if (this.Getter_cell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }

    //преобразует поле во время выстрела, возвращает true при попадании, false при промахе
    //(пишет что-то типа "какой точный выстрел!!" / "вы промахнились :(")
    //shot = true, если попадание; shot = false, если промах
    public void Shot(Cell cell, boolean shot) {
        if (shot) {
            this.Setter_cell(cell, 6);
        }
        else {
            this.Setter_cell(cell, 1);
        }
    }

}
