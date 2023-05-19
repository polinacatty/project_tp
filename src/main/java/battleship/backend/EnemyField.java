package battleship.backend;

public class EnemyField extends Field {
    //0(*) - еще не открытая клетка
    //1(#) - открытая клетка, пустая
    //6(!) - открытая клетка, принадлежащая кораблю

    public EnemyField() {
        super();
    }

    //Метод, который преобразует поле после выстрела
    public void shot(Cell cell, boolean shot) {
        if (shot) {
            this.setCell(cell, 6);
        } else {
            this.setCell(cell, 1);
        }
    }
}
