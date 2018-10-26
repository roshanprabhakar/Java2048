package com.roshanp;

public class Board {

    public static final int rows = 4;
    public static final int columns = 4;

    int[][] board = new int[columns][rows];
    private final int[] initChoices = new int[]{2, 4};

    public Board() {
        int x1 = (int) (Math.random() * columns);
        int y1 = (int) (Math.random() * rows);

        int x2 = (int) (Math.random() * columns);
        int y2 = (int) (Math.random() * rows);

        while (x2 != x1 && y2 != y1) {
            x2 = (int) (Math.random() * columns);
            y2 = (int) (Math.random() * rows);
        }

        board[x2][y2] = initChoices[(int) (Math.random() * 2)];
        board[x1][y1] = initChoices[(int) (Math.random() * 2)];
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


    private void addDown() {
        for (int i = rows - 1; i > 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (board[i][j] == board[i - 1][j]) {
                    board[i][j] += board[i][j];
                    board[i - 1][j] = 0;
                }
            }
        }
        moveAllDown();
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
        moveAllLeft();
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
        moveAllRight();
    }

    //upwards action
    public void upShift() {
        moveAllUp();
        addUp();
        moveAllUp(); //edge-case: three alike in a column with one space
    }

    //downwards action
    public void downShift() {
        moveAllDown();
        addDown();
        moveAllDown();
    }

    //leftwards action
    public void leftShift() {
        moveAllLeft();
        addLeft();
        moveAllLeft();
    }

    //rightwards action
    public void rightShift() {
        moveAllRight();
        addRight();
        moveAllRight();
    }

    public boolean winner(int winningValue) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == winningValue) {
                    return true;
                }
            }
        }
        return false;
    }

    public void spawnRandom() {
        int x = (int) (Math.random() * columns);
        int y = (int) (Math.random() * rows);
        while (board[x][y] != 0) {
            x = (int) (Math.random() * columns);
            y = (int) (Math.random() * rows);
        }
        board[x][y] = initChoices[(int) Math.random() * 2];
    }

    private void swap(int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
    }

    public void unusable() {
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

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(board[i][j]);
                }
                for (int x = 0; x < 5 - Integer.toString(board[i][j]).length(); x++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}