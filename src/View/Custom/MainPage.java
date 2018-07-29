package View.Custom;

import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class MainPage {
    public static void showMainPage(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textCardType = new Text("Card Type");
        gridPane.add(textCardType,0, 0);

        Button buttonSpellCard = new Button("SpellCard");
        gridPane.add(buttonSpellCard, 0 ,1);
        Button buttonHeroCard = new Button("HeroCard");
        gridPane.add(buttonHeroCard, 0 , 2);
        Button buttonSpellCasterCard = new Button("SpellCasterCard");
        gridPane.add(buttonSpellCasterCard, 0 , 3);
        Button buttonGeneralCard = new Button("General Card");
        gridPane.add(buttonGeneralCard, 0, 4);
        Button buttonNormalCard = new Button("CustomNormalCard");
        gridPane.add(buttonNormalCard,0 , 5);

        buttonSpellCard.setOnMouseClicked(event -> {
            SpellCardCreating.CreateScene();
            //TODO
        });
        buttonGeneralCard.setOnMouseClicked(event -> {

        });
        buttonHeroCard.setOnMouseClicked(event -> {

        });
        buttonNormalCard.setOnMouseClicked(event -> {
            NormalCardCreating.CreateScene();
        });
        buttonSpellCasterCard.setOnMouseClicked(event -> {

        });

        Graphics.getInstance().getStage().setScene(new Scene(gridPane));
    }
}
