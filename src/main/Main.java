import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {		
		Component p = SimplePane.getInstance(true);
		p.decorate(); 
		Pane pane = p.getPane();
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		Scene scene = new Scene(borderPane, 600, 600);
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
