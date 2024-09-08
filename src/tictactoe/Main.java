package tictactoe;

import tictactoe.Grid;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("choose grid size: ");
        int size = scanner.nextInt();
        Grid grid = new Grid(size);
        String state = grid.getState();
        grid.display();
        while (state == "Game unfinished") {
            grid.promptUser();
            grid.display();
            state = grid.getState();



        }
        System.out.println(state);

    }
}




