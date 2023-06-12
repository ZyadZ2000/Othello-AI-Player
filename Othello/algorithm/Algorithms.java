package Othello.algorithm;
import Othello.Board.Game_Settings;
import java.awt.*;

public class Algorithms {

    static int VisitedNode = 0;

    public static Point solve(int[][] arr2d, int player,int depth,Estimator estimator){
        VisitedNode = 0;
        int Optimal = Integer.MIN_VALUE;
        Point OptimalTransition = null;
        for(Point Transition : Game_Settings.getValidMoves(arr2d,player)){
            int[][] nextNode = Game_Settings.update_Board(arr2d,Transition,player);
            int ValueChild = MinMaxAlphaBeta(nextNode,player,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE,estimator);
                if(ValueChild > Optimal) {
                Optimal = ValueChild;
                OptimalTransition = Transition;
            }
        }
        return OptimalTransition;
    }

  
    private static int MinMaxAlphaBeta(int[][] arr2d,int player,int depth,boolean max,int alpha,int beta,Estimator estimator){
        VisitedNode++;
        if(depth == 0 || Game_Settings.isGameFinished(arr2d)){
            return estimator.calc(arr2d,player);
        }
        int Opponent = (player==1) ? 2 : 1;
        if((max && !Game_Settings.ValidMoveExist(arr2d,player)) || (!max && !Game_Settings.ValidMoveExist(arr2d,Opponent))){
            return MinMaxAlphaBeta(arr2d,player,depth-1,!max,alpha,beta,estimator);
        }
        int result_value;
        if(max){
            result_value = Integer.MIN_VALUE;
            for(Point Transition : Game_Settings.getValidMoves(arr2d,player)){ 
                int[][] nextNode = Game_Settings.update_Board(arr2d,Transition,player);
                int ValueChild = MinMaxAlphaBeta(nextNode,player,depth-1,false,alpha,beta,estimator);
                if(ValueChild > result_value) result_value = ValueChild;
                if(result_value > alpha) alpha = result_value;
                if(beta <= alpha) break; 
            }
        }else{
            result_value = Integer.MAX_VALUE;
            for(Point Transition : Game_Settings.getValidMoves(arr2d,Opponent)){ 
                int[][] newNode = Game_Settings.update_Board(arr2d,Transition,Opponent);
                int ValueChild = MinMaxAlphaBeta(newNode,player,depth-1,true,alpha,beta,estimator);
                if(ValueChild < result_value) result_value = ValueChild;
                if(result_value < beta) beta = result_value;
                if(beta <= alpha) break; 
            }
        }
        return result_value;
    }

}
