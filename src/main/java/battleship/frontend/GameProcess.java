package battleship.frontend;

import battleship.backend.Cell;
import battleship.backend.Player;

import java.util.Scanner;

public class GameProcess {
    public GameProcess() { };
    static Scanner scanner = new Scanner(System.in);

    public String acquaintance(int a) {
        System.out.println("Player" + a + ", enter your name");
        String namePlayer = scanner.nextLine();
        return namePlayer;
    }

    public void finish(Player player) {
        System.out.println("GAME FINISH\n" + player.getName() + " win!!!");
    }

    //Расстановка кораблей игрока
    public void placementShips(Player player) {
        System.out.println("Hello, " + player.getName() + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format:"
                + " 'letter''number', for example 'b5' or 'A2'");

        while (true) {
            PrintMyField printMyField = new PrintMyField(player.getMyField());
            printMyField.print();

            if (player.allShipsArePlaced()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                if (scanner.next().equals("yes")) {
                    break;
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input = scanner.next();
            if (input.equals("add")) {
                addShip(player);
            } else {
                if (input.equals("del")) {
                    deleteShip(player);
                } else {
                    System.out.println("wrong input format, please, try again");
                }
            }
        }
    }

    //Ход игрока
    public void turn(Player me, Player enemy) {
        PrintMyField printMyField = new PrintMyField(me.getMyField());
        PrintEnemyField printEnemyField = new PrintEnemyField(me.getEnemyField());
        System.out.println(me.getName() + ", your turn");
        System.out.println("Your field:");
        printMyField.print();
        System.out.println("Enemy field:");
        printEnemyField.print();
        System.out.println("Please enter coordinates of the cell where you want to shot to:");
        Cell cell = new Cell(this.tryInputCoordinate());
        if (me.attack(cell, enemy)) {
            if (enemy.shipIsDead(cell)) {
                System.out.println("Super!!! You hit the target, ship is dead");
                me.getEnemyField().borders(enemy.shipForCell(cell));
            } else {
                System.out.println("Super!!! You hit the target, but ship is not dead");
            }
            if (enemy.isLife()) {
                turn(me, enemy);
            }
        } else {
            System.out.println("You missed :(");
        }
    }

    //Метод, который обрабатывает исключение неверного ввода координаты
    private Cell tryInputCoordinate() {
        String input = scanner.next();
        if (this.checkInputCell(input)) {
            Cell cell = new Cell(input);
            return cell;
        } else {
            System.out.println("wrong input format, please, try again:");
            return this.tryInputCoordinate();
        }
    }

    //Метод, который обрабатывает исключения неверной постановки корабля
    private void addShip(Player player) {
        System.out.println("Enter the coordinate of the beginning of the ship:");
        Cell begin = new Cell(this.tryInputCoordinate());
        System.out.println("Enter the coordinate of the end of the ship:");
        Cell end = new Cell(this.tryInputCoordinate());
        if (player.tryAddShip(begin, end)) {
            System.out.println("You have successfully added a ship!");
        } else {
            System.out.println("You can`t add this ship, please, try again");
            this.addShip(player);
        }
    }

    //Метод, который обрабатывает исключение неверного удаления корабля
    private void deleteShip(Player player) {
        System.out.println("Enter the coordinate of any cell of the ship:");
        Cell anyCell = new Cell(this.tryInputCoordinate());
        if (player.tryDeleteShip(anyCell)) {
            System.out.println("You have successfully deleted a ship!");
        } else {
            System.out.println("You can`t delete this ship, please, try again");
            deleteShip(player);
        }
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
