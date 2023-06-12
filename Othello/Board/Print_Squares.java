package Othello.Board;

import javax.swing.*;
import java.awt.*;

public class Print_Squares extends JFrame implements Game_Interface {
    
    public static final int BOARD_LENGTH = 8;
    public static final int BOARD_WIDTH = 8;

    public Cell[][] cells;
    int[][] board;

    public Print_Squares(int[][] board, String title){

        this.board = board;

        JPanel OthelloBoard = new JPanel();
        OthelloBoard.setLayout(new GridLayout(8,8));
        OthelloBoard.setPreferredSize(new Dimension(250,250));
        OthelloBoard.setBackground(new Color(41,100, 59));

        //init board
        //resetBoard();

        cells = new Cell[BOARD_LENGTH][BOARD_WIDTH];
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                cells[row][col] = new Cell(this,OthelloBoard,row,col);
                OthelloBoard.add(cells[row][col]);
            }
        }

        this.add(OthelloBoard);
        this.setTitle(title);
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        OthelloBoard.repaint();
        //this.setSize(500,500);

    }

    public void showForm(){

    }


    @Override
    public int getGameScore(int row, int col) {
        return board[row][col];
    }

    @Override
    public void setGameScore(int row, int col, int value) {
        System.err.println("Printer cant edit !");
    }

    @Override
    public void handleClick(int row, int col) {
        //do nothing
    }
}
