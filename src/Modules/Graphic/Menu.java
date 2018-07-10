package Modules.Graphic;

import Control.GameControll.GameControl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Menu {
    private static Menu menu = new Menu();

    private Menu(){
    }

    public void startGame(GameControl gameControl) throws IOException{
        Stage primaryStage = Graphics.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/startScreen.fxml"));
        Graphics.getInstance().setMainScene(new Scene(root, Graphics.screenWidth,
                Graphics.screenHeight));
        primaryStage.setScene(Graphics.getInstance().getMainScene());
        primaryStage.setTitle("PELOPONNESIAN WAR");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        Graphics.startMusicPlayer.setCycleCount(Animation.INDEFINITE);
        Graphics.startMusicPlayer.play();

        Text text = (Text)root.lookup("#beginText");
        text.setEffect(Graphics.reflection);
        Timeline fadeAway = new Timeline(new KeyFrame(Duration.millis(0.1), event -> {
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
    }

    public void mainMenu(){

    }

    public static Menu getInstance(){
        return menu;
    }
}

class OpaciyChange{
    static double opacityChange = -0.001;
    final static String[] text = {"with High Performance...", "Represented by:" +
            "\nSajad the Gilga\nHossein the Haja\nKasra the Nigga", ""};
    static int turn = 0;
}