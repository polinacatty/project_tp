package battleship.frontend;

import battleship.backend.Field;

public class PrintMyField extends PrintField{
    //0(*) - пустая клетка
    //1(#) - пустая клетка, в нее был выстрел противника
    //5(@) - клетка принадлежит кораблю
    //6(!) - клетка принадлежит кораблю, в нее был выстрел противника

    public PrintMyField(Field field) {
        super(field);
    }

    @Override
    public void print() {
        System.out.println("   A B C D E F G H I J");
        for (int i = 0; i < getField().getSize(); i++) {
            if (i < 9) {
                System.out.print(" " + (i + 1));
            } else {
                System.out.print(i + 1);
            }
            for (int j = 0; j < getField().getSize(); j++) {
                if (getField().getCell(j, i) == 0) {
                    System.out.print(" *");
                }
                if (getField().getCell(j, i) == 1) {
                    System.out.print(" #");
                }
                if (getField().getCell(j, i) == 5) {
                    System.out.print(" @");
                }
                if (getField().getCell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }
}
