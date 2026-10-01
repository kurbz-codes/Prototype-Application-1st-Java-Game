package main;

import javax.swing.*;
import java.awt.*;

public class NoughtsAndCrosses {
    // Instantiating classes/creating local variables
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private Player currentPlayer;
    private boolean gameOver = false;

    // Creating objects from other classes
    public NoughtsAndCrosses() {
        board = new Board(this);
        playerX = new Player("X");
        playerO = new Player("O");
        currentPlayer = playerX;
    }

    // Game playing logic
    public void handleMove(int r, int c) {
        // Condition to play
        if(!board.isCellEmpty(r, c) || gameOver) {
            return;
        }
        board.setMove(r, c, currentPlayer.getSymbol());

        // Condition to stop playing
        if(checkWinner()) {
            board.setStatusLabel(currentPlayer.getSymbol() + " Won The Game!");
            gameOver = true;
            return;
        }

        // Condition for draw
        if(board.isBoardFull()) {
            board.setStatusLabel("Draw!");
            gameOver = true;
            return;
        }

        // Continuing game if no conditions met
        switchPlayer();
        board.setStatusLabel(currentPlayer.getSymbol() + "'s Turn");
    }

    // Reset button function
    public void resetGame() {
        if(gameOver) {
            board.resetBoard();
            gameOver = false;
            board.setStatusLabel(currentPlayer.getSymbol() + "'s Turn");
        }
    }

    // Keeping the game logic in control
    private boolean checkWinner() {
        return board.checkWinner();
    }

    // Method for switching player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }
}