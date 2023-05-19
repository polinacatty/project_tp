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

    public ArrayList<Ship> getShips() {
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

        for (Ship i : ships) {
            if (i.getSize() == 1) {
                count1DeckShips += 1;
            }
            if (i.getSize() == 2) {
                count2DeckShips += 1;
            }
            if (i.getSize() == 3) {
                count3DeckShips += 1;
            }
            if (i.getSize() == 4) {
                count4DeckShips += 1;
            }
        }
        return (count1DeckShips == 1 && count2DeckShips == 1 && count3DeckShips == 0 && count4DeckShips == 0);
    }

    //Метод, который проверяет, попал ли игрок по кораблю противника
    private boolean hit(Cell cell, Player enemy) {
        return (enemy.myField.getCell(cell) == 5 || enemy.myField.getCell(cell) == 6);
    }

    //Метод, который определяет корабль по заданной точке
    public Ship knowShipForCell(Cell cell) {
        for (Ship i: ships) {
            if (i.cellInShip(cell)) {
                return i;
            }
        }
        return ships.get(0);
    }

    //Метод, который проверяет мертв ли корабль, содержащий заданную точку
    public boolean shipIsDead(Cell cell) {
        Ship ship = knowShipForCell(cell);
        for (int j = 0; j < ship.getSize(); j++) {
            if (myField.getCell(ship.getCell(j)) == 5) {
                return false;
            }
        }
        return true;
    }

    //Метод, который преобразует собственное поле противника и поле противника, которое отображается у данного игрока
    public boolean attack(Cell cell, Player enemy) {
        enemy.myField.takeShot(cell);
        enemyField.shot(cell, hit(cell, enemy));
        return hit(cell, enemy);
    }

    public boolean tryAddShip(Cell begin, Cell end) {
        if (myField.canAddShip(begin, end)) {
            Ship ship = new Ship(begin, end);
            myField.addShip(ship);
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
    public boolean isAlive() {
        return myField.survivorsShips();
    }
}
