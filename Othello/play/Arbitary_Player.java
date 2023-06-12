package Othello.play;

import Othello.Board.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Arbitary_Player extends Player_Info {

    Random random = new Random();

    public Arbitary_Player(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Random Player";
    }

    @Override
    public Point play(int[][] arr2d) {
        ArrayList<Point> AvailableMoves = Game_Settings.getValidMoves(arr2d,value);

        if(AvailableMoves.size() > 0){
            return AvailableMoves.get(random.nextInt(AvailableMoves.size()));
        }else{
            return null;
        }

    }

}
