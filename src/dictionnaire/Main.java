package dictionnaire;

import java.util.LinkedList;

import dictionnaire.LexiNode;
import graphics.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Constants;

public class Main extends Application {
	
	Stage window;
	static LexiNode dictionary;

	public static void main(String[] args) {
		
		dictionary = new LexiNode();
		
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = new MainWindow(dictionary);
		window.show();
	}

}
