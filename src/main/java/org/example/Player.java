package org.example;

import java.util.ArrayList;

import static org.example.Game.scanner;

public class Player {
    public String name;
    public MyField my_field = new MyField();
    public EnemyField enemy_field = new EnemyField();
    public ArrayList<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    //Метод, который проверяет все ли корабли расставлены
    private boolean AllShipsArePlaced() {
        int count_1_deck_ships = 0;
        int count_2_deck_ships = 0;
        int count_3_deck_ships = 0;
        int count_4_deck_ships = 0;

        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).GetterSize() == 1) {
                count_1_deck_ships += 1;
            }
            if (this.ships.get(i).GetterSize() == 2) {
                count_2_deck_ships += 1;
            }
            if (this.ships.get(i).GetterSize() == 3) {
                count_3_deck_ships += 1;
            }
            if (this.ships.get(i).GetterSize() == 4) {
                count_4_deck_ships += 1;
            }
        }

        if ((count_1_deck_ships == 1) && (count_2_deck_ships == 1) && (count_3_deck_ships == 0) && (count_4_deck_ships == 0)) {
            return true;
        }
        return false;
    }

    //Метод, который проверяет, попал ли игрок по кораблю противника
    private boolean Hit(Cell cell, Player enemy) {
        if ((enemy.my_field.GetterCell(cell) == 0) || (enemy.my_field.GetterCell(cell) == 1)) {
            return false;
        }
        return true;
    }

    //Метод, который определяет корабль по заданной точке
    private Ship ShipForCell(Cell cell) {
        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).CellInShip(cell)) {
                return this.ships.get(i);
            }
        }
        Cell cell_ = new Cell(0,0);
        Ship ship = new Ship(cell_, cell_);
        return ship;
    }

    //Метод, который проверяет мертв ли корабль, содержащий заданную точку
    private boolean ShipIsDead(Cell cell) {
        Ship ship = this.ShipForCell(cell);
        for (int j = 0; j < ship.GetterSize(); j++) {
            if (this.my_field.GetterCell(ship.GetterCell(j)) == 5) {
                return false;
            }
        }
        return true;
    }

    //Метод, который преобразует собственное поле противника и поле противника, которое отображается у данного игрока
    private boolean Attack(Cell cell, Player enemy) {
        enemy.my_field.TakingShot(cell);
        this.enemy_field.Shot(cell, this.Hit(cell, enemy));
        return this.Hit(cell, enemy);
    }

    //Ход данного игрока
    public void Turn(Player enemy) {
        System.out.println(this.name + ", your turn");
        System.out.println("Your field:");
        this.my_field.PrintField();
        System.out.println("Enemy field:");
        this.enemy_field.PrintField();
        System.out.println("Please enter coordinates of the cell where you want to shot to:");
        Cell cell = new Cell(this.TryInputCoordinate());
        if (this.Attack(cell, enemy)) {
            if (enemy.ShipIsDead(cell)) {
                System.out.println("Super!!! You hit the target, ship is dead");
                this.enemy_field.Borders(enemy.ShipForCell(cell));
            }
            else {
                System.out.println("Super!!! You hit the target, but ship is not dead");
            }
            if (enemy.IsLife()) {
                this.Turn(enemy);
            }
        }
        else {
            System.out.println("You missed :(");
        }
    }

    //Метод, который обрабатывает исключение неверного ввода координаты
    private Cell TryInputCoordinate() {
        String input = scanner.next();
        if (this.CheckInputCell(input)) {
            Cell cell = new Cell(input);
            return cell;
        }
        else {
            System.out.println("wrong input format, please, try again:");
            return this.TryInputCoordinate();
        }
    }

    //Метод, который обрабатывает исключения неверной постановки корабля
    private void TryAddShip() {
        System.out.println("Enter the coordinate of the beginning of the ship:");
        Cell begin = new Cell(this.TryInputCoordinate());
        System.out.println("Enter the coordinate of the end of the ship:");
        Cell end = new Cell(this.TryInputCoordinate());
        if (this.my_field.CanAddShip(begin, end)) {
            Ship ship = new Ship(begin, end);
            this.my_field.AddShip(ship);
            ships.add(ship);
            System.out.println("You have successfully added a ship!");
        }
        else {
            System.out.println("You can`t add this ship, please, try again");
            this.TryAddShip();
        }
    }

    //Метод, который обрабатывает исключение неверного удаления корабля
    private void TryDeleteShip() {
        System.out.println("Enter the coordinate of any cell of the ship:");
        Cell any_cell = new Cell(this.TryInputCoordinate());
        if (this.my_field.CanDeleteShip(any_cell)) {
            for (int i = 0; i < this.ships.size(); i++) {
                if (this.ships.get(i).CellInShip(any_cell)) {
                    this.my_field.DeleteShip(this.ships.get(i));
                    this.ships.remove(i);
                    break;
                }
            }
            System.out.println("You have successfully deleted a ship!");
        }
        else {
            System.out.println("You can`t delete this ship, please, try again");
            this.TryDeleteShip();
        }
    }

    //Расстановка кораблей данного игрока
    public void PlacementShips() {

        System.out.println("Hello, " + this.name + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format: 'letter''namber', for example 'b5' or 'A2'");

        while (true) {

            this.my_field.PrintField();

            if (this.AllShipsArePlaced()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                if (scanner.next().equals("yes")) {
                    break;
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input = scanner.next();
            if (input.equals("add")) {
                this.TryAddShip();
            }
            else {
                if (input.equals("del")) {
                    this.TryDeleteShip();
                }
                else {
                    System.out.println("wrong input format, please, try again");
                }
            }
        }
    }

    //Метод, который проверяет жив ли игрок
    public boolean IsLife() {
        return this.my_field.SurvivorsShips();
    }

    //Метод, который проверяет правильно ли введена координата
    private boolean CheckInputCell(String input) {
        if (input.matches("^[abcdefghijABCDEFGHIJ]{1}\\d{1}")
                || (input.matches("^[abcdefghijABCDEFGHIJ]{1}[1]{1}[0]{1}"))) {
            return true;
        }
        return false;
    }
}
