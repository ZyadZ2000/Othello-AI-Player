package Othello.algorithm;

import Othello.Board.Game_Settings;

public class Estimator_Static implements Estimator {

    @Override
    public int calc(int[][] arr2d , int y){
        int m = calc_mobil(arr2d,y);
        int k = differenceCalculator(arr2d,y);
        return 2*m + k + 1000*calcCorner(arr2d,y);
    }

    public static int differenceCalculator(int[][] arr2d , int y){
        int q = (y==1) ? 2 : 1;

        int var1 = Game_Settings.CountPlayerPoints(arr2d,y);
        int var2 = Game_Settings.CountPlayerPoints(arr2d,q);

        return 100 * (var1 - var2) / (var1 + var2);
    }

    public static int calc_mobil(int[][] arr2d , int y){
        int oplayer = (y==1) ? 2 : 1;

        int NoTransition = Game_Settings.getValidMoves(arr2d,y).size();
        int NoOp = Game_Settings.getValidMoves(arr2d,oplayer).size();

        return 100 * (NoTransition - NoOp) / (NoTransition + NoOp + 1);
    }

    public static int calcCorner(int[][] arr2d , int y){
        int oplayer = (y==1) ? 2 : 1;

        int c = 0;
        int o = 0;

        if(arr2d[0][0]==y) c++;
        if(arr2d[7][0]==y) c++;
        if(arr2d[0][7]==y) c++;
        if(arr2d[7][7]==y) c++;

        if(arr2d[0][0]==oplayer) o++;
        if(arr2d[7][0]==oplayer) o++;
        if(arr2d[0][7]==oplayer) o++;
        if(arr2d[7][7]==oplayer) o++;

        return 100 * (c - o) / (c + o + 1);
    }

    public static int mapCalculation(int[][] arr2d , int y){
        int p = (y==1) ? 2 : 1;
        int[][] arr = {
                {200 , -100, 100,  50,  50, 100, -100,  200},
                {-100, -200, -50, -50, -50, -50, -200, -100},
                {100 ,  -50, 100,   0,   0, 100,  -50,  100},
                {50  ,  -50,   0,   0,   0,   0,  -50,   50},
                {50  ,  -50,   0,   0,   0,   0,  -50,   50},
                {100 ,  -50, 100,   0,   0, 100,  -50,  100},
                {-100, -200, -50, -50, -50, -50, -200, -100},
                {200 , -100, 100,  50,  50, 100, -100,  200}};

        //if corners are taken arr for that 1/4 loses effect
        if(arr2d[0][0] != 0){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    arr[i][j] = 0;
                }
            }
        }

        if(arr2d[0][7] != 0){
            for (int i = 0; i < 3; i++) {
                for (int j = 4; j <= 7; j++) {
                    arr[i][j] = 0;
                }
            }
        }

        if(arr2d[7][0] != 0){
            for (int i = 5; i < 8; i++) {
                for (int j = 0; j <= 3; j++) {
                    arr[i][j] = 0;
                }
            }
        }

        if(arr2d[7][7] != 0){
            for (int i = 5; i < 8; i++) {
                for (int j = 4; j <= 7; j++) {
                    arr[i][j] = 0;
                }
            }
        }


        int a = 0;
        int b = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(arr2d[i][j]==y) a += arr[i][j];
                if(arr2d[i][j]==p) b += arr[i][j];
            }
        }

        return (a - b) / (a + b + 1);
    }

    public static int calculateP(int[][] arr2d){
        int counts = 64 - Game_Settings.CountAllPoints(arr2d);
        return counts % 2 == 0 ? -1 : 1;
    }


}
