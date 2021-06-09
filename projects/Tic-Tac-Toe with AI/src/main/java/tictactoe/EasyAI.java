package tictactoe;

public class EasyAI extends RandomAI {

    public EasyAI(Grid grid, char symbol) {
        super(grid, symbol);
    }

    @Override
    public void turn() {
        System.out.println("Making move level \"easy\"");
        super.turn();
    }
}
