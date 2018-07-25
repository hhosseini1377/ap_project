package View.BattleGroundView;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GraveYardView {
    private ImageView graveyardView;

    public ImageView getGraveyardView () {
        return graveyardView;
    }

    public void setGraveyardView (ImageView graveyardView) {
        this.graveyardView = graveyardView;
    }

    public void viewGraveyard(Warrior owner){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Graveyard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        //changing scene
        Graphics.getInstance().setGraveyard(new Scene(root));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getGraveyard());
        //setting up the back button
        Button back = (Button) root.lookup("#backButton");
        EventHandler<MouseEvent> backHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    Graphics.getInstance().getStage().setScene(Graphics.getInstance().getBattle());
                    Graphics.getInstance().getStage().setFullScreen(true);
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    back.setEffect(new Glow(.5));
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    back.setEffect(null);
            }
        };
        back.addEventHandler(MouseEvent.ANY, backHandler);
        Graphics.getInstance().getStage().setFullScreen(true);

        //resizing the card holder vbox according to the page
        VBox cardHolder = (VBox) root.lookup("#cardHolder");
        cardHolder.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT - 50);
        cardHolder.setMaxSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT - 50);

        int cardNum = owner.getGraveYard().getDestroyedCards().size();
        VBox holderCol1 = new VBox(40);
        VBox holderCol2 = new VBox(40);
        VBox holderCol3 = new VBox(40);
        VBox holderCol4 = new VBox(40);
        VBox holderCol5 = new VBox(40);
        for (int i = 0; i < cardNum; i++){
            switch (i%5){
                case 0:
                    holderCol1.getChildren().add(owner.getGraveYard()
                            .getDestroyedCards().get(i)
                            .getCardView().getMainVBox());
                    break;
                case 1:
                    holderCol2.getChildren().add(owner.getGraveYard()
                            .getDestroyedCards().get(i)
                            .getCardView().getMainVBox());
                    break;
                case 2:
                    holderCol3.getChildren().add(owner.getGraveYard()
                            .getDestroyedCards().get(i)
                            .getCardView().getMainVBox());
                    break;
                case 3:
                    holderCol4.getChildren().add(owner.getGraveYard()
                            .getDestroyedCards().get(i)
                            .getCardView().getMainVBox());
                    break;
                case 4:
                    holderCol5.getChildren().add(owner.getGraveYard()
                            .getDestroyedCards().get(i)
                            .getCardView().getMainVBox());
                    break;
            }
        }
    }
}
