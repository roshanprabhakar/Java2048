package com.roshanp;

public class Board {

    public static final int rows = 4;
    public static final int columns = 4;

    int[][] board = new int[columns][rows];
    private final int[] initChoices = new int[]{2, 4};

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

    //upwards action
    public void upShift() {
        moveAllUp();
        addUp();
        moveAllUp(); //edge-case: three alike in a column with one space
    }

    private void moveAllUp() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    for (int k = i; k < rows; k++) {
                        if (board[k][j] != 0) {
                            swap(k, j, i, j);
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

    //downwards action
    public void downShift() {
        moveAllDown();
        addDown();
        moveAllDown();
    }

    private void moveAllDown() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (board[i][j] == 0) {
                    for (int k = i; k >= 0; k--) {
                        if (board[k][j] != 0) {
                            swap(k, j, i, j);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void addDown() {
        for (int i = rows - 1; i > 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (board[i][j] == board[i - 1][j]) {
                    board[i][j] += board[i][j];
                    board[i - 1][j] = 0;
                }
            }
        }
    }

    public void leftShift() {
        moveAllLeft();
        addLeft();
        moveAllLeft();
    }

    private void moveAllLeft() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    for (int k = j; k < columns; k++) {
                        if (board[i][k] != 0) {
                            swap(i, j, i, k);
                            break;
                        }
                    }
                }
            }
        }
    }


    private void addLeft() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    board[i][j] += board[i][j];
                    board[i][j + 1] = 0;
                }
            }
        }
    }

    public void rightShift() {
        moveAllRight();
        addRight();
        moveAllRight();
    }

    private void moveAllRight() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (board[i][j] == 0) {
                    for (int k = j; k >= 0; k--) {
                        if (board[i][k] != 0) {
                            swap(i, j, i, k);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void addRight() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 1; j--) {
                if (board[i][j] == board[i][j - 1]) {
                    board[i][j] += board[i][j];
                    board[i][j - 1] = 0;
                }
            }
        }
    }


    private void swap(int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != 0) {
                    System.out.print(board[i][j] + " " /* + ", (i = " + i + ", j = " + j + ") "*/);
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
