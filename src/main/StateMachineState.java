import javafx.scene.control.Label;

public interface StateMachineState {
    void onEnterState(char playerTurn, Label label);
}
