package com.cbfacademy;

public class GameFactory {
    /**
     * Creates a new dice game.
     *
     * @return A dice game.
     */
    public static Game create(Player player1, Player player2) {
        return new DiceGame(player1, player2);
    }
}
