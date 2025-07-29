package com.cbfacademy;

public class PlayerFactory {
    /**
     * Creates a new player.
     *
     * @return A new player.
     */
    public static Player create(String name) {
        DicePlayer player = new DicePlayer();
        player.setName(name);
        return player;
    }
}
