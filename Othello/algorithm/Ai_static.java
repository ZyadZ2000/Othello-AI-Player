package Othello.algorithm;

import Othello.Board.Player_Info;

import java.awt.*;

public class Ai_static extends Player_Info {

    private int depth;
    private Estimator estimator;

    public Ai_static(int mark, int depth) {
        super(mark);
        this.depth = depth;
        estimator = new Estimator_Static();
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Static AI (Depth " + depth + ")";
    }

    @Override
    public Point play(int[][] arr2d) {
        return Algorithms.solve(arr2d,value,depth,estimator);
    }
}
