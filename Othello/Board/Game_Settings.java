package Othello.Board;

import java.awt.*;
import java.util.ArrayList;

public class Game_Settings {
    
    public static final int EMPTY = 0;
    public static final int BOARD_LENGTH = 8;
    public static final int BOARD_WIDTH = 8;
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    public static boolean isGameFinished(int[][] board){
        
        if((ValidMoveExist(board,PLAYER_ONE) || ValidMoveExist(board,PLAYER_TWO))){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    public static int[][] getFirstState(){
        int[][] board = new int[BOARD_LENGTH][BOARD_WIDTH];
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                board[row][col] = 0;
            }
        }
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
        return board;
    }
	  public static Point UpdateMove(int[][] before , int[][] after){
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if(before[row][col]== EMPTY && after[row][col]!= EMPTY){
                    return new Point(row,col);
                }
            }
        }
        return null;
    }
   
    public static int Find_Winner(int[][] board){
        if(!isGameFinished(board))
            //not finish yet
            return -1;
        else{
            //find the winner
            int player1_points = CountPlayerPoints(board,PLAYER_ONE);
            int player2_points = CountPlayerPoints(board,PLAYER_TWO);
            
            if(player1_points < player2_points){
                //p2 wins
                return PLAYER_TWO;
            }
            else if(player1_points > player2_points){
                //p1 wins
                return PLAYER_ONE;
            }
            else{
                //tie
                return EMPTY;
            }
        }
    }
	
    public static int CountAllPoints(int[][] board){
        int counter = 0;
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if(board[row][col] != EMPTY) counter++;
            }
        }
        return counter;
    }
    

    public static int CountPlayerPoints(int[][] board, int player){
        int points = 0;
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if(board[row][col] == player) points++;
            }
        }
        return points;
    }
    


    public static boolean ValidMoveExist(int[][] board, int player){ 
        return getValidMoves(board,player).size() > EMPTY;
    }
	

    public static ArrayList<Point> getValidMoves(int[][] board, int player){
        ArrayList<Point> Valids = new ArrayList<>();
        
        boolean result;
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                result = Valid_Move(board,player,row,col);
                if(result){
                    Valids.add(new Point(row,col));
                }
            }
        }
        return Valids;
    }

   

}
