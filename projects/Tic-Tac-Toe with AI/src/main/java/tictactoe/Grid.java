package tictactoe;

public class Grid {
    private char[][] grid;
    private Status status;

    public Grid(String grid) {
        setGridString(grid);
        this.status = Status.NOT_FINISHED;
    }

    public boolean isFinished() {
        return this.status != Status.NOT_FINISHED;
    }

    public char[][] getGrid() {
        return grid;
    }

    public char[] getGridArray() {
        return this.toString().toCharArray();
    }

    public void setGridString(String grid) {
        this.grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid.toCharArray(), (i * 3), this.grid[i], 0, 3);
        }
    }

    public boolean isOccupied(int row, int column) {
        return this.grid[row][column] != Characters.EMPTY.getCharacter();
    }

    public void setCell(char symbol, int row, int column) {
        if (isOccupied(row, column)) {
            return;
        }
        this.grid[row][column] = symbol;
        result();
    }

    private void result() {
        if (won(Characters.X.getCharacter())) {
            this.status = Status.XWIN;
        } else if (won(Characters.O.getCharacter())) {
            this.status = Status.OWIN;
        } else if (!contains(Characters.EMPTY.getCharacter())) {
            this.status = Status.DRAW;
        }
    }

    public boolean won(char symbol) {
        for (int i = 0; i < 3; i++) {
            boolean winColumn = true;
            boolean winRow = true;
            for (int j = 0; j < 3; j++) {
                if (winColumn && this.grid[j][i] != symbol) {
                    winColumn = false;
                }
                if (winRow && this.grid[i][j] != symbol) {
                    winRow = false;
                }
            }
            if (winColumn || winRow) {
                return true;
            }
        }

        boolean corners1 = this.grid[0][0] == symbol && this.grid[2][2] == symbol;
        boolean corners2 = this.grid[0][2] == symbol && this.grid[2][0] == symbol;
        return (corners1 || corners2) && this.grid[1][1] == symbol;
    }

    public boolean contains(char symbol) {
        for (char[] row : this.grid) {
            for (char cell : row) {
                if (cell == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printResult() {
        System.out.println(this.status.getMessage());
    }

    public void printGrid() {
        System.out.println("---------");
        String grid = this.toString().replaceAll("_", " ");
        char[] field = grid.toCharArray();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            System.out.print(field[i] + " ");
            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("---------");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : this.grid) {
            for (char cell : row) {
                builder.append(cell);
            }
        }
        return builder.toString();
    }

    private enum Status {
        NOT_FINISHED("Game not finished"),
        XWIN("X wins"),
        OWIN("O wins"),
        DRAW("Draw");

        private final String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
