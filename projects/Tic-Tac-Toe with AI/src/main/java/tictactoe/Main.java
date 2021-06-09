package tictactoe;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(scanner);
        ticTacToe.run();
        scanner.close();
    }
}
