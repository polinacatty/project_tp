package battleship.backend;

public class EnemyField extends Field {

    public EnemyField() {
        super();
    }

    /**
     * Method that transforms the field after the shot
     * @param cell
     * @param shot
     */
    public void shot(Cell cell, boolean shot) {
        if (shot) {
            setCell(cell, 6);
        } else {
            setCell(cell, 1);
        }
    }
}
