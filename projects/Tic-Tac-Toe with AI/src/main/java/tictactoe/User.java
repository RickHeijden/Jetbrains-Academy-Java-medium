package tictactoe;

import java.util.Scanner;

public class User implements Player {

    private final Grid grid;
    private final Scanner scanner;
    private final char symbol;

    public User(Grid grid, Scanner scanner, char symbol) {
        this.grid = grid;
        this.scanner = scanner;
        this.symbol = symbol;
    }

    @Override
    public void turn() {
        while (true) {
            System.out.print("Enter the coordinates:");
            try {
                int row = Integer.parseInt(scanner.next()) - 1;
                int column = Integer.parseInt(scanner.next()) - 1;

                if (row < 0 || row > 2 || column < 0 || column > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } else if (grid.isOccupied(row, column)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                grid.setCell(symbol, row, column);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
