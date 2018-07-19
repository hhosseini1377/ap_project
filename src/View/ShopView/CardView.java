package View.ShopView;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CardView {

    private VBox frame = new VBox(0);

    private ImageView cardImage;
    private VBox mainVBox;
    private HBox nameHBox = new HBox(0);
    private HBox kindHBox = new HBox(0);
    private Card card;


    public Card getCard() {
        return card;
    }

    public CardView(double width, double height, Image image, Card card, double x, double y, boolean isBig) {
        this.card = card;


        frame.setPrefSize(width, height);
        frame.setStyle("-fx-background-image: url(/Files/Images/cardBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");
        frame.setAlignment(Pos.CENTER);

        cardImage = new ImageView(image);
        cardImage.setFitWidth(width * 5 / 8);
        cardImage.setFitHeight(height * 11 / 24);

        mainVBox = new VBox(height / 24);
        mainVBox.setLayoutX(width / 8);
        mainVBox.setLayoutY(height / 24);

        mainVBox.setPrefSize(width * 6 / 8, height * 21 / 24);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        HBox mainNameHBox = new HBox(width * 1 / 8);
        mainNameHBox.setMaxSize(width * 6 / 8, height/24);

//        Effect nameBoxShadow = new DropShadow(3, 1, 2, Color.BLACK);
//        mainNameHBox.setEffect(nameBoxShadow);

        nameHBox.setMaxSize(width * 3 / 8, height / 24);
        nameHBox.setAlignment(Pos.CENTER_RIGHT);
        Text nameText = new Text(card.getName());
        if (!isBig) {
            nameText.setStyle("-fx-font-size: 10");
        }else {
            nameText.setStyle("-fx-font-size: 20");
        }
        nameHBox.getChildren().add(nameText);

        kindHBox.setMaxSize(width * 2 / 8, height / 24);
        nameHBox.setAlignment(Pos.CENTER_LEFT);
        Text kindText = new Text(getKind(card));
        if (!isBig) {
            kindText.setStyle("-fx-font-size: 10");
        }else {
            kindText.setStyle("-fx-font-size: 20");
        }
        kindHBox.getChildren().add(kindText);

        Label detailsText = null;

        if (isBig){
            detailsText = new Label(card.toString());
        }else {
            if (card instanceof Monster) {
                detailsText = new Label(("  HP:" + ((Monster) card).getHP() + "\n" + "  AP:" + ((Monster) card).getAP()));
                detailsText.setMinSize(width * 3 / 4, height * 1 / 4);
            }else {
                detailsText = new Label(card.toString());
                detailsText.setMinSize(width * 3 / 4, height * 1 / 4);
            }
        }

        mainNameHBox.getChildren().addAll(nameHBox, kindHBox);



        mainVBox.getChildren().addAll(mainNameHBox, cardImage, detailsText);
        frame.getChildren().addAll( mainVBox);


    }

    public VBox getMainVBox() {
        return frame;
    }

    public String getKind (Card card){
        if (card instanceof Spell){
            return "SpellCard";
        }else {
            return "salam";
        }
    }

}
