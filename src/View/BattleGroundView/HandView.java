package View.BattleGroundView;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void addToHand(Card card, boolean isOpponent){
//        if (hand.getChildren().contains(new ImageView(card.getCardImage()))) {
//            card.setCardImage(card.renew().getCardImage());
//        }
        ImageView cardImageView = null;
        if (isOpponent){
            cardImageView = new ImageView(Graphics.CARD_BACK);
        }else
            cardImageView = new ImageView(card.getCardImage());
        ImageView finalCardImageView = cardImageView;
        cardImageView.setOnMouseEntered(event -> finalCardImageView.setEffect(new Glow(.4)));
        cardImageView.setOnMouseExited(event -> finalCardImageView.setEffect(null));
        cardImageView.setFitWidth(50);
        cardImageView.setFitHeight(80);
        hand.getChildren().add(cardImageView);
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
        if (doesContain(card.getCardImage())){
            ImageView cardImageView;
            if (isOpponent)
                cardImageView = getView(Graphics.CARD_BACK);
            else
                cardImageView = getView(card.getCardImage());
            hand.getChildren().remove(cardImageView);
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

    private boolean doesContain(Image image){
        for(Node node: hand.getChildren()){
            if (((ImageView) node).getImage() == image){
                return true;
            }
        }
        return false;
    }

    private ImageView getView(Image image){
        for(Node node: hand.getChildren()){
            if (((ImageView) node).getImage() == image){
                return (ImageView) node;
            }
        }
        return null;
    }
}