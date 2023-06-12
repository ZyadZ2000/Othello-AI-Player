package Othello.algorithm;

import Othello.Board.Game_Settings;

import java.awt.*;
import java.util.ArrayList;

import static Othello.Board.Game_Settings.getValidMoves;

public class Estimator_Realtime implements Estimator {

    int[][] setWeightDisc;

    public int calc(int[][] arr2d , int y){
        int result_total = 0;

        int[] arr_weights = setWeightDisc[Game_Settings.CountAllPoints(arr2d)];

        if(arr_weights[0] != 0) {
            result_total += arr_weights[0] * availabilty(arr2d,y);
        }
        if(arr_weights[1] != 0) {
            result_total += arr_weights[1] * border(arr2d,y);
        }
        if(arr_weights[2] != 0) {
            result_total += arr_weights[2] * parts(arr2d,y);
        }
        if(arr_weights[3] != 0) {
            result_total += arr_weights[3] * locate(arr2d,y);
        }
        if(arr_weights[4] != 0) {
            result_total += arr_weights[4] * stabilization(arr2d,y);
        }
        if(arr_weights[5] != 0) {
            result_total += arr_weights[5] * grabCor(arr2d,y);
        }

        return result_total;
    }


    public Estimator_Realtime(int[][] arrSetWeight , int[] arr){
        setWeightDisc = new int[65][arrSetWeight[0].length];

        //dc : Disk Count
        for(int disckIterator = 0; disckIterator <= 64; disckIterator++) {
            // determine which set of arr_weights to use
            int var = 0;
            for(int i = 0; i < arr.length; i++) {
                if(disckIterator <= arr[i]) {
                    var = i;
                    break;
                }
            }

            if(var == 0) {
                setWeightDisc[disckIterator] = arrSetWeight[0];
                continue;
            }

           
            double scalefactor = ((double)disckIterator - arr[var - 1]) / (arr[var] - arr[var - 1]);
            for(int i = 0; i < arrSetWeight[var].length; i++) {
                setWeightDisc[disckIterator][i] = (int)Math.rint(scalefactor * arrSetWeight[var][i] + (1 - scalefactor) * arrSetWeight[var - 1][i]);
            }
        }
    }

    public static int parts(int[][] arr2d , int y){
        int oplayer = (y==1) ? 2 : 1;

        int var1 = Game_Settings.CountPlayerPoints(arr2d,y);
        int var2 = Game_Settings.CountPlayerPoints(arr2d,oplayer);

        return 100 * (var1 - var2) / (var1 + var2 + 1);
    }

    static int[][] arr2dScore = {
            {100 , -10 , 8  ,  6 ,  6 , 8  , -10 ,  100},
            {-10 , -25 ,  -4, -4 , -4 , -4 , -25 , -10 },
            {8   ,  -4 ,   6,   4,   4,   6,  -4 ,  8  },
            {6   ,  -4 ,   4,   0,   0,   4,  -4 ,  6  },
            {6   ,  -4 ,   4,   0,   0,   4,  -4 ,  6  },
            {8   ,  -4 ,   6,   4,   4,   6,  -4 ,  8  },
            {-10 , -25 ,  -4, -4 , -4 , -4 , -25 , -10 },
            {100 , -10 , 8  ,  6 ,  6 , 8  , -10 ,  100}};

    public static int locate(int[][] arr2d , int y){
        int player2 = (y==1) ? 2 : 1;

        int var1 = 0;
        int var2 = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(arr2d[i][j]==y) var1 += arr2dScore[i][j];
                if(arr2d[i][j]==player2) var2 += arr2dScore[i][j];
            }
        }

        return var1 - var2;
    }

    public static int stabilization(int[][] arr2d , int player1){
        int player2 = (player1==1) ? 2 : 1;

        int var1 = 0;
        int var2 = 0;

        if(arr2d[0][0] == player1) var1 += Game_Settings.get_NON_Flippped_Disks(arr2d,player1,0,0).size();
        if(arr2d[0][7] == player1) var1 += Game_Settings.get_NON_Flippped_Disks(arr2d,player1,0,7).size();
        if(arr2d[7][0] == player1) var1 += Game_Settings.get_NON_Flippped_Disks(arr2d,player1,7,0).size();
        if(arr2d[7][7] == player1) var1 += Game_Settings.get_NON_Flippped_Disks(arr2d,player1,7,7).size();

        if(arr2d[0][0] == player2) var2 += Game_Settings.get_NON_Flippped_Disks(arr2d,player2,0,0).size();
        if(arr2d[0][7] == player2) var2 += Game_Settings.get_NON_Flippped_Disks(arr2d,player2,0,7).size();
        if(arr2d[7][0] == player2) var2 += Game_Settings.get_NON_Flippped_Disks(arr2d,player2,7,0).size();
        if(arr2d[7][7] == player2) var2 += Game_Settings.get_NON_Flippped_Disks(arr2d,player2,7,7).size();

        return 100 * (var1 - var2) / (var1 + var2 + 1);
    }

    public static int availabilty(int[][] arr2d , int p1){
        int p2 = (p1==1) ? 2 : 1;

        int var1 = getValidMoves(arr2d,p1).size();
        int var2 = getValidMoves(arr2d,p2).size();

        return 100 * (var1 - var2) / (var1 + var2 + 1);
    }

    public static int border(int[][] board , int p1){
        int p2 = (p1==1) ? 2 : 1;

        int var1 = Game_Settings.frontier_squares(board,p1).size();
        int var2 = Game_Settings.frontier_squares(board,p2).size();

        return 100 * (var1 - var2) / (var1 + var2 + 1);
    }

    public static int grabCor(int[][] arr2d , int p1){
        ArrayList<Point> Transitions = Game_Settings.getValidMoves(arr2d,p1);

        for(Point transition : Transitions){
            //if p1 have corner move return 1
            if(transition.x == 0 && transition.y == 0) return 100;
            if(transition.x == 7 && transition.y == 0) return 100;
            if(transition.x == 0 && transition.y == 7) return 100;
            if(transition.x == 7 && transition.y == 7) return 100;
        }

        return 0;
    }

}
