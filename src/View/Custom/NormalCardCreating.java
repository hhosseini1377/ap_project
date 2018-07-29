package View.Custom;

import Control.GameControll.GameControl;
import Modules.Card.Monsters.Monster;
import Modules.CustomCard.CustomNormalCard;
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

public class NormalCardCreating {
    private static CustomNormalCard customNormalCard;
    public static void CreateScene(GameControl gameControl){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textTitle = new Text("NormalCard");
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

        Button finishButton = new Button("finished");
        gridPane.add(finishButton, 0,5);

        finishButton.setOnMouseClicked(event -> {
            customNormalCard = new CustomNormalCard(textFieldName.getText(), Integer.parseInt(textFieldAP.getText()), Integer.parseInt(textFieldHP.getText()), Integer.parseInt(textFieldManaPoint.getText()), Integer.parseInt(textFieldGilCost.getText()));
            gameControl.getUser().getCardInventory().add(customNormalCard);
        });



    }
}
