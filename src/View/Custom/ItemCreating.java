package View.Custom;

import Control.GameControll.GameControl;
import Control.ShopControl;
import Modules.CustomCard.CustomItem;
import Modules.Graphic.Graphics;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class ItemCreating {
    private static CustomItem customItem;
    public static void createScene(GameControl gameControl){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10 , 10));

        Text textTitle = new Text("Item");
        textTitle.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        gridPane.add(textTitle , 0 , 0);

        Label SpellName = new Label( " name");
        gridPane.add(SpellName, 0 , 1);

        TextField textFieldName = new TextField();
        textFieldName.setPromptText("name");
        gridPane.add(textFieldName, 1 , 1);

        Label manaChange = new Label( "mana Change");
        gridPane.add(manaChange, 0 , 2);

        TextField textFieldManaChange = new TextField();
        textFieldManaChange.setPromptText("mana point");
        gridPane.add(textFieldManaChange, 1 , 2);

        Label labelGilCost = new Label("gil cost");
        gridPane.add(labelGilCost, 0, 3);

        TextField textFieldGilCost =  new TextField();
        textFieldGilCost.setPromptText("gil cost");
        gridPane.add(textFieldGilCost, 1, 3);

        Label labelHPChange = new Label("HP Change");
        gridPane.add(labelHPChange,0, 4);

        TextField textFieldHPChange = new TextField();
        textFieldHPChange.setPromptText("HP change");
        gridPane.add(textFieldHPChange, 1, 4);

        Button buttonFinish = new Button("finished");
        gridPane.add(buttonFinish, 0 , 5);

        buttonFinish.setOnMouseClicked(event -> {
          customItem = new CustomItem(Integer.parseInt(textFieldManaChange.getText()), Integer.parseInt(textFieldHPChange.getText()), Integer.parseInt(textFieldGilCost.getText()), textFieldName.getText());
          gameControl.getUser().getItemInventory().add(customItem);
          customItem.startViews();
          customItem.setItemImage(new ImageView(new Image(new File("./src/Files/Images/Default.png").toURI().toString())));
        });

        Graphics.getInstance().getStage().setScene(new Scene(gridPane));



    }
}
