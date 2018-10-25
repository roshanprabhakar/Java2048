package com.roshanp;

import java.util.Scanner;

public class Main {

    public static Board board = new Board();
    public static final int winning_value = 64;

    public static void main(String[] args) {
        System.out.println("Welcome to " + winning_value + "!");
        board.display();
        while (!board.winner(64)) {
            playerTurn();
        }
        System.out.println("You won " + winning_value + "!");
    }

    public static void playerTurn() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Your move: ");
        String playerChoice = scanner.nextLine();


        if (playerChoice.equals("w")) {
            board.upShift();
        } else if (playerChoice.equals("s")) {
            board.downShift();
        } else if (playerChoice.equals("a")) {
            board.leftShift();
        } else if (playerChoice.equals("d")) {
            board.rightShift();
        } else if (playerChoice.equals("display")) {
            board.display();
            return;
        } else {
            System.out.println("not a valid move (w, a, s, d, and display are valid)");
            return;
        }
        board.spawnRandom();
        board.display();
    }
}
