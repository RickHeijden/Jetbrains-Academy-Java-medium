package tictactoe;

public class MediumAI extends RandomAI {

    public MediumAI(Grid grid, char symbol) {
        super(grid, symbol);
    }

    @Override
    public void turn() {
        System.out.println("Making move level \"medium\"");
        int[] move = getNecessaryMove();
        if (move[0] == -1 || move[1] == -1) {
            super.turn();
        } else {
            grid.setCell(symbol, move[0], move[1]);
        }
    }

    private int[] getNecessaryMove() {
        int[] move = new int[]{-1, -1};
        for (int i = 0; i < 3; i++) {
            int row = 0;
            int col = 0;
            int rowOcc = -1;
            int colOcc = -1;
            for (int j = 0; j < 3; j++) {
                row += grid.getGrid()[i][j];
                col += grid.getGrid()[j][i];
                rowOcc = !grid.isOccupied(i, j) ? j : rowOcc;
                colOcc = !grid.isOccupied(j, i) ? j : colOcc;
            }
            if (row == 208 || row == 190) {
                move = new int[]{i, rowOcc};
                break;
            } else if (col == 208 || col == 190) {
                move = new int[]{colOcc, i};
                break;
            }
        }
        if (move[0] == -1 || move[1] == -1) {
            int center = grid.getGrid()[1][1];
            int diag1 = grid.getGrid()[0][0] + grid.getGrid()[2][2] + center;
            int diag2 = grid.getGrid()[0][2] + grid.getGrid()[2][0] + center;
            if (diag1 == 208 || diag1 == 190) {
                for (int i = 0; i < 3; i++) {
                    if (grid.getGrid()[i][i] == 32) {
                        move = new int[]{i, i};
                        break;
                    }
                }
            } else if (diag2 == 208 || diag2 == 190) {
                if (center == 32) {
                    move = new int[]{1, 1};
                } else if (grid.getGrid()[0][2] == 32) {
                    move = new int[]{0, 2};
                } else {
                    move = new int[]{2, 0};
                }
            }
        }
        return move;
    }
}
