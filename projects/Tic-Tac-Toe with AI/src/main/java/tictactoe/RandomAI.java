package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RandomAI implements Player {

    protected final Grid grid;
    protected final char symbol;
    private final Random random = new Random();

    public RandomAI(Grid grid, char symbol) {
        this.grid = grid;
        this.symbol = symbol;
    }

    @Override
    public void turn() {
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!this.grid.isOccupied(i, j)) {
                    cells.add(new int[]{i, j});
                }
            }
        }
        int index = random.nextInt(cells.size());
        grid.setCell(symbol, cells.get(index)[0], cells.get(index)[1]);
    }
}
