package Othello.Board;

import Othello.algorithm.*;
import Othello.play.GUI_Windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements Game_Interface {
	private JButton startButton, restartButton;

	// reversi board
	int[][] board;

	// player turn
	// black plays first
	int turn = 1;

	// swing elements
	Cell[][] cells;
	JLabel score1;
	JLabel score2;

	int totalscore1 = 0;
	int totalscore2 = 0;

	JLabel tscore1;
	JLabel tscore2;

	Player_Info player1;
	Player_Info player2;

	Timer player1HandlerTimer;
	Timer player2HandlerTimer;

	@Override
	public int getGameScore(int i, int j) {
		return board[i][j];
	}

	@Override
	public void setGameScore(int i, int j, int value) {
		board[i][j] = value;
	}

	public Board(String difficulty, String mode) {
	}

	private boolean awaitForClick = false;

	private void restartGame() {
		// Reset the board
		resetBoard();
		turn = 1;
		manageTurn();
	}

	public void manageTurn() {
	}

	public void resetBoard() {
	}

	// update highlights on possible moves and scores
	public void updateBoardInfo() {
	}

	@Override
	public void handleClick(int i, int j) {
	}

	public void handleAI(Player_Info ai) {
	}

}

