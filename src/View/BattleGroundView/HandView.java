package View.BattleGroundView;

import Modules.Card.Card;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;

public class HandView {
    private HBox hand;

    public HBox getHand () {
        return hand;
    }

    public void setHand (HBox hand) {
        this.hand = hand;
    }

    public void addToHand(Card card, boolean isOpponent){
        if (hand.getChildren().contains(card.getCardImage1())) {
            card.setCardImage1(card.renew().getCardImage1());
        }
        card.getCardImage1().setFitWidth(50);
        card.getCardImage1().setFitHeight(80);
        hand.getChildren().add(card.getCardImage1());
        int size = hand.getChildren().size();
        double rotation = (isOpponent?1:-1)*30;
        for (Node node: hand.getChildren()){
            node.setRotate(rotation);
            double trans = (isOpponent?-1:1) * (rotation >= 0 ? rotation : -rotation);
            node.setTranslateY(trans*20.0/30);
            node.setTranslateX(-rotation*10.0/30);
            rotation = rotation + (isOpponent?-1:1) * 60.0/(size-1);
        }
    }

    public void removeFromHand(Card card, boolean isOpponent){
        if (hand.getChildren().contains(card.getCardImage1())){
            hand.getChildren().remove(card.getCardImage1());
            hand.getChildren().add(card.getCardImage1());
            int size = hand.getChildren().size();
            double rotation = (isOpponent?1:-1)*30;
            for (Node node: hand.getChildren()){
                node.setRotate(rotation);
                double trans = (isOpponent?-1:1) * (rotation >= 0 ? rotation : -rotation);
                node.setTranslateY(trans*20.0/30);
                node.setTranslateX(-rotation*10.0/30);
                rotation = rotation + (isOpponent?-1:1) * 60.0/(size-1);
            }
        }
    }
}
