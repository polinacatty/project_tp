package org.example;

public class EnemyField extends Field {

    //0(*) - еще не открытая клетка
    //1(#) - открытая клетка, пустая
    //6(!) - открытая клетка, принадлежащая кораблю

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
                if (this.GetterCell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }

    //Метод, который преобразует поле после выстрела
    public void Shot(Cell cell, boolean shot) {
        if (shot) {
            this.SetterCell(cell, 6);
        }
        else {
            this.SetterCell(cell, 1);
        }
    }
}
