package View.BattleGroundView;

import Modules.Card.Card;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HandView {
    private HBox hand;

    public HBox getHand () {
        return hand;
    }

    public void setHand (HBox hand) {
        this.hand = hand;
    }

    public void addToHand(Card card){
        card.getCardImage().setFitWidth(50);
        card.getCardImage().setFitHeight(80);
        hand.getChildren().add(card.getCardImage());
    }
}
