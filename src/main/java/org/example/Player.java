package org.example;

import java.util.ArrayList;

public class Player {
    private String name;
    private MyField myField = new MyField();
    private EnemyField enemyField = new EnemyField();
    private ArrayList<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getterName() {
        return name;
    }
    
    public MyField getterMyField() {
        return myField;
    }
    
    public ArrayList<Ship> getterShips() {
        return ships;
    }

    public EnemyField getterEnemyField() {
        return enemyField;
    }
    
    //Метод, который проверяет все ли корабли расставлены
    private boolean allShipsArePlaced() {
        int count1DeckShips = 0;
        int count2DeckShips = 0;
        int count3DeckShips = 0;
        int count4DeckShips = 0;

        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).getterSize() == 1) {
                count1DeckShips += 1;
            }
            if (this.ships.get(i).getterSize() == 2) {
                count2DeckShips += 1;
            }
            if (this.ships.get(i).getterSize() == 3) {
                count3DeckShips += 1;
            }
            if (this.ships.get(i).getterSize() == 4) {
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
        if (enemy.myField.getterCell(cell) == 0 || enemy.myField.getterCell(cell) == 1) {
            return false;
        }
        return true;
    }

    //Метод, который определяет корабль по заданной точке
    private Ship shipForCell(Cell cell) {
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
    private boolean shipIsDead(Cell cell) {
        Ship ship = this.shipForCell(cell);
        for (int j = 0; j < ship.getterSize(); j++) {
            if (this.myField.getterCell(ship.getterCell(j)) == 5) {
                return false;
            }
        }
        return true;
    }

    //Метод, который преобразует собственное поле противника и поле противника, которое отображается у данного игрока
    private boolean attack(Cell cell, Player enemy) {
        enemy.myField.takingShot(cell);
        this.enemyField.shot(cell, this.hit(cell, enemy));
        return this.hit(cell, enemy);
    }

    //Ход данного игрока
    public void turn(Player enemy) {
        System.out.println(this.name + ", your turn");
        System.out.println("Your field:");
        this.myField.printField();
        System.out.println("Enemy field:");
        this.enemyField.printField();
        System.out.println("Please enter coordinates of the cell where you want to shot to:");
        Cell cell = new Cell(this.tryInputCoordinate());
        if (this.attack(cell, enemy)) {
            if (enemy.shipIsDead(cell)) {
                System.out.println("Super!!! You hit the target, ship is dead");
                this.enemyField.borders(enemy.shipForCell(cell));
            } else {
                System.out.println("Super!!! You hit the target, but ship is not dead");
            }
            if (enemy.isLife()) {
                this.turn(enemy);
            }
        } else {
            System.out.println("You missed :(");
        }
    }

    //Метод, который обрабатывает исключение неверного ввода координаты
    private Cell tryInputCoordinate() {
        String input = Game.scanner.next();
        if (this.checkInputCell(input)) {
            Cell cell = new Cell(input);
            return cell;
        } else {
            System.out.println("wrong input format, please, try again:");
            return this.tryInputCoordinate();
        }
    }

    //Метод, который обрабатывает исключения неверной постановки корабля
    private void tryAddShip() {
        System.out.println("Enter the coordinate of the beginning of the ship:");
        Cell begin = new Cell(this.tryInputCoordinate());
        System.out.println("Enter the coordinate of the end of the ship:");
        Cell end = new Cell(this.tryInputCoordinate());
        if (this.myField.canAddShip(begin, end)) {
            Ship ship = new Ship(begin, end);
            this.myField.addShip(ship);
            ships.add(ship);
            System.out.println("You have successfully added a ship!");
        } else {
            System.out.println("You can`t add this ship, please, try again");
            this.tryAddShip();
        }
    }

    //Метод, который обрабатывает исключение неверного удаления корабля
    private void tryDeleteShip() {
        System.out.println("Enter the coordinate of any cell of the ship:");
        Cell anyCell = new Cell(this.tryInputCoordinate());
        if (this.myField.canDeleteShip(anyCell)) {
            for (int i = 0; i < this.ships.size(); i++) {
                if (this.ships.get(i).cellInShip(anyCell)) {
                    this.myField.deleteShip(this.ships.get(i));
                    this.ships.remove(i);
                    break;
                }
            }
            System.out.println("You have successfully deleted a ship!");
        } else {
            System.out.println("You can`t delete this ship, please, try again");
            this.tryDeleteShip();
        }
    }

    //Расстановка кораблей данного игрока
    public void placementShips() {

        System.out.println("Hello, " + this.name + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format:"
                + " 'letter''number', for example 'b5' or 'A2'");

        while (true) {

            this.myField.printField();

            if (this.allShipsArePlaced()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                if (Game.scanner.next().equals("yes")) {
                    break;
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input = Game.scanner.next();
            if (input.equals("add")) {
                this.tryAddShip();
            } else {
                if (input.equals("del")) {
                    this.tryDeleteShip();
                } else {
                    System.out.println("wrong input format, please, try again");
                }
            }
        }
    }

    //Метод, который проверяет жив ли игрок
    public boolean isLife() {
        return this.myField.survivorsShips();
    }

    //Метод, который проверяет правильно ли введена координата
    private boolean checkInputCell(String input) {
        if (input.matches("^[abcdefghijABCDEFGHIJ]{1}\\d{1}")
                || (input.matches("^[abcdefghijABCDEFGHIJ]{1}[1]{1}[0]{1}"))) {
            return true;
        }
        return false;
    }
}
