package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

class Grid {
    private int size;

    private char[][] container;
    private int xs;
    private int os;
    private String state = "Game unfinished";
    public static final int X_ASCII = 88;
    public static final int O_ASCII = 79;
    public final int X_NUM;
    public final int O_NUM;
    private boolean xxx;
    private boolean ooo;

    Grid(int n) {
        size = n;
        container = new char[n][n];
        for (int i = 0; i < container.length; i++)
            for (int j = 0; j < container.length; j++)
                container[i][j] = ' ';


        X_NUM = container.length * X_ASCII;
        O_NUM = container.length * O_ASCII;
    }

    Grid() {
        container = new char[3][3];
        for (char[] chars : container)
            for (char c : chars)
                c = ' ';
        X_NUM = 3 * X_ASCII;
        O_NUM = 3 * O_ASCII;
    }

    public void display() {
        for (int i = 0; i < Math.pow(container.length, 2); i++)
            System.out.print("-");

        System.out.println();
        for (char[] chars : container) {
            System.out.print("| ");
            for (char c : chars)
                System.out.print(c + " ");
            System.out.println("|");
        }
        for (int i = 0; i < Math.pow(container.length, 2); i++)
            System.out.print("-");


        System.out.println();
    }

    private void play(int i, int j) {

        if (xs == os) {
            container[i][j] = 'X';
            xs++;

        } else {
            container[i][j] = 'O';
            os++;
        }

        updateState();


    }

    private void updateState() {
        int mDiag = 0;
        int aDiag = 0;
        for (int i = 0; i < container.length; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < container.length; j++) {
                row += container[i][j];
                col += container[j][i];

            }
            mDiag += container[i][i];
            aDiag += container[i][container.length - 1 - i];
            if (row == X_NUM || col == X_NUM) xxx = true;
            if (row == O_NUM || col == O_NUM) ooo = true;
        }
        if (mDiag == X_NUM || aDiag == X_NUM) xxx = true;
        if (mDiag == O_NUM || aDiag == O_NUM) ooo = true;
        state = xxx ? "X wins" : ooo ? "O wins" :
                xs + os == ((int) Math.pow(container.length, 2)) ? "Draw" : "Game unfinished";

    }

    public String getState() {
        return state;
    }

    public void promptUser() {
        Scanner scanner = new Scanner(System.in);
        boolean isAvailable = false;
        int i, j;
        while (!isAvailable) {
            try {
                i = scanner.nextInt();
                j = scanner.nextInt();
                if (container[i - 1][j - 1] == ' ') isAvailable = true;
                if (!isAvailable)
                    System.out.println("This cell is occupied! try another one");
                else play(i - 1, j - 1);

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers");
                scanner.nextLine();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("The number should be in the range 1- " + container.length);

            }
        }
    }
}
