package org.example;

import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);

//    public static void Try_add_ship(Player player) {
//        System.out.println("Enter the coordinate of the beginning of the ship:");
//        Cell begin = new Cell(scanner.next());
//        System.out.println("Enter the coordinate of the end of the ship:");
//        Cell end = new Cell(scanner.next());
//        if (player.my_field.Can_add_ship(begin, end)) {
//            Ship ship = new Ship(begin, end);
//            player.my_field.Add_ship(ship);
//            player.Ships.add(ship);
//            System.out.println("You have successfully added a ship!");
//        }
//        else {
//            System.out.println("You can`t add this ship, please, try again");
//            Try_add_ship(player);
//        }
//    }
//
//    public static void Try_delete_ship(Player player) {
//        System.out.println("Enter the coordinate of any cell of the ship:");
//        Cell any_cell = new Cell(scanner.next());
//        if (player.my_field.Can_delete_ship(any_cell)) {
//            for (int i = 0; i < player.Ships.size(); i++) {
//                if (player.Ships.get(i).Cell_in_ship(any_cell)) {
//                    player.my_field.Delete_ship(player.Ships.get(i));
//                    break;
//                }
//            }
//            System.out.println("You have successfully deleted a ship!");
//        }
//        else {
//            System.out.println("You can`t delete this ship, please, try again");
//            Try_delete_ship(player);
//        }
//    }
//
//
//
//
//
//
//    public static void Placement_ships(Player player) {
//
//        System.out.println("Hello, " + player.name + ", please, arrange your ships");
//        player.my_field.Print_field();
//        System.out.println("Enter cell coordinates in the following format: 'letter''namber', for example 'b5' or 'A2'");
//
//        while (true) {
//
//            if (player.All_ships_are_placed()) {
//                System.out.println("All ship are placed. Are you finish?");
//                System.out.println("Write yes/no");
//                if (scanner.next() == "yes") {
//                    break;
//                }
//            }
//
//            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'delete':");
//
//            if (scanner.next() == "add") {
//                System.out.println(6);
//                Try_add_ship(player);
//            }
//            else {
//                if (scanner.next() == "delete") {
//                    Try_delete_ship(player);
//                }
//                else {
//                    System.out.println("wrong input format, please, try again");
//                }
//            }
//
//        }
//
//    }
//


    public static void main(String[] args) {

        System.out.println("Player1, enter your name");
        String name_player1 =  scanner.nextLine();
        System.out.println("Player2, enter your name");
        String name_player2 =  scanner.nextLine();

        Player player_1 = new Player(name_player1);
        Player player_2 = new Player(name_player2);

/////////////////////////////////////////////////////////////////////////////////////////////////
        //PLAYER_1

        System.out.println("Hello, " + player_1.name + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format: 'letter''namber', for example 'b5' or 'A2'");

        while (true) {

            player_1.my_field.Print_field();

            if (player_1.All_ships_are_placed()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                if (scanner.next().equals("yes")) {
                    break;
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input = scanner.nextLine();
            if (input.equals("add")) {
                //////////////////////

                while (true) {
                    System.out.println("Enter the coordinate of the beginning of the ship:");
                    Cell begin = new Cell(scanner.next());
                    System.out.println("Enter the coordinate of the end of the ship:");
                    Cell end = new Cell(scanner.next());
                    if (player_1.my_field.Can_add_ship(begin, end)) {
                        Ship ship = new Ship(begin, end);
                        player_1.my_field.Add_ship(ship);
                        player_1.Ships.add(ship);
                        System.out.println("You have successfully added a ship!");
                        break;
                    }
                    else {
                        System.out.println("You can`t add this ship, please, try again");
                    }
                }

                //////////////////////
            }
            else {
                if (input.equals("del")) {
                    /////////////////////////
                    while (true) {
                        System.out.println("Enter the coordinate of any cell of the ship:");
                        Cell any_cell = new Cell(scanner.next());
                        if (player_1.my_field.Can_delete_ship(any_cell)) {
                            for (int i = 0; i < player_1.Ships.size(); i++) {
                                if (player_1.Ships.get(i).Cell_in_ship(any_cell)) {
                                    player_1.my_field.Delete_ship(player_1.Ships.get(i));
                                    player_1.Ships.remove(i);
                                    break;
                                }
                            }
                            System.out.println("You have successfully deleted a ship!");
                            break;
                        }
                        else {
                            System.out.println("You can`t delete this ship, please, try again");
                        }
                    }
                    /////////////////////////
                }
                else {
                    System.out.println("wrong input format, please, try again");
                }
            }

        }

        /////////////////////////////////////////////////////////////////
        //PLAYER_2


        System.out.println("Hello, " + player_2.name + ", please, arrange your ships");
        System.out.println("Enter cell coordinates in the following format: 'letter''namber', for example 'b5' or 'A2'");

        while (true) {

            player_2.my_field.Print_field();

            if (player_2.All_ships_are_placed()) {
                System.out.println("All ship are placed. Are you finish?");
                System.out.println("Write yes/no");
                if (scanner.next().equals("yes")) {
                    break;
                }
            }

            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'del':");
            String input_ = scanner.nextLine();
            if (input_.equals("add")) {
                /////////////////////

                while (true) {
                    System.out.println("Enter the coordinate of the beginning of the ship:");
                    Cell begin = new Cell(scanner.next());
                    System.out.println("Enter the coordinate of the end of the ship:");
                    Cell end = new Cell(scanner.next());
                    if (player_2.my_field.Can_add_ship(begin, end)) {
                        Ship ship = new Ship(begin, end);
                        player_2.my_field.Add_ship(ship);
                        player_2.Ships.add(ship);
                        System.out.println("You have successfully added a ship!");
                        break;
                    }
                    else {
                        System.out.println("You can`t add this ship, please, try again");
                    }
                }

                /////////////////////
            }
            else {
                if (input_.equals("del")) {
                    /////////////////////////

                    while (true) {
                        System.out.println("Enter the coordinate of any cell of the ship:");
                        Cell any_cell = new Cell(scanner.next());
                        if (player_2.my_field.Can_delete_ship(any_cell)) {
                            for (int i = 0; i < player_2.Ships.size(); i++) {
                                if (player_2.Ships.get(i).Cell_in_ship(any_cell)) {
                                    player_2.my_field.Delete_ship(player_2.Ships.get(i));
                                    player_2.Ships.remove(i);
                                    break;
                                }
                            }
                            System.out.println("You have successfully deleted a ship!");
                            break;
                        }
                        else {
                            System.out.println("You can`t delete this ship, please, try again");
                        }
                    }

                    /////////////////////////
                }
                else {
                    System.out.println("wrong input format, please, try again");
                }
            }

        }

        ///////////////////////////////////////////////////////////////////

        while (player_1.isLife()) {

            player_1.Turn(player_2);

            if (player_2.isLife()) {
                player_2.Turn(player_1);
            }
            else {
                break;
            }
        }

        if (player_1.isLife()) {
            System.out.println("GAME FINISH\n" + "player_1 win!!!");
        }
        else {
            System.out.println("GAME FINISH\n" + "player_2 win!!!");
        }

    }
}