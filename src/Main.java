import Control.GameControll.GameControl;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 100, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        GameControl gameControl = new GameControl("/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/save/");
        try {
            gameControl.startGame();
        }catch (IOException e){
            System.out.println("problem in input of files");
        }
        launch(args);
        gameControl.game();
    }
}