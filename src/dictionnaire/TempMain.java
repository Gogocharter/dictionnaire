package dictionnaire;

import graphics.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Constants;

public class TempMain extends Application {
	
	Stage window;

	public static void main(String[] args) {
		
		LexiNode dictionary = new LexiNode(' ');
		
		Word word1 = new Word("test", "tester de quoi");
		Word word2 = new Word("tester", "tester de quoi");
		Word word3 = new Word("testorons", "tester de quoi");

		dictionary.findNewWordBranch(word1, 0);
		dictionary.findNewWordBranch(word2, 0);
		dictionary.findNewWordBranch(word3, 0);
		
		// Start graphical interface.
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = new MainWindow();
		window.show();
	}

}
