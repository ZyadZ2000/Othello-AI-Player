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

   

}
