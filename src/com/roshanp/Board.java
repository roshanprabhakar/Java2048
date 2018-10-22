package com.roshanp;

public class Board {

    public static final int rows = 4;
    public static final int columns = 4;

    int[][] board = new int[columns][rows];
    public final int[] initChoices = new int[]{2, 4};

    public Board() {
        int x1 = (int) (Math.random() * columns);
        int y1 = (int) (Math.random() * rows);
        board[x1][y1] = initChoices[(int) (Math.random() * 2)];

        int x2 = (int) (Math.random() * columns);
        int y2 = (int) (Math.random() * rows);

        while (x2 != x1 || y2 != y1) {
            x2 = (int) (Math.random() * columns);
            y2 = (int) (Math.random() * rows);
        }

        board[(int) (Math.random() * columns)][(int) (Math.random() * rows)] = initChoices[(int) (Math.random() * 2)];
    }

    //moving everything up
    public void upShift() {
        moveAllUp();
        addUp();
    }

    private void moveAllUp() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    for (int k = i; k < columns; k++) {
                        if (board[k][j] != 0) {
                            swap(i, j, k, j);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void addUp() {
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != 0) {
                    if (board[i + 1][j] == board[i][j]) {
                        board[i][j] = board[i][j] + board[i + 1][j];
                        board[i + 1][j] = 0;
                    }
                }
            }
        }
        moveAllUp();
    }

    //HELPER
    private void swap(int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " "/* + ", (" + i + "," + j + ") "*/);
            }
            System.out.println();
        }
    }
}
