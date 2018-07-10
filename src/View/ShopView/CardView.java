package View.ShopView;

import Modules.Card.Card;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardView {

    private ImageView imageView;
    private VBox mainVBox;
    private HBox name$tribeHbox = new HBox(0);


    public CardView(double width, double height, Image image, Card card) {
        imageView = new ImageView(image);

        mainVBox = new VBox(height / 24);

        mainVBox.setPrefSize(width * 6 / 8, height * 21 / 24);
        mainVBox.setAlignment(Pos.CENTER);

        name$tribeHbox.setPrefSize(width * 6 / 8, height / 24);
        Text nameText = new Text(card.getName());
//        nameText.setTextAlignment();
//        name$tribeHbox.getChildren().addAll()
    }

}
