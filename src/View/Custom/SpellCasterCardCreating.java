package View.Custom;

import Control.GameControll.GameControl;
import Modules.Card.Monsters.Monster;
import Modules.CustomCard.CustomNormalCard;
import Modules.CustomCard.CustomSpellCasterCard;
import Modules.CustomCard.CustomSpellType;
import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class SpellCasterCardCreating {
    private static boolean addToShop;
    private static CustomSpellType customSpellType;
    private static CustomSpellCasterCard customSpellCasterCard;
    public static void CreateScene(GameControl gameControl){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-image: url(Files/Images/CardShopBackground.jpg);");
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textTitle = new Text("SpellCasterCard");
        textTitle.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        gridPane.add(textTitle , 0 , 0);

        Label SpellName = new Label( " name");
        gridPane.add(SpellName, 0 , 1);

        TextField textFieldName = new TextField();
        textFieldName.setPromptText("name");
        gridPane.add(textFieldName, 1 , 1);

        Label manaPoint = new Label( "mana point");
        gridPane.add(manaPoint, 0 , 2);

        TextField textFieldManaPoint = new TextField();
        textFieldManaPoint.setPromptText("mana point");
        gridPane.add(textFieldManaPoint, 1 , 2);

        Label labelGilCost = new Label("gil cost");
        gridPane.add(labelGilCost, 0, 3);

        TextField textFieldGilCost =  new TextField();
        textFieldGilCost.setPromptText("gil cost");
        gridPane.add(textFieldGilCost, 1, 3);

        Label labelAP = new Label("AP");
        gridPane.add(labelAP,0, 4);

        TextField textFieldAP = new TextField();
        textFieldAP.setPromptText("ap");
        gridPane.add(textFieldAP, 1, 4);

        Label labelHP = new Label("HP");
        gridPane.add(labelHP,0, 5);

        TextField textFieldHP = new TextField();
        textFieldHP.setPromptText("hp");
        gridPane.add(textFieldHP, 1, 5);

        Button allF = new Button("all friendly");
        Button allE = new Button("all enemy");
        Button randomF = new Button ("random friendly");
        Button randomE = new Button("random enemy");


        gridPane.add(allF, 0 ,6);
        gridPane.add(allE, 1, 6);
        gridPane.add(randomF, 2,6);
        gridPane.add(randomE, 3, 6);

        allE.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeAllEnemy);
        allF.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeAllFriendly);
        randomE.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeRandomEnemy);
        randomF.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeRandomFriendly);

        Button finishButton = new Button("finished");
        gridPane.add(finishButton, 0,7);

        Button buttonAddToShop = new Button("add to shop");
        gridPane.add(buttonAddToShop,0,8);

        buttonAddToShop.setOnMouseClicked(event -> addToShop = true);

        finishButton.setOnMouseClicked(event -> {
            customSpellCasterCard = new CustomSpellCasterCard(textFieldName.getText(), Integer.parseInt(textFieldAP.getText()), Integer.parseInt(textFieldHP.getText()), Integer.parseInt(textFieldManaPoint.getText()), Integer.parseInt(textFieldGilCost.getText()));
            SpellCreating.CreateScene("Spell", customSpellType, customSpellCasterCard, "spellCaster:spell");
            gameControl.getUser().getCardInventory().add(customSpellCasterCard);
            if (addToShop){
                gameControl.getShopControl().getCardShop().getCards().add(customSpellCasterCard);
            }
            addToShop = false;
            customSpellCasterCard.setCardImage(new Image(new File("./src/Files/Images/Default.png").toURI().toString()));
            customSpellCasterCard.startViews(customSpellCasterCard.getCardImage());
        });
        Graphics.getInstance().getStage().setScene(new Scene(gridPane));
    }
}
