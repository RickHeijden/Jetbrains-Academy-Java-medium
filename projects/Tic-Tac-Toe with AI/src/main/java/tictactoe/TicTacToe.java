package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private static final String EMPTY_GRID = "_________".replaceAll("_",
            String.valueOf(Characters.EMPTY.getCharacter()));
    private final Scanner scanner;

    public TicTacToe(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("Input command:");
            String command = scanner.nextLine();
            if (command.contains("start")) {
                Grid grid = new Grid(EMPTY_GRID);
                grid.printGrid();

                String[] commands = command.split(" ");
                if (commands.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }

                if (game(grid, Arrays.copyOfRange(commands, 1, 3))) {
                    grid.printResult();
                }
            } else if (command.equals("exit")) {
                break;
            }
        }
    }

    public boolean game(Grid grid, String[] commands) {
        List<Player> players = getPlayers(grid, commands);

        if (players.size() != 2 || players.contains(null)) {
            System.out.println("Bad parameters!");
            return false;
        }

        Player xPlayer = players.get(0);
        Player oPlayer = players.get(1);

        while (!grid.isFinished()) {
            xPlayer.turn();
            grid.printGrid();
            if (grid.isFinished()) {
                break;
            }
            oPlayer.turn();
            grid.printGrid();
        }
        if (xPlayer instanceof User || oPlayer instanceof User) {
            scanner.nextLine();
        }
        return true;
    }

    public List<Player> getPlayers(Grid grid, String[] playerList) {
        List<Player> players = new ArrayList<>();
        char symbol = Characters.X.getCharacter();
        for (String player : playerList) {
            switch (player) {
            case "easy":
                players.add(new EasyAI(grid, symbol));
                break;
            case "user":
                players.add(new User(grid, scanner, symbol));
                break;
            case "medium":
                players.add(new MediumAI(grid, symbol));
                break;
            case "hard":
                players.add(new HardAI(grid, symbol));
                break;
            }
            symbol = symbol == Characters.X.getCharacter() ? Characters.O.getCharacter() : Characters.X.getCharacter();
        }
        return players;
    }
}
