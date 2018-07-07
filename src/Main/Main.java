package Main;

import Control.GameControll.GameControl;
import Modules.Graphic.Graphics;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Files/Resources/startScreen.fxml"));
        Graphics.getInstance().setMainScene(new Scene(root, Graphics.screenWidth,
                Graphics.screenHeight));
        Graphics.getInstance().setStage(primaryStage);
        primaryStage.setScene(Graphics.getInstance().getMainScene());
        primaryStage.setTitle("PELOPONNESIAN WAR");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        Graphics.startPlayer.setCycleCount(Animation.INDEFINITE);
        Graphics.startPlayer.play();

        Text text = (Text)root.lookup("#beginText");
        text.setEffect(Graphics.reflection);
        Timeline fadeAway = new Timeline(new KeyFrame(Duration.millis(3.2), event -> {
            text.setOpacity(text.getOpacity() + OpaciyChange.opacityChange);
            if (text.getOpacity() < 0.01 && OpaciyChange.opacityChange < 0){
                text.setText(OpaciyChange.text[OpaciyChange.turn]);
                OpaciyChange.turn++;
                if (OpaciyChange.turn == 1)
                    text.setFont(Font.font(60));
                if (OpaciyChange.turn == 2) {
                    text.setEffect(null);
                    text.setFont(Font.font(50));
                }else if(OpaciyChange.turn == 3){
                    GameControl gameControl = new GameControl("./src/Files/save/");
                    try {
                        gameControl.startGame();
                    }catch (IOException e){
                        System.out.println("problem in input of files");
                    }
                }
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }else if(text.getOpacity() > 0.99 && OpaciyChange.opacityChange > 0){
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }
        }));
        fadeAway.setCycleCount(5000);
        fadeAway.play();

//        gameControl.game();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private boolean menu(){
        return true;
    }
}

class OpaciyChange{
    public static double opacityChange = -0.001;
    public final static String[] text = {"with High Performance...", "Represented by:" +
            "\nSajad the Gilga\nHossein the Haja\nKasra the Nigga", ""};
    public static int turn = 0;
}