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
		if (mode.equals("Human Vs Human")) {
			player1 = new GUI_Windows(1);
			player2 = new GUI_Windows(2);
		} else if (mode.equals("Ai Vs Human") && difficulty.equals("Hard")) {
			player1 = new GUI_Windows(1);
			player2 = new Ai_Killer(2, 6, false);
		} else if (mode.equals("Ai Vs Human") && difficulty.equals("Easy")) {
			player1 = new GUI_Windows(1);
			player2 = new Ai_Dynamic(1, 1);
		} else if (mode.equals("Ai Vs Ai") && difficulty.equals("Hard")) {
			player1 = new Ai_Killer(1, 6, true);
			player2 = new Ai_Killer(2, 6, false);
		} else {
			player1 = new Ai_Dynamic(1, 6);
			player2 = new Ai_Dynamic(2, 6);
		}

		restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Restart the Othello.game
				restartGame();
				repaint();
			}
		});

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel reversiBoard = new JPanel();
		reversiBoard.setLayout(new GridLayout(8, 8));
		reversiBoard.setPreferredSize(new Dimension(500, 500));
		reversiBoard.setBackground(new Color(150, 150, 150));

		// init board
		resetBoard();

		cells = new Cell[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cells[i][j] = new Cell(this, reversiBoard, i, j);
				reversiBoard.add(cells[i][j]);
			}
		}

		mainPanel.add(reversiBoard, BorderLayout.CENTER);

		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.X_AXIS));
		sidebar.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add a 10-pixel margin
		sidebar.setPreferredSize(new Dimension(0, 100));

		score1 = new JLabel("Score 1");
		score2 = new JLabel("Score 2");

		Font labelFont = score1.getFont();
		score1.setFont(new Font(labelFont.getFontName(), Font.BOLD, 18));
		score2.setFont(new Font(labelFont.getFontName(), Font.BOLD, 18));

		sidebar.add(Box.createHorizontalStrut(40));
		sidebar.add(score1);
		sidebar.add(Box.createHorizontalStrut(60));
		sidebar.add(score2);
		sidebar.add(Box.createHorizontalStrut(50));
		sidebar.add(restartButton);

		this.add(mainPanel, BorderLayout.CENTER);
		this.add(sidebar, BorderLayout.SOUTH);

		updateBoardInfo();

		player1HandlerTimer = new Timer(1000, (ActionEvent e) -> {
			handleAI(player1);
			player1HandlerTimer.stop();
			manageTurn();
		});

		player2HandlerTimer = new Timer(1000, (ActionEvent e) -> {
			handleAI(player2);
			player2HandlerTimer.stop();
			manageTurn();
		});

		manageTurn();
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

