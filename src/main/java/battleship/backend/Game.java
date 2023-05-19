package battleship.backend;

import battleship.frontend.GameProcess;

public class Game {
    public static void main(String[] args) {
        GameProcess gameProcess = new GameProcess();

        Player player1 = new Player(gameProcess.acquaintance(1));
        Player player2 = new Player(gameProcess.acquaintance(2));

        gameProcess.placementShips(player1);
        gameProcess.placementShips(player2);

        while (player1.isLife()) {

            gameProcess.turn(player1, player2);

            if (player2.isLife()) {
                gameProcess.turn(player2, player1);
            } else {
                break;
            }
        }

        if (player1.isLife()) {
            gameProcess.finish(player1);
        } else {
            gameProcess.finish(player2);
        }
    }
}
