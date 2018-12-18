package graphics;

import java.io.File;
import java.util.LinkedList;

import dictionnaire.LexiNode;
import dictionnaire.LexiWord;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Constants;

/**
 * This class is used to display the main application window and
 * create it's components, also to regulate how they interact
 * with each other.
 * 
 * @author Etienne Audet-Cobello
 *
 */
public class MainWindow extends Stage {
	
	LexiWord selectedWord;
	LexiNode dictionary;
	ListView<LexiWord> listPotentialWords;
	ListView<LexiWord> listAllWords;
	TextField textFieldWord;
	TextField textFieldWordDefinition;
	
	/**
	 * Constructor.
	 * 
	 * This constructor initializes a new MainWindow stage.
	 * 
	 * @param dictionary the dictionnary the application is using
	 */
	public MainWindow(LexiNode dictionary) {
		this.setTitle(Constants.APP_NAME);
		
		BorderPane layout = new BorderPane();
		
		layout.setTop(createTopBox());
		layout.setLeft(createLeftBox());
		layout.setCenter(createCenterBox());
		layout.setRight(createRightBox());
		layout.setBottom(createBottomBox());
		
		Scene scene = new Scene(layout, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		this.setScene(scene);
		
		this.dictionary = dictionary;
	}
	
	/**
	 * Create the box in the MainWindow's layout.
	 * 
	 * @return Node the box to be added to the layout
	 */
	private Node createBottomBox() {
		HBox bottomBox = new HBox();
		bottomBox.setPadding(new Insets(5,12,5,12));
		bottomBox.setAlignment(Pos.CENTER);
		
	    Button buttonAddEdit = new Button(Constants.BUTTON_ADD_EDIT);
	    buttonAddEdit.prefWidthProperty().bind(bottomBox.widthProperty());
	    
	    buttonAddEdit.setOnAction(e -> addOrModifyWord());
	    
	    bottomBox.getChildren().addAll(buttonAddEdit);

	    return bottomBox;
	}

	/**
	 * Create the box in the MainWindow's layout.
	 * 
	 * @return Node the box to be added to the layout
	 */
	private Node createRightBox() {
		VBox rightBox = new VBox();
		rightBox.setPadding(new Insets(15,12,15,12));
		rightBox.setSpacing(10);
		rightBox.setAlignment(Pos.TOP_CENTER);
	    
	    listAllWords = new ListView<>();
	    listAllWords.setPrefWidth(150);
	    listAllWords.prefHeightProperty().bind(rightBox.heightProperty());
	    
	    rightBox.getChildren().addAll(listAllWords);
	    
	    listAllWords.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	            selectedWord = listAllWords.getSelectionModel().getSelectedItem();
	            
	            if (selectedWord != null) {
		            textFieldWordDefinition.setText(selectedWord.getDefenition());
		            textFieldWord.setText(selectedWord.getWord());
	            }
	        }
	    });

	    return rightBox;
	}

	/**
	 * Create the box in the MainWindow's layout.
	 * 
	 * @return Node the box to be added to the layout
	 */
	private Node createCenterBox() {
		HBox centerBox = new HBox();
		centerBox.setPadding(new Insets(15,12,15,12));
		centerBox.setSpacing(10);
		
	    textFieldWordDefinition = new TextField();
	    textFieldWordDefinition.prefWidthProperty().bind(centerBox.widthProperty());
	    textFieldWordDefinition.prefHeightProperty().bind(centerBox.heightProperty());
	    textFieldWordDefinition.setAlignment(Pos.TOP_LEFT);
	    
	    centerBox.getChildren().addAll(textFieldWordDefinition);

	    return centerBox;
	}

	/**
	 * Create the box in the MainWindow's layout.
	 * 
	 * @return Node the box to be added to the layout
	 */
	private Node createLeftBox() {
		VBox leftBox = new VBox();
		leftBox.setPadding(new Insets(15,12,15,12));
		leftBox.setSpacing(10);
		leftBox.setAlignment(Pos.TOP_CENTER);
	    
	    listPotentialWords = new ListView<>();
	    
	    listPotentialWords.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	            selectedWord = listPotentialWords.getSelectionModel().getSelectedItem();
	            
	            if (selectedWord != null) {
		            textFieldWordDefinition.setText(selectedWord.getDefenition());
	            }
	        }
	    });
	   
	    textFieldWord = new TextField();
	    textFieldWord.setPrefSize(300, 30);
	    textFieldWord.textProperty().addListener((obs, oldText, newText) -> {
	    	selectedWord = null;
	    	textFieldWordDefinition.setText(null);
	    	listPotentialWords.getItems().clear();
	    	
	    	LinkedList<LexiWord> potentialWords = dictionary.Search(newText);
	    	
	        for (LexiWord word : potentialWords) {
	        	listPotentialWords.getItems().add(word);
			}
	    });
	    
	    leftBox.getChildren().addAll(textFieldWord, listPotentialWords);

	    return leftBox;
	}

	/**
	 * Create the box in the MainWindow's layout.
	 * 
	 * @return Node the box to be added to the layout
	 */
	public HBox createTopBox() {
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15,12,15,12));
		topBox.setSpacing(10);
		topBox.setStyle("-fx-background-color: #336699;");
		topBox.setAlignment(Pos.CENTER);
		
	    Button buttonLoad = new Button(Constants.BUTTON_LOAD);
	    buttonLoad.setPrefSize(100, 20);
	    
	    buttonLoad.setOnAction(e -> openFileChooser());

	   
	    Button buttonSave = new Button(Constants.BUTTON_SAVE);
	    buttonSave.setPrefSize(100, 20);
	    
	    buttonSave.setOnAction(e -> openFileSaver());
	    
	    topBox.getChildren().addAll(buttonLoad, buttonSave);

	    return topBox;
	}

	/**
	 * Creates a FileChooser which is used to select a dictionary file to get
	 * words from.
	 */
	private void openFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Dictionnary File");
        File file = fileChooser.showOpenDialog(this);
        
        if (file == null) { return; };
        
        dictionary = new LexiNode();
        
        listAllWords.getItems().clear();
        	
        dictionary.LoadFile(file.getAbsolutePath());
        
        LinkedList<LexiWord> words = new LinkedList<LexiWord>();
        words = dictionary.allWords(dictionary, words);
        
        for (LexiWord word : words) {
			listAllWords.getItems().add(word);
		}
	}
	
	/**
	 * Creates a FileChooser which is used to save a dictionary file with
	 * new or modified words.
	 */
	private void openFileSaver() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dictionnary File");
        File file = fileChooser.showSaveDialog(this);
        if (file != null) {
            dictionary.saveFile(file.getAbsolutePath());
        }
	}
	
	/**
	 * Adds a new word to the dictionary or modifies an existing
	 * word's definition depending on if it is in the dictionary or not.
	 */
	private void addOrModifyWord() {
		if (textFieldWord.getText() == "") return;
		if (textFieldWordDefinition.getText() == "") return;
		
		LexiWord newOrModifiedWord = new LexiWord(textFieldWord.getText(), textFieldWordDefinition.getText());
		
		dictionary.findNewWordBranch(newOrModifiedWord, 0);
		
		listAllWords.getItems().clear();
		
        LinkedList<LexiWord> words = new LinkedList<LexiWord>();
        words = dictionary.allWords(dictionary, words);
        
        for (LexiWord word : words) {
			listAllWords.getItems().add(word);
        }
	}
}
