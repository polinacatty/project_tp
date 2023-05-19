package battleship.frontend;

import battleship.backend.Field;

public class PrintEnemyField extends PrintField{

    /**
     * Сonstructor that creates a class object by field
     * @param field
     */
    public PrintEnemyField(Field field) {
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
                if (getField().getCell(j, i) == 6) {
                    System.out.print(" !");
                }
            }
            System.out.println("");
        }
    }
}
