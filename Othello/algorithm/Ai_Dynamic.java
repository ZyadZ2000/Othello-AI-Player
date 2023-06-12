package Othello.algorithm;

import Othello.Board.Player_Info;

import java.awt.*;

public class Ai_Dynamic extends Player_Info {

    private int depth;
    private Estimator estimator;

    public Ai_Dynamic(int mark, int depth) {
        super(mark);
        this.depth = depth;
        estimator = new Estimator_Dynamic();
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "AI";//"Dynamic AI (Depth " + depth + ")";
    }

    @Override
    public Point play(int[][] board) {
        return Algorithms.solve(board,value,depth,estimator);
    }
}
