package View.Custom;

import Control.GameControll.GameControl;
import Modules.Card.Monsters.General;
import Modules.CustomCard.CustomGeneralCard;
import Modules.CustomCard.CustomSpellType;
import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GeneralCardCreating {
    private static CustomGeneralCard customGeneralCard;
    private static CustomSpellType battleCryType;
    private static CustomSpellType willType;
    public static void CreateScene(GameControl gameControl){
        GridPane gridPane = new GridPane();
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
        gridPane.add(labelHP,0, 4);

        TextField textFieldHP = new TextField();
        textFieldHP.setPromptText("hp");
        gridPane.add(textFieldHP, 1, 4);

        Button allFBattleCry = new Button("all friendly battleCry");
        Button allEBattleCry = new Button("all enemy battleCry");
        Button randomFBattleCry = new Button ("random friendly battleCry");
        Button randomEBattleCry = new Button("random enemy battleCry");


        gridPane.add(allFBattleCry, 0 ,5);
        gridPane.add(allEBattleCry, 1, 5);
        gridPane.add(randomFBattleCry, 2,5);
        gridPane.add(randomEBattleCry, 3, 5);

        allEBattleCry.setOnMouseClicked(event -> battleCryType = CustomSpellType.ChangeAllEnemy);
        allFBattleCry.setOnMouseClicked(event -> battleCryType = CustomSpellType.ChangeAllFriendly);
        randomEBattleCry.setOnMouseClicked(event -> battleCryType = CustomSpellType.ChangeRandomEnemy);
        randomFBattleCry.setOnMouseClicked(event -> battleCryType = CustomSpellType.ChangeRandomFriendly);

        Button allFWill = new Button("all friendly battleCry");
        Button allEWill = new Button("all enemy battleCry");
        Button randomFWill = new Button ("random friendly battleCry");
        Button randomEWill = new Button("random enemy battleCry");


        gridPane.add(allFWill, 0 ,5);
        gridPane.add(allEWill, 1, 5);
        gridPane.add(randomFWill, 2,5);
        gridPane.add(randomEWill, 3, 5);

        allEWill.setOnMouseClicked(event -> willType = CustomSpellType.ChangeAllEnemy);
        allFWill.setOnMouseClicked(event -> willType = CustomSpellType.ChangeAllFriendly);
        randomEWill.setOnMouseClicked(event -> willType = CustomSpellType.ChangeRandomEnemy);
        randomFWill.setOnMouseClicked(event -> willType = CustomSpellType.ChangeRandomFriendly);

        Button finishButton = new Button("finished");
        gridPane.add(finishButton, 0,6);

        finishButton.setOnMouseClicked(event -> {
            customGeneralCard = new CustomGeneralCard(textFieldName.getText(), Integer.parseInt(textFieldAP.getText()), Integer.parseInt(textFieldHP.getText()), Integer.parseInt(textFieldManaPoint.getText()), Integer.parseInt(textFieldGilCost.getText()));
            SpellCreating.CreateScene("battleCry",battleCryType, customGeneralCard, "general:battleCry");
            SpellCreating.CreateScene("Will", willType, customGeneralCard, "general:will");
            gameControl.getUser().getCardInventory().add(customGeneralCard);
        });



    }
}
