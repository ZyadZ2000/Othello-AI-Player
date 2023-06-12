package Othello.algorithm;

import Othello.Board.Game_Settings;
import Othello.Board.Player_Info;

import java.awt.*;
import java.util.ArrayList;

public class Ai_Killer extends Player_Info {

    private int depth;
    private Estimator estimator;
    private boolean isPlayer_1;

    private ArrayList<Point> Transtions_history;
    private int[][] arr2dLast;
    private boolean run_1 = true;

    OpeningBook openingBook;
    private boolean isValid = true;

    public Ai_Killer(int mark, int depth, boolean firstplayer) {
        super(mark);

        openingBook = new OpeningBook();
        openingBook.initOpening();

        Transtions_history = new ArrayList<>();

        this.depth = depth;
        isPlayer_1 = firstplayer;

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
        return "AI";//"Realtime Killer (Depth " + depth + ")";
    }

    @Override
    public Point play(int[][] arr2d) {

        if(run_1){
            if(!isPlayer_1){
                Point opMove = Game_Settings.UpdateMove(Game_Settings.getFirstState(),arr2d);
                if(opMove != null) Transtions_history.add(opMove);
                else isValid = false;
            }
            run_1 = false;
        }else{
            Point Transitionop = Game_Settings.UpdateMove(arr2dLast,arr2d);
            if(Transitionop != null) Transtions_history.add(Transitionop);
            else isValid = false;
        }

        Point MyTransition = method2play(arr2d);
        arr2dLast = Game_Settings.update_Board(arr2d,MyTransition,value);
        Transtions_history.add(MyTransition);

        return MyTransition;
    }

    public Point method2play(int[][] arr2d) {
        ArrayList<Point> Transitions = Game_Settings.getValidMoves(arr2d,value);
        int mark = ((value == 1) ? 2 : 1);

        Point Optimal2play = null;
        int optimalVal = Integer.MIN_VALUE;

        ArrayList<Point> corners = new ArrayList<>();
        corners.add(new Point(0,0));
        corners.add(new Point(0,7));
        corners.add(new Point(7,0));
        corners.add(new Point(7,7));
        for(Point transition : Transitions){
            for(Point corner : corners){
                if(corner.equals(transition)){
                    int Val = estimator.calc(Game_Settings.update_Board(arr2d,transition,value),value);
                    if(Val > optimalVal) {
                        Optimal2play = transition;
                        optimalVal = Val;
                    }
                }
            }
        }
        if(Optimal2play != null){
            return Optimal2play;
        }

        Optimal2play = null;
        optimalVal = Integer.MIN_VALUE;

        for(Point transition : Transitions){
            int[][] resultarr2d = Game_Settings.update_Board(arr2d,transition,value);
            if(Game_Settings.getValidMoves(resultarr2d,mark).size() == 0){
                int res = estimator.calc(resultarr2d,value);
                if(res > optimalVal) {
                    Optimal2play = transition;
                    optimalVal = res;
                }
            }
        }
        if(Optimal2play != null){
            return Optimal2play;
        }

        if(isValid) {
            Point OptimalMove = openingBook.move(Transtions_history);
            if (OptimalMove != null) {
                return OptimalMove;
            }
            isValid = false;
        }

        return Algorithms.solve(arr2d,value,depth,estimator);
    }
}
