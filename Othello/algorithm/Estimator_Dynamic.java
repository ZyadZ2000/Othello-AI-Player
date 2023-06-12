package Othello.algorithm;

import Othello.Board.Game_Settings;

import static Othello.algorithm.Estimator_Static.*;

public class Estimator_Dynamic implements Estimator {

    //Evaluation Function Changes during Early-Game / Mid-Game / Late-Game
    enum stateGame {
        early,
        mid,
        last
    }

    private stateGame GetState(int[][] arr2d){
        int x = Game_Settings.CountAllPoints(arr2d);
        if(x<20) return stateGame.early;
        else if(x<=58) return stateGame.mid;
        else return stateGame.last;
    }

    @Override
    public int calc(int[][] arr2d , int y){

        //terminal
        if(Game_Settings.isGameFinished(arr2d)){
            return 1000*differenceCalculator(arr2d, y);
        }

        //semi-terminal
        switch (GetState(arr2d)){
            case early:
                return 1000*calcCorner(arr2d,y) + 50*calc_mobil(arr2d,y);
            case mid:
                return 1000*calcCorner(arr2d,y) + 20*calc_mobil(arr2d,y) + 10*differenceCalculator(arr2d, y) + 100*calculateP(arr2d);
            case last:
            default:
                return 1000*calcCorner(arr2d,y) + 100*calc_mobil(arr2d,y) + 500*differenceCalculator(arr2d, y) + 500*calculateP(arr2d);
        }
    }

}
