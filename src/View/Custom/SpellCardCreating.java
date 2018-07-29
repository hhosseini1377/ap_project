package View.Custom;

import Modules.Card.Spell.Spell;
import Modules.Card.Spell.SpellType;
import Modules.CustomCard.CustomSpellCard;
import Modules.CustomCard.CustomSpellType;
import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SpellCardCreating {
    private static CustomSpellCard spellCard;
    private static CustomSpellType customSpellType;
    private static SpellType spellType;
    public static CustomSpellCard CreateScene (){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textTitle = new Text("SpellCard");
        textTitle.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        gridPane.add(textTitle , 0 , 0);

        Label SpellName = new Label( " name");
        gridPane.add(SpellName, 0 , 1);

        TextField textFieldSpellName = new TextField();
        textFieldSpellName.setPromptText("name");
        gridPane.add(textFieldSpellName, 1 , 1);

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

        Label labelSpellType = new Label( "spell type");
        gridPane.add(labelSpellType, 0 , 4);

        Button auraButton = new Button("Aura");
        Button instantButton = new Button("Instant");
        Button continuousButton = new Button("Continuous");

        gridPane.add(auraButton, 1, 4);
        gridPane.add(instantButton, 2, 4);
        gridPane.add(continuousButton, 3, 4);

        auraButton.setOnMouseClicked(event -> spellType = SpellType.AURA);
        instantButton.setOnMouseClicked(event -> spellType = SpellType.INSTANT);
        continuousButton.setOnMouseClicked(event -> spellType = SpellType.CONTINUOUS);

        Button allF = new Button("all friendly");
        Button allE = new Button("all enemy");
        Button randomF = new Button ("random friendly");
        Button randomE = new Button("random enemy");


        gridPane.add(allF, 0 ,5);
        gridPane.add(allE, 1, 5);
        gridPane.add(randomF, 2,5);
        gridPane.add(randomE, 3, 5);

        allE.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeAllEnemy);
        allF.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeAllFriendly);
        randomE.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeRandomEnemy);
        randomF.setOnMouseClicked(event -> customSpellType = CustomSpellType.ChangeRandomFriendly);

        Button finish = new Button("finish");

        gridPane.add(finish,0 , 6);

        finish.setOnMouseClicked(event -> {
            spellCard = new CustomSpellCard(SpellCreating.CreateScene("spell", customSpellType) ,textFieldSpellName.getText(), Integer.parseInt(textFieldManaPoint.getText()), Integer.parseInt(textFieldGilCost.getText()), spellType);
        });


        Graphics.getInstance().getStage().setScene(new Scene(gridPane));

        return spellCard;

    }
}