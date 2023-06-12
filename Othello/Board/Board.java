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
		if (Game_Settings.ValidMoveExist(board, 1) || Game_Settings.ValidMoveExist(board, 2)) {
			updateBoardInfo();
			if (turn == 1) {
				if (Game_Settings.ValidMoveExist(board, 1)) {
					if (player1.isUserPlayer()) {
						awaitForClick = true;
						// after click this function should be call backed
					} else {
						player1HandlerTimer.start();
					}
				} else {
					// forfeit this move and pass the turn
					turn = 2;
					manageTurn();
				}
			} else {
				if (Game_Settings.ValidMoveExist(board, 2)) {
					if (player2.isUserPlayer()) {
						awaitForClick = true;
						// after click this function should be call backed
					} else {
						player2HandlerTimer.start();
					}
				} else {
					turn = 1;
					manageTurn();
				}
			}
		} else {
			// Othello.game finished
			System.out.println("Game Finished !");
			int winner = Game_Settings.Find_Winner(board);
			if (winner == 1)
				totalscore1++;
			else if (winner == 2)
				totalscore2++;
			updateTotalScore();

		}
	}

	public void resetBoard() {
		board = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = 0;
			}
		}
		// initial board state
		setGameScore(3, 3, 2);
		setGameScore(3, 4, 1);
		setGameScore(4, 3, 1);
		setGameScore(4, 4, 2);
		setGameScore(0, 0, 0);
		setGameScore(0, 1, 0);
		setGameScore(0, 2, 0);
		setGameScore(0, 3, 0);
		setGameScore(0, 4, 0);
		setGameScore(0, 5, 0);
		setGameScore(0, 6, 0);
		setGameScore(0, 7, 0);
		setGameScore(1, 0, 0);
		setGameScore(1, 1, 0);
		setGameScore(1, 2, 0);
		setGameScore(1, 3, 0);
		setGameScore(1, 4, 0);
		setGameScore(1, 5, 0);
		setGameScore(1, 6, 0);
		setGameScore(1, 7, 0);
		setGameScore(2, 0, 0);
		setGameScore(2, 1, 0);
		setGameScore(2, 2, 0);
		setGameScore(2, 3, 0);
		setGameScore(2, 4, 0);
		setGameScore(2, 5, 0);
		setGameScore(2, 6, 0);
		setGameScore(2, 7, 0);
		setGameScore(3, 0, 0);
		setGameScore(3, 1, 0);
		setGameScore(3, 2, 0);
		setGameScore(3, 5, 0);
		setGameScore(3, 6, 0);
		setGameScore(3, 7, 0);
		setGameScore(4, 0, 0);
		setGameScore(4, 1, 0);
		setGameScore(4, 2, 0);
		setGameScore(4, 5, 0);
		setGameScore(4, 6, 0);
		setGameScore(4, 7, 0);
		setGameScore(5, 0, 0);
		setGameScore(5, 1, 0);
		setGameScore(5, 2, 0);
		setGameScore(5, 3, 0);
		setGameScore(5, 4, 0);
		setGameScore(5, 5, 0);
		setGameScore(5, 6, 0);
		setGameScore(5, 7, 0);
		setGameScore(6, 0, 0);
		setGameScore(6, 1, 0);
		setGameScore(6, 2, 0);
		setGameScore(6, 3, 0);
		setGameScore(6, 4, 0);
		setGameScore(6, 5, 0);
		setGameScore(6, 6, 0);
		setGameScore(6, 7, 0);
		setGameScore(7, 0, 0);
		setGameScore(7, 1, 0);
		setGameScore(7, 2, 0);
		setGameScore(7, 3, 0);
		setGameScore(7, 4, 0);
		setGameScore(7, 5, 0);
		setGameScore(7, 6, 0);
		setGameScore(7, 7, 0);
	}

	// update highlights on possible moves and scores
	public void updateBoardInfo() {

		int p1score = 0;
		int p2score = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 1)
					p1score++;
				if (board[i][j] == 2)
					p2score++;

				if (Game_Settings.Valid_Move(board, turn, i, j)) {
					cells[i][j].highlight = 1;
				} else {
					cells[i][j].highlight = 0;
				}
			}
		}

		score1.setText(player1.playerName() + " : " + p1score);
		score2.setText(player2.playerName() + " : " + p2score);
	}

	public void updateTotalScore() {
		tscore1.setText(player1.playerName() + " : " + totalscore1);
		tscore2.setText(player2.playerName() + " : " + totalscore2);
	}

	@Override
	public void handleClick(int i, int j) {
		if (awaitForClick && Game_Settings.Valid_Move(board, turn, i, j)) {

			// update board
			board = Game_Settings.update_Board(board, new Point(i, j), turn);

			// advance turn
			turn = (turn == 1) ? 2 : 1;

			repaint();

			awaitForClick = false;

			// callback
			manageTurn();
		}
	}

	public void handleAI(Player_Info ai) {
		Point aiPlayPoint = ai.play(board);
		int i = aiPlayPoint.x;
		int j = aiPlayPoint.y;
		if (!Game_Settings.Valid_Move(board, ai.value, i, j))
			System.err.println("FATAL : AI Invalid Move !");

		// update board
		board = Game_Settings.update_Board(board, aiPlayPoint, turn);

		// advance turn
		turn = (turn == 1) ? 2 : 1;

		repaint();
	}

}

