package Othello.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Window extends JFrame {
	private JRadioButton easyRadioButton;
	private JRadioButton hardRadioButton;
	private ButtonGroup difficultyButtonGroup;
	private JButton humanVSaiButton;
	private JButton aiVSaiButton;
	private JButton humanVShumanButton;
	private Board board;

	public Main_Window() {
		// Set up the form
		setTitle("Game Options");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10)); // Using BorderLayout with spacing

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout()); // Using GridBagLayout for centered placement

		// Create the difficulty radio buttons
		easyRadioButton = new JRadioButton("Easy");
		hardRadioButton = new JRadioButton("Hard");
		easyRadioButton.doClick();
		difficultyButtonGroup = new ButtonGroup();
		difficultyButtonGroup.add(easyRadioButton);
		difficultyButtonGroup.add(hardRadioButton);

		// Create the start buttons
		humanVSaiButton = new JButton("Human VS AI");
		aiVSaiButton = new JButton("AI VS AI");
		humanVShumanButton = new JButton("Human VS Human");

		humanVSaiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String difficulty = easyRadioButton.isSelected() ? "Easy" : "Hard";
				dispose();
				board = new Board(difficulty, "Ai Vs Human");
				createGameFrame();
			}
		});

		humanVShumanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String difficulty = easyRadioButton.isSelected() ? "Easy" : "Hard";
				dispose();
				board = new Board(difficulty, "Human Vs Human");
				createGameFrame();
			}
		});

		aiVSaiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String difficulty = easyRadioButton.isSelected() ? "Easy" : "Hard";
				dispose();
				board = new Board(difficulty, "Ai Vs Ai");
				createGameFrame();
			}
		});

		// Add components to the center panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);

		centerPanel.add(new JLabel("Difficulty:"), gbc);

		gbc.gridy = 1;
		centerPanel.add(easyRadioButton, gbc);

		gbc.gridy = 2;
		centerPanel.add(hardRadioButton, gbc);

		gbc.gridy = 3;
		centerPanel.add(new JLabel(), gbc); // Empty label for spacing

		gbc.gridy = 4;
		centerPanel.add(humanVSaiButton, gbc);

		gbc.gridy = 5;
		centerPanel.add(aiVSaiButton, gbc);

		gbc.gridy = 6;
		centerPanel.add(humanVShumanButton, gbc);

		// Add the center panel to the frame
		add(centerPanel, BorderLayout.CENTER);
	}

	private void createGameFrame() {
	}

}
