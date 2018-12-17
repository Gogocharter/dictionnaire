package dictionnaire;

import java.util.LinkedList;

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
		
		LexiWord word1 = new LexiWord("test", "tester de quoi");
		LexiWord word2 = new LexiWord("tester", "tester de quoi");
		LexiWord word3 = new LexiWord("testorons", "tester de quoi");

		dictionary.findNewWordBranch(word1, 0);
		dictionary.findNewWordBranch(word2, 0);
		dictionary.findNewWordBranch(word3, 0);
		dictionary.findNewWordBranch(word1, 0);
		
		// Start graphical interface.
		
		LinkedList<LexiWord> test  = dictionary.Search(word1);
		for (LexiWord word : test) {
			System.out.println(word.getWord());
		}
		
		System.out.println("-----------------------------------------");
		
		LexiWord word4 = dictionary.SearchSingleWord(word1);
		System.out.println(word4.getDefenition());
		
		System.out.println("-----------------------------------------");
		
		test = new LinkedList<LexiWord>();
		test = dictionary.allWords(dictionary, test);
		for (LexiWord word : test) {
			System.out.println(word.getWord());
		}
		
		//mod
		System.out.println("----------------UPDATE-------------------------");
		//mot n'existe pas
		LexiWord word6 = new LexiWord("tesfdvadft", "Tester test test tester");
		dictionary.updateLexiWord(word6);
		word4 = dictionary.SearchSingleWord(word1);
		System.out.println(word4.getDefenition());
		
		launch(args);

	}


	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = new MainWindow();
		window.show();
	}

}
