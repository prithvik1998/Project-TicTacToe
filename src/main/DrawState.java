import javafx.scene.control.Label;

public class DrawState implements StateMachineState{

    SimplePane simplePane;

    public DrawState(SimplePane s)
    {
        simplePane = s;
    }

    @Override
    public void onEnterState(char p, Label label) {
        label.setText("Draw!");
        simplePane.setPlayerTurn(' ');
    }
}
