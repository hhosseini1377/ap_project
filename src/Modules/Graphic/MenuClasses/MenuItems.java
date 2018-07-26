package Modules.Graphic.MenuClasses;

import Control.GameControll.GameControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class MenuItems{
    public static int number = 0;

    public MenuItems(Text text, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    text.setStyle("-fx-font-size: 25; -fx-font-family: Purisa;");
                    text.setFill(Color.rgb(229,223,160));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                    text.setFill(Color.CORNSILK);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    switch (text.getText()){
                        case "Single Player":
                            try {
                                Menu.getInstance().dynamicMenu();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "MultiPlayer":
                            break;
                        case "Custom Game":
                            break;
                        case "Save Game":
                            break;
                        case "Settings":
                            try {
                                Menu.getInstance().settings();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Reset Game":
                            gameControl.resetGame();
                            break;
                        case "About Game":
                            break;
                        case "Sound":
                            if (!Graphics.isMute) {
                                Graphics.getInstance().getMusicPlayer().stop();
                                Graphics.isMute = true;
                            }
                            else {
                                Graphics.getInstance().getMusicPlayer().play();
                                Graphics.isMute = false;
                            }
                            break;
                    }
                }
            }
        };
        text.addEventHandler(MouseEvent.ANY, mouseEvent);
    }

    public MenuItems(ImageView exit, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    exit.setFitHeight(85);
                    exit.setFitWidth(75);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    exit.setFitHeight(80);
                    exit.setFitWidth(70);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    gameControl.endGame();
                }
            }
        };
        exit.addEventHandler(MouseEvent.ANY, mouseEvent);
    }
}

