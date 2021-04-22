import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


public class board extends Application {


    private SimpleObjectProperty<Color> playerColor = new SimpleObjectProperty<Color>(Color.RED);
    private int row;
    private int column;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final BorderPane board = new BorderPane();
        final GridPane gridpane = new GridPane();
        primaryStage.setTitle("Connect Four");
        primaryStage.setResizable(true);

        Scene scene = new Scene(board, 1000, 740, true);
        scene.setFill(Color.BLACK);

        gridpane.setTranslateY(30);
        gridpane.setAlignment(Pos.CENTER);

        gridpane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));
        gridpane.getRowConstraints().addAll(
                new RowConstraints(100),
                new RowConstraints(100),
                new RowConstraints(100),
                new RowConstraints(100),
                new RowConstraints(100),
                new RowConstraints(100));

        createGrids(gridpane);

        board.setCenter(gridpane);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();



    }

    //Add Column and Row
    private void addGrid(final GridPane gridpane){
        gridpane.getColumnConstraints().addAll(new ColumnConstraints(100));
        gridpane.getRowConstraints().addAll(new RowConstraints(100));
        createGrids(gridpane);
    }



    //Create Grids
    private void createGrids(final GridPane gridpane){
        //gridpane.getChildren().clear();
        for(row=0;row<gridpane.getColumnConstraints().size(); row++){
            for(column=0; column<=gridpane.getColumnConstraints().size(); column++){

                Rectangle rect = new Rectangle(100,100);
                Circle circ = new Circle(47);
                circ.centerXProperty().set(50);
                circ.centerYProperty().set(50);
                Shape boardPiece = Path.subtract(rect, circ);
                boardPiece.setFill(Color.BLUE);
                boardPiece.setStroke(Color.BLUE);
                boardPiece.setOpacity(100);


                final Circle bottomToken = new Circle(40);

                final Circle token = new Circle(40);
                token.fillProperty().bind(playerColor);

                token.setTranslateY(-(100*(row+1)));


                final TranslateTransition DropDown = new TranslateTransition(Duration.millis(200), token);

                //allows pieces to be placed from top row
                token.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent arg0) {
                        if(token.getTranslateY()!=(-0)){
                            DropDown.setToY(0);
                            DropDown.play();
                            if(playerColor.get()==Color.RED){
                                playerColor.set(Color.YELLOW);
                                token.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
                            }else{
                                playerColor.set(Color.RED);
                                token.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
                            }
                        }
                    }
                });


                //allows pieces to be placed on bottom row
                bottomToken.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent arg0) {
                        if(token.getTranslateY()!=0){
                            DropDown.setToY(0);
                            DropDown.play();
                            if(playerColor.get()==Color.RED){
                                playerColor.set(Color.YELLOW);
                                token.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
                            }else{
                                playerColor.set(Color.RED);
                                token.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
                            }
                        }
                    }
                });


                StackPane fullGame = new StackPane();
                fullGame.getChildren().addAll(boardPiece, bottomToken, token);
                gridpane.add(fullGame, column, row);

            }
        }
    }
}
