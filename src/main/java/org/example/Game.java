package org.example;

import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Player1, enter your name");
        String name_player1 =  scanner.nextLine();
        System.out.println("Player2, enter your name");
        String name_player2 =  scanner.nextLine();

        Player player_1 = new Player(name_player1);
        Player player_2 = new Player(name_player2);

        player_1.Placement_ships();
        player_2.Placement_ships();

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
            System.out.println("GAME FINISH\n" + player_1.name + " win!!!");
        }
        else {
            System.out.println("GAME FINISH\n" + player_2.name + " win!!!");
        }

    }
}
