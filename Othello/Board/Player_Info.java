package Othello.Board;

import java.awt.*;

public abstract class Player_Info {

    protected int value;
    public Player_Info(int score){
        value = score;
    }

    abstract public boolean isUserPlayer();

    abstract public String playerName();

    abstract public Point play(int[][] board);

}
