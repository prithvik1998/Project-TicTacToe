import javafx.scene.control.Label;

public class NextState  implements StateMachineState{

    SimplePane simplePane;

    public NextState(SimplePane s)
    {
        simplePane = s;
    }

    public void onEnterState(char playerTurn, Label label) {
        // Display whose turn
        playerTurn = (playerTurn == 'X') ? 'O' : 'X';
        simplePane.setPlayerTurn(playerTurn);
        label.setText(playerTurn + "'s turn");
    }
}
