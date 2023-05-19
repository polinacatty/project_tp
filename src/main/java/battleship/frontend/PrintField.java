package battleship.frontend;

import battleship.backend.Field;

public class PrintField {
    private Field field;

    public Field getField() {
        return field;
    }

    public PrintField(Field anyfield) {
        field = anyfield;
    }

    public void print() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 1; i <= field.SIZE; i++) {
            if (i < 9) {
                System.out.println(" " + i + " * * * * * * * * * *");
            } else {
                System.out.println(i + " * * * * * * * * * *");
            }
        }
    }
}
