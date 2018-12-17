package graphics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Constants;

public class MainWindow extends Stage {
	
	public MainWindow() {
		this.setTitle(Constants.APP_NAME);
		
		BorderPane layout = new BorderPane();
		
		layout.setTop(createTopBox());
		layout.setLeft(createLeftBox());
		layout.setCenter(createCenterBox());
		layout.setRight(createRightBox());
		layout.setBottom(createBottomBox());
		
		Scene scene = new Scene(layout, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		this.setScene(scene);
	}
	
	private Node createBottomBox() {
		HBox bottomBox = new HBox();
		bottomBox.setPadding(new Insets(5,12,5,12));
		bottomBox.setAlignment(Pos.CENTER);
		
	    Button buttonAddEdit = new Button(Constants.BUTTON_ADD_EDIT);
	    buttonAddEdit.prefWidthProperty().bind(bottomBox.widthProperty());
	    
	    bottomBox.getChildren().addAll(buttonAddEdit);

	    return bottomBox;
	}

	private Node createRightBox() {
		VBox rightBox = new VBox();
		rightBox.setPadding(new Insets(15,12,15,12));
		rightBox.setSpacing(10);
		rightBox.setAlignment(Pos.TOP_CENTER);
	    
	    ListView<String> listAllWords = new ListView<>();
	    listAllWords.setPrefWidth(150);
	    listAllWords.prefHeightProperty().bind(rightBox.heightProperty());
	    
	    rightBox.getChildren().addAll(listAllWords);

	    return rightBox;
	}

	private Node createCenterBox() {
		HBox centerBox = new HBox();
		centerBox.setPadding(new Insets(15,12,15,12));
		centerBox.setSpacing(10);
		
	    TextField textFieldWordDefinition = new TextField();
	    
	    textFieldWordDefinition.prefWidthProperty().bind(centerBox.widthProperty());
	    textFieldWordDefinition.prefHeightProperty().bind(centerBox.heightProperty());
	    
	    textFieldWordDefinition.setAlignment(Pos.TOP_LEFT);
	    
	    centerBox.getChildren().addAll(textFieldWordDefinition);

	    return centerBox;
	}

	private Node createLeftBox() {
		VBox leftBox = new VBox();
		leftBox.setPadding(new Insets(15,12,15,12));
		leftBox.setSpacing(10);
		leftBox.setAlignment(Pos.TOP_CENTER);
		
	    TextField textFieldWord = new TextField();
	    textFieldWord.setPrefSize(300, 30);
	    
	    ListView<String> listPotentialWords = new ListView<>();
	    listPotentialWords.getItems().addAll("Wassup", "Man", "Yeahhh");
	    
	    leftBox.getChildren().addAll(textFieldWord, listPotentialWords);

	    return leftBox;
	}

	public HBox createTopBox() {
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15,12,15,12));
		topBox.setSpacing(10);
		topBox.setStyle("-fx-background-color: #336699;");
		topBox.setAlignment(Pos.CENTER);
		
	    Button buttonLoad = new Button(Constants.BUTTON_LOAD);
	    buttonLoad.setPrefSize(100, 20);

	    Button buttonSave = new Button(Constants.BUTTON_SAVE);
	    buttonSave.setPrefSize(100, 20);
	    
	    topBox.getChildren().addAll(buttonLoad, buttonSave);

	    return topBox;
	}

}
