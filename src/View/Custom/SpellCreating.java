package View.Custom;

import Control.GameControll.GameControl;
import Modules.Card.Card;
import Modules.CustomCard.*;
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

public class SpellCreating {
    private static Scene SpellCreating;
    public static Spell spell;

    public static Spell CreateScene (String title, CustomSpellType customSpellType, Card card, String type){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-image: url(Files/Images/CardShopBackground.jpg);");
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textTitle = new Text(title);
        textTitle.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        gridPane.add(textTitle , 0 , 0);

        Label SpellName = new Label(title + " name");
        gridPane.add(SpellName, 0 , 1);

        TextField textFieldSpellName = new TextField();
        textFieldSpellName.setPromptText("name");
        gridPane.add(textFieldSpellName, 1 , 1);

        Label SpellDetail = new Label(title + " detail");
        gridPane.add(SpellDetail, 0 , 2);

        TextField textFieldSpellDetail = new TextField();
        textFieldSpellDetail.setPromptText("name");
        gridPane.add(textFieldSpellDetail, 1 , 2);

        Label APChange = new Label("AP Change");
        gridPane.add(APChange, 0, 3);

        TextField textFieldAPChange = new TextField();
        textFieldAPChange.setPromptText("ap change");
        gridPane.add(textFieldAPChange, 1, 3);

        Label HPChange = new Label("HP Change");
        gridPane.add(HPChange, 0, 4);

        TextField textFieldHPChange = new TextField();
        textFieldHPChange.setPromptText("hp change");
        gridPane.add(textFieldHPChange, 1, 4);

        Button finishButton = new Button("Finished");
        gridPane.add(finishButton, 0, 5);

        Graphics.getInstance().getStage().setScene(new Scene(gridPane));


        finishButton.setOnMouseClicked(e ->{
            switch (customSpellType){
                case ChangeAllEnemy:
                    spell = new SpellChangeAllEnemy(Integer.parseInt(textFieldAPChange.getText()),
                            Integer.parseInt(textFieldHPChange.getText()),
                            textFieldSpellName.getText(),
                            textFieldSpellDetail.getText());
                    spell.setSpellDetails(textFieldSpellDetail.getText());
                    checkCardSpellType(type, card, spell);
                    break;
                case ChangeAllFriendly:
                    spell = new SpellChangeAllFriendly(Integer.parseInt(textFieldAPChange.getText()),
                            Integer.parseInt(textFieldHPChange.getText()),
                            textFieldSpellName.getText(),
                            textFieldSpellDetail.getText());
                    checkCardSpellType(type, card, spell);
                    break;
                case ChangeRandomEnemy:
                    spell = new SpellChangeRandomEnemy(Integer.parseInt(textFieldAPChange.getText()),
                            Integer.parseInt(textFieldHPChange.getText()),
                            textFieldSpellName.getText(),
                            textFieldSpellDetail.getText());
                    checkCardSpellType(type, card, spell);
                    break;
                case ChangeRandomFriendly:
                    spell = new SpellChangeRandomFriendly(Integer.parseInt(textFieldAPChange.getText()),
                            Integer.parseInt(textFieldHPChange.getText()),
                            textFieldSpellName.getText(),
                            textFieldSpellDetail.getText());
                    checkCardSpellType(type, card, spell);
                    break;
                case ChangeRandomAmount:
                    spell = new SpellChangeRandomAmount(Integer.parseInt(textFieldAPChange.getText()),
                            Integer.parseInt(textFieldHPChange.getText()),
                            textFieldSpellName.getText(),
                            textFieldSpellDetail.getText());
                    checkCardSpellType(type, card, spell);
                    break;
            }
        });
        return spell;

    }

    private static void checkCardSpellType(String type, Card card, Spell spell){
        switch (type.split(":")[0]){
            case "spellCard":
                ((CustomSpellCard) card).setSpell(spell);
                break;
            case "hero":
                if (type.split(":")[1].equals("will"))
                    ((CustomHeroCard) card).setWill(spell);
                else if (type.split(":")[1].equals("battleCry"))
                    ((CustomHeroCard) card).setBattleCry(spell);
                else
                    ((CustomHeroCard) card).setSpell(spell);
                break;
            case "general":
                if (type.split(":")[1].equals("will"))
                    ((CustomGeneralCard) card).setWill(spell);
                else
                    ((CustomGeneralCard) card).setBattleCry(spell);
                break;
            case "spellCaster":
                ((CustomSpellCasterCard)card).setSpell(spell);

                break;
        }

    }

}
