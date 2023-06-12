package Othello.Board;

//interface that gui speaks to
public interface Game_Interface {

    public int getGameScore(int row,int col);

    public void setGameScore(int row,int col,int value);

    public void handleClick(int row,int col);

}
