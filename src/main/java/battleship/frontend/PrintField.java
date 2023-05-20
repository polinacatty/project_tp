package battleship.frontend;

import battleship.backend.Field;

public class PrintField {
    private Field field;

    /**
     * Сonstructor that creates a class object by field
     * @param anyField
     */
    public PrintField(Field anyField) {
        field = anyField;
    }

    /**
     * Method that returns a field
     * @return field
     */
    public Field getField() {
        return field;
    }

    /**
     * Method that prints the field
     */
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
