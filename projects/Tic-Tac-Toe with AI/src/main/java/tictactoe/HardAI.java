package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class HardAI implements Player {

    private final char symbol;
    private final Grid game;

    public HardAI(Grid grid, char symbol) {
        this.game = grid;
        this.symbol = symbol;
    }

    @Override
    public void turn() {
        System.out.println("Making move level \"hard\"");
        int[] bestMove = miniMax(game.getGridArray(), this.symbol);
        this.game.setCell(symbol, bestMove[0]/3, bestMove[0]%3);
    }

    public int[] miniMax(char[] grid, char symbol) {
        int[] available = emptyIndexies(grid);

        char reverseSymbol = this.symbol == Characters.O.getCharacter() ? Characters.X.getCharacter() : Characters.O
                .getCharacter();
        if (winning(grid, reverseSymbol)) {
            return new int[]{-1, -10};
        } else if (winning(grid, this.symbol)) {
            return new int[]{-1, 10};
        } else if (available.length == 0) {
            return new int[]{-1, 0};
        }

        List<int[]> moves = new ArrayList<>();
        for (int i : available) {
            int[] move = new int[2];
            grid[i] = symbol;
            move[0] = i;
            char reverse =
                    symbol == Characters.O.getCharacter() ? Characters.X.getCharacter() : Characters.O.getCharacter();
            move[1] = miniMax(grid, reverse)[1];
            grid[i] = Characters.EMPTY.getCharacter();
            moves.add(move);
        }

        int[] bestMove = new int[0];
        if (symbol == this.symbol) {
            int bestScore = -10000;
            for (int[] move : moves) {
                if (move[1] > bestScore) {
                    bestScore = move[1];
                    bestMove = move;
                }
            }
        } else {
            int bestScore = 10000;
            for (int[] move : moves) {
                if (move[1] < bestScore) {
                    bestScore = move[1];
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }

    private int[] emptyIndexies(char[] grid) {
        List<Integer> indexies = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == Characters.EMPTY.getCharacter()) {
                indexies.add(i);
            }
        }
        return indexies.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean winning(char[] grid, char symbol){
        return (grid[0] == symbol && grid[1] == symbol && grid[2] == symbol) ||
                (grid[3] == symbol && grid[4] == symbol && grid[5] == symbol) ||
                (grid[6] == symbol && grid[7] == symbol && grid[8] == symbol) ||
                (grid[0] == symbol && grid[3] == symbol && grid[6] == symbol) ||
                (grid[1] == symbol && grid[4] == symbol && grid[7] == symbol) ||
                (grid[2] == symbol && grid[5] == symbol && grid[8] == symbol) ||
                (grid[0] == symbol && grid[4] == symbol && grid[8] == symbol) ||
                (grid[2] == symbol && grid[4] == symbol && grid[6] == symbol);
    }
}
