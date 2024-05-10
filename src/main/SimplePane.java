import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class SimplePane extends BorderPane implements Component {
	private Cell[][] cell = new Cell[3][3];
	private char playerTurn = 'X';
	private boolean isAdvancedMode;
	private static Component theSimplePane;
	StateMachine stateMachine ;
	private Label lblStatus = new Label("X's turn to play");

	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent a) {
			((ICommand) a.getSource()).Execute();
		}
	};

	public static Component getInstance(boolean isAdvancedMode) {
		if(theSimplePane == null)
		{
			if (isAdvancedMode) {
				theSimplePane = new AdvancedPane(new SimplePane(isAdvancedMode));
			} else {
				theSimplePane = new StandardPane(new SimplePane(isAdvancedMode));
			}
		}
		return  theSimplePane;
	}

	private SimplePane(boolean bool) {
		isAdvancedMode = bool;
		stateMachine = new StateMachine(SimplePane.this);
	}

	@Override
	public void decorate() {
		GridPane gp = new GridPane();
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				cell[i][j] = new Cell(i, j);
				gp.add(cell[i][j], i, j);
			}
		}
		this.setCenter(gp);
		this.setBottom(lblStatus);
	}
	
	@Override
	public void addButtons(CommandButton... buttons) {
		FlowPane fp = new FlowPane();
		fp.getChildren().addAll(buttons);

		for (CommandButton b : buttons) {
			b.setPane(this);
			b.setOnAction(ae);
		}

		this.setTop(fp);
	}

	public void newGame() {
		lblStatus = new Label("X's turn to play");
		playerTurn = 'X';

		CommandButton.careTaker.getUndoArrayList().clear();
		CommandButton.careTaker.getRedoArrayList().clear();

		decorate();
	}

	public void newToggle() {
		theSimplePane  = new StandardPane(new SimplePane(isAdvancedMode));
	}

	public void setPane(char shape, int i, int j) {
		playerTurn = (playerTurn == 'X') ? 'O' : 'X';
		// Display whose turn
		lblStatus.setText(playerTurn + "'s turn");
		cell[i][j].setToken(shape);
	}

	public boolean checkCells() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j] != null)
					return false;
			}
		}
		return true;
	}

	@Override
	public Pane getPane() {
		return this;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public Cell[][] getCells() {
		return cell;
	}

	public void setPlayerTurn(char x) {
		playerTurn = x;
	}

	// An inner class for a cell
	public class Cell extends Pane { // Token used for this cell
		private char token = ' ';
		private int row;
		private int column;

		public Cell(int i, int j) {
			this.row = i;
			this.column = j;

			setStyle("-fx-border-color: white");
			this.setPrefSize(2000, 2000);
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		/* Handle a mouse click event */
		private void handleMouseClick() { 
			// If cell is empty and game is not over
			if (token == ' ' && playerTurn != ' ') {
				setToken(playerTurn);
				stateMachine.changeState(playerTurn);
				if (isAdvancedMode)
				{
					CommandButton.originator.setState(playerTurn, row, column);
					CommandButton.careTaker.addToUndoList(CommandButton.originator.saveStateToMemento());
					for (int i = 0; i < CommandButton.careTaker.getMementoRedoListSize(); i++) 
					{
						CommandButton.careTaker.removeLastRedo();
					}
				}
			}
		}

		/** Set a new token */
		public void setToken(char c) {
			token = c;
			if (token == 'X') {
				Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));

				Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
				line2.startYProperty().bind(this.heightProperty().subtract(10));
				line2.endXProperty().bind(this.widthProperty().subtract(10));

				this.getChildren().addAll(line1, line2); // Add the lines to the pane

			} else if (token == 'O') {
				Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
				ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				ellipse.setStroke(Color.WHITE);
				ellipse.setFill(Color.BLACK);

				this.getChildren().add(ellipse); // Add the ellipse to the pane
			} else if (token == ' ') {
				this.getChildren().clear();
			}
		}

		/** Return token */
		public char getToken() {
			return token;
		}
	}
}
