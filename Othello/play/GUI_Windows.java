package Othello.play;

import Othello.Board.Player_Info;

import java.awt.*;

public class GUI_Windows extends Player_Info {

    public GUI_Windows(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return true;
    }

    @Override
    public String playerName() {
        return "User" ;
    }

    @Override
    public Point play(int[][] board) {
        return null;
    }

}
