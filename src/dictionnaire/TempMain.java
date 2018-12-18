package dictionnaire;

import java.util.LinkedList;

import dictionnaire.LexiNode;
import graphics.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Constants;

public class TempMain extends Application {
	
	Stage window;
	static LexiNode dictionary;

	public static void main(String[] args) {
		
		dictionary = new LexiNode();
		
		dictionary.LoadFile("D:\\Workspace\\git\\dictionnaire\\Dictio.txt");
		
		LexiWord word1 = new LexiWord("test", "tester de quoi");
		LexiWord word2 = new LexiWord("tester", "tester de quoi");
		LexiWord word3 = new LexiWord("testorons", "tester de quoi");

		LexiWord rngword = new LexiWord("sdafasdfasd", "tester de quoi");

		
		dictionary.findNewWordBranch(word1, 0);
		dictionary.findNewWordBranch(word2, 0);
		dictionary.findNewWordBranch(word3, 0);
		dictionary.findNewWordBranch(word1, 0);
		
		// Start graphical interface.
		
		LinkedList<LexiWord> test  = dictionary.Search("sdafasdfasd");
		if (test != null) {
			for (LexiWord word : test) {
				System.out.println(word.getWord());
			}
		}
		
		
		System.out.println("-----------------------------------------");
		
		LexiWord word4 = dictionary.SearchSingleWord("test");
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
		word4 = dictionary.SearchSingleWord("test");
		System.out.println(word4.getDefenition());
		
		
		dictionary.saveFile("D:\\Workspace\\git\\dictionnaire\\Dictio.txt");
		
		dictionary = new LexiNode();
		
		launch(args);

	}


	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = new MainWindow(dictionary);
		window.show();
	}

}
