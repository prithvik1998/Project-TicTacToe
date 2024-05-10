import javafx.scene.control.Label;

public class WonState implements StateMachineState{

    SimplePane simplePane;

    public WonState(SimplePane s)
    {
        simplePane = s;
    }

    @Override
    public void onEnterState(char p, Label label) {
        label.setText(p + " won!");
        simplePane.setPlayerTurn(' ');
    }
}
