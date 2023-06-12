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
	  public static boolean Valid_Move(int[][] board, int player, int ROW, int COL){
        boolean checking, Valid = false;
        int Modified_Row , Modified_Col ;

        int Enemy;
        if(player == PLAYER_ONE){
            Enemy= PLAYER_TWO;
        }
        else{
            Enemy= PLAYER_ONE;
        }

        // If the cell is not empty
        if(board[ROW][COL] != EMPTY) {
            Valid = false;
            return Valid;
        }

        //check the 8 directions
        for (int i= 0;i< 8 ; i++){
            checking = false;
            if(i == 0){
                Modified_Row = ROW - 1;
                Modified_Col = COL;
                while(Modified_Row > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row--;
                    checking = true;
                }
                if(Modified_Row >= 0 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 1){
                Modified_Row = ROW + 1;
                Modified_Col = COL;
                while(Modified_Row < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row++;
                    checking = true;
                }
                if(Modified_Row <= 7 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 2){
                Modified_Row = ROW;
                Modified_Col = COL - 1;
                while(Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Col--;
                    checking = true;
                }
                if(Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 3){
                Modified_Row = ROW;
                Modified_Col = COL + 1;

                while(Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Col++;
                    checking = true;
                }
                if(Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 4){
                Modified_Row = ROW - 1;
                Modified_Col = COL - 1;
                while(Modified_Row > 0 && Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row--;
                    Modified_Col--;
                    checking = true;
                }
                if(Modified_Row >= 0 && Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 5){
                Modified_Row = ROW - 1;
                Modified_Col = COL + 1;
                while(Modified_Row > 0 && Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row--;
                    Modified_Col++;
                    checking = true;
                }
                if(Modified_Row >= 0 && Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 6){
                Modified_Row = ROW + 1;
                Modified_Col = COL - 1;
                while(Modified_Row < 7 && Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row++;
                    Modified_Col--;
                    checking = true;
                }
                if(Modified_Row <= 7 && Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
            else if(i == 7){
                Modified_Row = ROW + 1;
                Modified_Col = COL + 1;
                while(Modified_Row < 7 && Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    Modified_Row++;
                    Modified_Col++;
                    checking = true;
                }
                if(Modified_Row <= 7 && Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && checking == true){
                    Valid = true;
                    break;
                }
            }
        }

        return Valid;
    }
	
	public static ArrayList<Point> get_Flipped_Points(int[][] board, int player, int ROW, int COL){

        int Modified_Row , Modified_Col ;
        int Enemy;
        if(player == PLAYER_ONE){
            Enemy= PLAYER_TWO;
        }
        else{
            Enemy= PLAYER_ONE;
        }
        ArrayList<Point> Reverse_Points = new ArrayList<>();

        for(int i = 0;i<8;i++){
            ArrayList<Point> temp = new ArrayList<>();
            if(i == 0){
                Modified_Row = ROW - 1;
                Modified_Col = COL;
                while(Modified_Row > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row--;
                }
                if(Modified_Row >= 0 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 1){
                Modified_Row = ROW + 1;
                Modified_Col = COL;
                while(Modified_Row < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row++;
                }
                if(Modified_Row <= 7 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 2){
                Modified_Row = ROW;
                Modified_Col = COL - 1;
                while(Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Col--;
                }
                if(Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 3){
                Modified_Row = ROW;
                Modified_Col = COL + 1;

                while(Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Col++;
                }
                if(Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 4){
                Modified_Row = ROW - 1;
                Modified_Col = COL - 1;
                while(Modified_Row > 0 && Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row--;
                    Modified_Col--;
                }
                if(Modified_Row >= 0 && Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 5){
                Modified_Row = ROW - 1;
                Modified_Col = COL + 1;
                while(Modified_Row > 0 && Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row--;
                    Modified_Col++;
                }
                if(Modified_Row >= 0 && Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 6){
                Modified_Row = ROW + 1;
                Modified_Col = COL - 1;
                while(Modified_Row < 7 && Modified_Col > 0 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row++;
                    Modified_Col--;
                }
                if(Modified_Row <= 7 && Modified_Col >= 0 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
            else if(i == 7){
                Modified_Row = ROW + 1;
                Modified_Col = COL + 1;
                while(Modified_Row < 7 && Modified_Col < 7 && board[Modified_Row][Modified_Col] == Enemy){
                    temp.add(new Point(Modified_Row,Modified_Col));
                    Modified_Row++;
                    Modified_Col++;
                }
                if(Modified_Row <= 7 && Modified_Col <= 7 && board[Modified_Row][Modified_Col] == player && temp.size() > 0){
                    Reverse_Points.addAll(temp);
                }
            }
        }
        return Reverse_Points;
    }

    public static int[][] update_Board(int[][] board, Point move , int player){

        int[][] UpdatedBoard = new int[BOARD_LENGTH][BOARD_WIDTH];
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                UpdatedBoard[row][col] = board[row][col];
            }
        }
        ArrayList<Point> Flipped = Game_Settings.get_Flipped_Points(UpdatedBoard,player,move.x,move.y);
        UpdatedBoard[move.x][move.y] = player;
        for(Point pt : Flipped){
            UpdatedBoard[pt.x][pt.y] = player;
        }
        return UpdatedBoard;
    }


   

}
