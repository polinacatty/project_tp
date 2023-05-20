package battleship.backend;

import battleship.frontend.GameProcess;

public class Game {
    /**
     * Method in which the game takes place
     * @param player1
     * @param player2
     * @param gameProcess
     * @return 1/2 if the player with that number surrendered; 0 if no one surrendered
     */
    public static int numberOfThePlayerWhoSurrendered(Player player1, Player player2, GameProcess gameProcess) {
        if (gameProcess.arrangeShips(player1)) {
            return 1;
        }
        if (gameProcess.arrangeShips(player2)) {
            return 2;
        }

        while (player1.isAlive()) {

            if (gameProcess.makeMove(player1, player2)) {
                return 1;
            }

            if (player2.isAlive()) {
                if (gameProcess.makeMove(player2, player1)) {
                    return 2;
                }
            } else {
                break;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        GameProcess gameProcess = new GameProcess();

        Player player1 = new Player(gameProcess.makeAcquaintance(1));
        Player player2 = new Player(gameProcess.makeAcquaintance(2));

        switch (numberOfThePlayerWhoSurrendered(player1, player2, gameProcess)) {
            case 1:
                gameProcess.resign(player1);
                break;
            case 2:
                gameProcess.resign(player2);
                break;
            default:
                if (player1.isAlive()) {
                    gameProcess.finish(player1);
                } else {
                    gameProcess.finish(player2);
                }
                break;
        }
    }
}
