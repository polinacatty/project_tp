package battleship.backend;

import java.util.ArrayList;

public class Player {
    private String name;
    private MyField myField = new MyField();
    private EnemyField enemyField = new EnemyField();
    private ArrayList<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public MyField getMyField() {
        return myField;
    }

    public ArrayList<Ship> getterShips() {
        return ships;
    }

    public EnemyField getEnemyField() {
        return enemyField;
    }

    //Метод, который проверяет все ли корабли расставлены
    public boolean allShipsArePlaced() {
        int count1DeckShips = 0;
        int count2DeckShips = 0;
        int count3DeckShips = 0;
        int count4DeckShips = 0;

        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).getSize() == 1) {
                count1DeckShips += 1;
            }
            if (this.ships.get(i).getSize() == 2) {
                count2DeckShips += 1;
            }
            if (this.ships.get(i).getSize() == 3) {
                count3DeckShips += 1;
            }
            if (this.ships.get(i).getSize() == 4) {
                count4DeckShips += 1;
            }
        }

        if (count1DeckShips == 1 && count2DeckShips == 1 && count3DeckShips == 0 && count4DeckShips == 0) {
            return true;
        }
        return false;
    }

    //Метод, который проверяет, попал ли игрок по кораблю противника
    private boolean hit(Cell cell, Player enemy) {
        if (enemy.myField.getCell(cell) == 0 || enemy.myField.getCell(cell) == 1) {
            return false;
        }
        return true;
    }

    //Метод, который определяет корабль по заданной точке
    public Ship shipForCell(Cell cell) {
        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).cellInShip(cell)) {
                return this.ships.get(i);
            }
        }
        Cell cell0 = new Cell(0, 0);
        Ship ship = new Ship(cell0, cell0);
        return ship;
    }

    //Метод, который проверяет мертв ли корабль, содержащий заданную точку
    public boolean shipIsDead(Cell cell) {
        Ship ship = this.shipForCell(cell);
        for (int j = 0; j < ship.getSize(); j++) {
            if (this.myField.getCell(ship.getCell(j)) == 5) {
                return false;
            }
        }
        return true;
    }

    //Метод, который преобразует собственное поле противника и поле противника, которое отображается у данного игрока
    public boolean attack(Cell cell, Player enemy) {
        enemy.myField.takingShot(cell);
        this.enemyField.shot(cell, this.hit(cell, enemy));
        return this.hit(cell, enemy);
    }

    public boolean tryAddShip(Cell begin, Cell end) {
        if (this.myField.canAddShip(begin, end)) {
            Ship ship = new Ship(begin, end);
            this.myField.addShip(ship);
            ships.add(ship);
            return true;
        }
        return false;
    }

    public boolean tryDeleteShip(Cell anyCell) {
        if (this.myField.canDeleteShip(anyCell)) {
            for (int i = 0; i < this.ships.size(); i++) {
                if (this.ships.get(i).cellInShip(anyCell)) {
                    this.myField.deleteShip(this.ships.get(i));
                    this.ships.remove(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    //Метод, который проверяет жив ли игрок
    public boolean isLife() {
        return this.myField.survivorsShips();
    }
}
