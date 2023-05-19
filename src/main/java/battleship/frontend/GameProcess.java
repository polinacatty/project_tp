package battleship.frontend;

import battleship.backend.Cell;
import battleship.backend.Player;

import java.util.Scanner;

public class GameProcess {
    public GameProcess() { };
    static Scanner scanner = new Scanner(System.in);

    public String makeAcquaintance(int a) {
        System.out.println("Player" + a + ", enter your name");
        String namePlayer = scanner.nextLine();
        return namePlayer;
    }

    public void finish(Player player) {
        System.out.println("GAME FINISH\n" + player.getName() + " win!!!");
    }

    public void resign(Player player) {
        System.out.println("GAME OVER\n" + player.getName() + " resigned :(");
    }

    //Расстановка кораблей игрока
    public boolean arrangeShips(Player player) {
        System.out.println("Hello, " + player.getName() + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format:"
                + " 'letter''number', for example 'b5' or 'A2'");
        System.out.println("If you want to capitulate, than print 'cup'");

        while (true) {
            PrintMyField printMyField = new PrintMyField(player.getMyField());
            printMyField.print();

            if (player.allShipsArePlaced()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                String input = scanner.next();
                if (input.equals("yes")) {
                    break;
                } else {
                    if (input.equals("cup")) {
                        return true;
                    }
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input = scanner.next();
            switch (input) {
                case "add":
                    if (addShip(player)) {
                        return true;
                    }
                    break;
                case "del":
                    if (deleteShip(player)) {
                        return true;
                    }
                    break;
                case "cup":
                    return true;
                default:
                    System.out.println("wrong input format, please, try again");
            }
        }
        return false;
    }

    //Ход игрока
    public boolean makeAMove(Player me, Player enemy) {
        PrintMyField printMyField = new PrintMyField(me.getMyField());
        PrintEnemyField printEnemyField = new PrintEnemyField(me.getEnemyField());
        System.out.println(me.getName() + ", your turn");
        System.out.println("Your field:");
        printMyField.print();
        System.out.println("Enemy field:");
        printEnemyField.print();
        System.out.println("Please enter coordinates of the cell where you want to shot to:");
        Cell cell = new Cell(tryInputCoordinate());
        if (cell.getCoordinateX() == -1) {
            return true;
        }
        if (me.attack(cell, enemy)) {
            if (enemy.shipIsDead(cell)) {
                System.out.println("Super!!! You hit the target, ship is dead");
                me.getEnemyField().setBorders(enemy.knowShipForCell(cell));
            } else {
                System.out.println("Super!!! You hit the target, but ship is not dead");
            }
            if (enemy.isAlive()) {
                makeAMove(me, enemy);
            }
        } else {
            System.out.println("You missed :(");
        }
        return false;
    }

    //Метод, который обрабатывает исключение неверного ввода координаты
    private Cell tryInputCoordinate() {
        String input = scanner.next();
        if (checkInputCell(input)) {
            return new Cell(input);
        } else {
            if (input.equals("cup")) {
                return new Cell(-1, -1);
            } else {
                System.out.println("wrong input format, please, try again:");
                return tryInputCoordinate();
            }
        }
    }

    //Метод, который обрабатывает исключения неверной постановки корабля
    private boolean addShip(Player player) {
        System.out.println("Enter the coordinate of the beginning of the ship:");
        Cell begin = new Cell(tryInputCoordinate());
        if (begin.getCoordinateX() == -1) {
            return true;
        }
        System.out.println("Enter the coordinate of the end of the ship:");
        Cell end = new Cell(tryInputCoordinate());
        if (end.getCoordinateX() == -1) {
            return true;
        }
        if (player.tryAddShip(begin, end)) {
            System.out.println("You have successfully added a ship!");
        } else {
            System.out.println("You can`t add this ship, please, try again");
            addShip(player);
        }
        return false;
    }

    //Метод, который обрабатывает исключение неверного удаления корабля
    private boolean deleteShip(Player player) {
        System.out.println("Enter the coordinate of any cell of the ship:");
        Cell anyCell = new Cell(tryInputCoordinate());
        if (anyCell.getCoordinateX() == -1) {
            return true;
        }
        if (player.tryDeleteShip(anyCell)) {
            System.out.println("You have successfully deleted a ship!");
        } else {
            System.out.println("You can`t delete this ship, please, try again");
            deleteShip(player);
        }
        return false;
    }

    //Метод, который проверяет правильно ли введена координата
    private boolean checkInputCell(String input) {
        return (input.matches("^[abcdefghijABCDEFGHIJ]{1}\\d{1}")
                || (input.matches("^[abcdefghijABCDEFGHIJ]{1}[1]{1}[0]{1}")));
    }
}
