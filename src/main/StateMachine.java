public class StateMachine {

    SimplePane simplePane;
    StateMachineState wonState;
    StateMachineState lostState;
    StateMachineState nextState;
    StateMachineState currentState;

    public StateMachine(SimplePane simplePane)
    {
        this.simplePane = simplePane;
        wonState = new WonState(simplePane);
        lostState = new DrawState(simplePane);
        nextState = new NextState(simplePane);
        currentState = nextState ;
    }


    /** Determine if the player with the specified token wins
     * Using Chain Of Responsibility design pattern
     */
    public boolean isWon(char token) {
        Handler row = new CheckForRowsHandler();
        Handler col = new CheckForColumnsHandler();
        Handler diag = new CheckForDiagonalsHandler();

        row.setSuccessor(col);
        col.setSuccessor(diag);

        return row.handleRequest(simplePane, token);
    }

    /** Determine if all the cells are occupied and the game is a draw */
    public boolean isDraw() {
        SimplePane.Cell[][] cell = simplePane.getCells();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cell[i][j].getToken() == ' ')
                    return false;
            }
        }
        return true;
    }

    public void changeState(char playerTurn) {

        // Set token in the cell
        // Check game status
        if (isWon(playerTurn)) {
            currentState = wonState;
            simplePane.getLblStatus().setText(playerTurn + " won!");
        } else if (isDraw()) {
            currentState = lostState;
        } else { // Change the turn
            currentState = nextState;
        }

        currentState.onEnterState(playerTurn, simplePane.getLblStatus());
    }

}
