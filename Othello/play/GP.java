package Othello.play;
import java.awt.*;
import Othello.Board.*;

import java.util.ArrayList;

public class GP extends Player_Info {

    public GP(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "The Greedy Player";
    }

    @Override
    public Point play(int[][] arr2d) {

        ArrayList<Point> AvailableMoves = Game_Settings.getValidMoves(arr2d,value);

        Point OptimalTransition = null;
        int OptimalValue = 0;

        for(Point Transition : AvailableMoves) {
            int Value = Game_Settings.get_Flipped_Points(arr2d, this.value, Transition.x,Transition.y).size();
            if(Value > OptimalValue){
                OptimalValue = Value;
                OptimalTransition = Transition;
            }
        }

        return OptimalTransition;

    }

}