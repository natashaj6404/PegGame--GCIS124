package main.java.Assignment1;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PegUI extends Application {

    public static Stage MainStage;
    public static Button load;
    public static Button  save;
    public static Button exist;
    public static Label statusLabel;
    public static GridPane boardPane;

    /**
     * This method will start the GUI for the peg game
     * 
     * @param MainStage the main stage for the GUI
     */
    @Override
    public void start(Stage MainStage) {
        PegUI.MainStage = MainStage;
        MainStage.setTitle("Peg Game ");
        

        //  labels and Buttons 
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.LAVENDERBLUSH);
        rect.setStroke(Color.BLACK);
        // title 

        Label titleLabel = new Label("Welcome To the Peg Game ");
        titleLabel.setStyle("-fx-font-size: 45px; -fx-font-weight: bold;");
        // rules
        Label rules = new Label(
                "The game board is a square with pegs in all holes except one.\n" +
                "Only vertical and horizontal jumps allowed.\n" +
                "The game is over when no more moves can be made.");
        // load, exit, save
        load = new Button("Load Game");
        Button exit = new Button("Exit");
        save = new Button("Save Game");
        save.setVisible(false); // this button shouldnt initially show 
        
        statusLabel = new Label();

        load.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        exit.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        save.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Create the board
        boardPane = new GridPane();
        boardPane.setPadding(new Insets(16));
        boardPane.setHgap(5);
        boardPane.setVgap(5);
        boardPane.setAlignment(Pos.CENTER);
        boardPane.setGridLinesVisible(false);

        Label ispeg = new Label("Peg = ");
        Label isnotpeg = new Label("Not Peg = ");

        Circle e_peg = new Circle(20);
        e_peg.setFill(Color.BLACK);
        Circle e_notpeg = new Circle(20);
        e_notpeg.setFill(Color.WHITE);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(load, save);

        HBox pegIndicators = new HBox(10);
        pegIndicators.setAlignment(Pos.CENTER);
        pegIndicators.getChildren().addAll(ispeg, e_peg, isnotpeg, e_notpeg);

        // Create VBox for title, rules, and buttons
        VBox vbox = new VBox(20); // Adjust spacing between nodes
        vbox.setAlignment(Pos.CENTER); // Center align content
        vbox.getChildren().addAll(titleLabel, rules, hbox ,statusLabel,pegIndicators,boardPane);

        // Create VBox for exit button
        VBox ButtomVBox = new VBox(20);
        ButtomVBox.getChildren().addAll(exit);

        // Create BorderPane for layout
        BorderPane root = new BorderPane();
        root.setTop(vbox); // Center VBox with title, rules, and buttons
        root.setBottom(ButtomVBox); // Align Exit button to the bottom

        root.setStyle("-fx-background-color: lightblue;");

        // Set up the scene
        Scene scene = new Scene(root, 800, 600); // Adjusted scene size for visibility
        MainStage.setScene(scene);
        MainStage.show();

        // Setting the event handelers
        load.setOnAction(e -> {PEG_GUI.loadGame();});
        exit.setOnAction(e -> {PEG_GUI.exitGame();});
        save.setOnAction(e -> {PEG_GUI.saveGame();});
    }

    /**
     * This method will return the main stage
     * 
     * @return the main stage
     */
    public static Stage getMainStage() {
        return PegUI.MainStage;
    }

    /**
     * Displays an error dialog with the specified message.
     *
     * @param message the error message to display
     * @param title   the title of the error dialog
     * 
     * @return void
     */
    public static void PopUp(String message,String title) {
        Stage PopStage = new Stage();
        PopStage.initOwner(getMainStage());
        PopStage.setTitle(title);

        Label PopLabel = new Label(message);
        PopLabel.setWrapText(true); // Allow text to wrap to multiple lines
        Button okButton = new Button("OK");

        okButton.setOnAction(e -> PopStage.close());

        VBox Layout_UP = new VBox(16);
        Layout_UP.setAlignment(Pos.CENTER);
        Layout_UP.setPadding(new Insets(16));
        Layout_UP.getChildren().addAll(PopLabel, okButton);

        Scene PopScene = new Scene(Layout_UP);
        PopStage.setScene(PopScene);
        PopStage.sizeToScene(); // Adjust the window size based on the content
        PopStage.showAndWait();
    }

    /**
     * Method to set the visibility of a button
     * 
     * @param b the button to set the visibility of
     * @param v the visibility to set
     * 
     * @return void
     */
    public static void setVisibility(Button b , boolean v){
        if (v == true)
        b.setVisible(true);
        else
        b.setVisible(false);
        }

    /**
     * mehtod to make a single tile on the board
     */
    public static Node makeTile(int i) {
        Rectangle rect = new Rectangle(50, 50);

        //move to the left
        rect.setTranslateX(-5);

        if (i % 2 == 0) {
            rect.setFill(Color.LIGHTGRAY);
            rect.setStroke(Color.BLACK);
        } else {
            rect.setFill(Color.GREEN);
        }

        return rect;
    }

    /**
     * This method will add pegs to the board
     * 
     * @param boardPane
     * @param board
     */
    public static void UpdateBoard(GridPane boardPane, boolean[][] board, Location selectedLocation){
        boardPane.getChildren().clear();
        int BoardSize = PEG_GUI.game.BOARD_SIZE;
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                final int row = i; // Create a final copy of i
                final int col = j; // Create a final copy of j
                Node tile = makeTile(2);
                Circle circle = new Circle(20);
                if (board[i][j] == true) {
                    circle.setOnMouseClicked(e -> PEG_GUI.select_peg(row, col)); // Use the final copy of i
                    if (selectedLocation != null && selectedLocation.getRow() == i && selectedLocation.getCol() == j) {
                        tile = makeTile(1);
                    } else {
                        circle.setFill(Color.BLACK);
                    }
                } else {
                    circle.setFill(Color.WHITE);
                    circle.setStroke(Color.BLACK);
                    circle.setOnMouseClicked(e -> PEG_GUI.select_peg(row, col)); // Use the final copy of i
                }
                boardPane.add(tile, j, i);
                boardPane.add(circle, j, i);
            }
        }
        statusLabel.setText("Pegs Remaining: " + PEG_GUI.game.pegs);
    }

    /**
     * main method to launch the GUI
     * 
     * @param args
     * 
     * @return void
     */
    public static void main(String[] args) {
        launch(args);
    }
}
