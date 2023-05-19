package battleship.backend;

import battleship.frontend.GameProcess;

public class Game {
    //Метод, в котором происходит игра;
    //возвращает 1/2, если игрок под таким номером сдался, и 0, если никто не сдавался
    public static int numberOfThePlayerWhoSurrendered(Player player1, Player player2, GameProcess gameProcess) {
        if (gameProcess.arrangeShips(player1)) {
            return 1;
        }
        if (gameProcess.arrangeShips(player2)) {
            return 2;
        }

        while (player1.isAlive()) {

            if (gameProcess.makeAMove(player1, player2)) {
                return 1;
            }

            if (player2.isAlive()) {
                if (gameProcess.makeAMove(player2, player1)) {
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
            case 0:
                if (player1.isAlive()) {
                    gameProcess.finish(player1);
                } else {
                    gameProcess.finish(player2);
                }
                break;
        }
    }
}
