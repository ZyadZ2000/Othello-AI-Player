package Othello.algorithm;

import Othello.Board.Player_Info;

import java.awt.*;

public class Ai_Current extends Player_Info {

    private int depth;
    private Estimator estimator;

    public Ai_Current(int mark, int depth) {
        super(mark);
        this.depth = depth;

        if(mark==1) {
            estimator = new Estimator_Realtime(new int[][] {
                    {8, 85, -40, 10, 210, 520},
                    {8, 85, -40, 10, 210, 520},
                    {33, -50, -15, 4, 416, 2153},
                    {46, -50, -1, 3, 612, 4141},
                    {51, -50, 62, 3, 595, 3184},
                    {33, -5,  66, 2, 384, 2777},
                    {44, 50, 163, 0, 443, 2568},
                    {13, 50, 66, 0, 121, 986},
                    {4, 50, 31, 0, 27, 192},
                    {8, 500, 77, 0, 36, 299}},
                    new int[] {0, 55, 56, 57, 58, 59, 60, 61, 62, 63});
        }else{
            estimator = new Estimator_Realtime(new int[][] {
                    {8, 85, -40, 10, 210, 520},
                    {8, 85, -40, 10, 210, 520},
                    {33, -50, -15, 4, 416, 2153},
                    {46, -50, -1, 3, 612, 4141},
                    {51, -50, 62, 3, 595, 3184},
                    {33, -5,  66, 2, 384, 2777},
                    {44, 50, 163, 0, 443, 2568},
                    {13, 50, 66, 0, 121, 986},
                    {4, 50, 31, 0, 27, 192},
                    {8, 500, 77, 0, 36, 299}},
                    new int[] {0, 55, 56, 57, 58, 59, 60, 61, 62, 63});
        }
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Realtime AI (Depth " + depth + ")";
    }

    @Override
    public Point play(int[][] arr2d) {
        return Algorithms.solve(arr2d,value,depth,estimator);
    }
}
