package View.Custom;

import Control.GameControll.GameControl;
import Control.ShopControl;
import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class MainPage {
    public static void showMainPage(GameControl gameControl){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
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
        Button buttonItem = new Button("CustomItem");
        gridPane.add(buttonItem,0,6);
        Button buttonAmulet = new Button("CustomAmulet");
        gridPane.add(buttonAmulet, 0, 7);

        buttonSpellCard.setOnMouseClicked(event -> {
            SpellCardCreating.CreateScene(gameControl);
        });
        buttonGeneralCard.setOnMouseClicked(event -> {
            GeneralCardCreating.CreateScene(gameControl);
        });
        buttonHeroCard.setOnMouseClicked(event -> {
            HeroCardCreating.CreateScene(gameControl);
        });
        buttonNormalCard.setOnMouseClicked(event -> {
            NormalCardCreating.CreateScene(gameControl);
        });
        buttonSpellCasterCard.setOnMouseClicked(event -> {
            SpellCasterCardCreating.CreateScene(gameControl);
        });
        buttonItem.setOnMouseClicked(event -> {
            ItemCreating.createScene(gameControl);
        });

        Graphics.getInstance().getStage().setScene(new Scene(gridPane));
        Graphics.getInstance().getStage().setFullScreen(true);
    }
}
