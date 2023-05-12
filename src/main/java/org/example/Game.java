package org.example;

import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Player1, enter your name");
        String namePlayer1 = scanner.nextLine();
        System.out.println("Player2, enter your name");
        String namePlayer2 = scanner.nextLine();

        Player player1 = new Player(namePlayer1);
        Player player2 = new Player(namePlayer2);

        player1.placementShips();
        player2.placementShips();

        while (player1.isLife()) {

            player1.turn(player2);

            if (player2.isLife()) {
                player2.turn(player1);
            } else {
                break;
            }
        }

        if (player1.isLife()) {
            System.out.println("GAME FINISH\n" + player1.getterName() + " win!!!");
        } else {
            System.out.println("GAME FINISH\n" + player2.getterName() + " win!!!");
        }
    }
}
