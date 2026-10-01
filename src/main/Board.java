package main;

import javax.swing.*;
import java.awt.*;

public class Board {

    // Game variables
    private final JButton[][] buttons = new JButton[3][3];
    private final NoughtsAndCrosses game;
    private final JLabel statusLabel;

    public Board(NoughtsAndCrosses controller) {
        // Current game object is this
        this.game = controller;

        // Creating the main window
        JFrame frame = new JFrame("Noughts and Crosses");

        // Creating the playing board
        JPanel centrePanel = new JPanel(new GridLayout(3, 3, 5, 5));
        centrePanel.setBackground(Color.LIGHT_GRAY);
        centrePanel.setPreferredSize(new Dimension(600,600));

        // Creating top panel and game status label
        JPanel topPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("X's Turn");
        statusLabel.setPreferredSize(new Dimension(50, 50));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(statusLabel);

        // Creating bottom panel and reset button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton resetButton = new JButton("New game");
        resetButton.setPreferredSize(new Dimension(50, 50));
        resetButton.setFont(new Font("Arial", Font.BOLD, 30));
        resetButton.addActionListener(e -> this.game.resetGame());
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(resetButton);

        // Looping through 2D array
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {

                // Creating interactable buttons
                buttons[r][c] = new JButton();
                JButton button = buttons[r][c];

                // Customising buttons
                button.setBackground(Color.DARK_GRAY);
                button.setFont(new Font("Arial", Font.BOLD, 55));
                button.setOpaque(true);
                button.setBorderPainted(false);

                // Button functionality
                int row  = r;
                int col = c;
                button.addActionListener(e -> this.game.handleMove(row, col));

                // Adding button to panel
                centrePanel.add(button);
            }
        }

        // Adding components to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centrePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setSize(700, 700);
        frame.pack();

        // Setting the conditions of the frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Setter for the statusLabel
    public void setStatusLabel(String text) {
        statusLabel.setText(text);
    }

    // Putting X or Y on the board
    public void setMove(int r, int c, String symbol) {
        buttons[r][c].setText(symbol);

        if(symbol.equals("X")) {
            buttons[r][c].setForeground(Color.BLUE);
        } else {
            buttons[r][c].setForeground(Color.ORANGE);
        }
    }

    // Condition for making a move
    public boolean isCellEmpty(int r, int c) {
        return buttons[r][c].getText().isEmpty();
    }

    // Condition for draw
    public boolean isBoardFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (isCellEmpty(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Win condition
    public boolean checkWinner() {
        // Rows
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {

                if (!buttons[r][0].getText().isEmpty()
                        && buttons[r][0].getText().equals(buttons[r][1].getText())
                        && buttons[r][1].getText().equals(buttons[r][2].getText())) {

                    highlightButtons(r, 0);
                    highlightButtons(r, 1);
                    highlightButtons(r, 2);
                    return true;
                }
            }
        }

        // Columns
        for (int c = 0; c < buttons[0].length; c++) {
            for (int r = 0; r < buttons.length; r++) {

                if (!buttons[0][c].getText().isEmpty()
                        && buttons[0][c].getText().equals(buttons[1][c].getText())
                        && buttons[1][c].getText().equals(buttons[2][c].getText())) {

                    highlightButtons(0, c);
                    highlightButtons(1, c);
                    highlightButtons(2, c);
                    return true;
                }
            }
        }

        // Diagonals
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {

            highlightButtons(0, 0);
            highlightButtons(1, 1);
            highlightButtons(2, 2);
            return true;
        }

        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {

            highlightButtons(0, 2);
            highlightButtons(1, 1);
            highlightButtons(2, 0);
            return true;
        }

        // If no winner
        return false;
    }

    // Highlighting winner
    private void highlightButtons(int r, int c) {
        buttons[r][c].setBackground(Color.WHITE);
    }

    // Resetting buttons to blank text/colour values
    public void resetBoard() {
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                buttons[r][c].setText("");
                buttons[r][c].setBackground(Color.DARK_GRAY);
            }
        }
    }
}