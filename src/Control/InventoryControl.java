package Control;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.User.Inventory.CardInventory;
import Modules.User.User;
import View.InventoryView.CardInventoryView;
import View.InventoryView.ItemInventoryView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class InventoryControl {
    private final User user;
    private CardInventoryView cardView;
    private ItemInventoryView itemView;

    public InventoryControl (User user) {
        this.user = user;
        cardView = new CardInventoryView(user);
        itemView = new ItemInventoryView(user);
    }

    public void mainThread () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Files/Resources/Inventory.fxml"));
        Graphics.getInstance().setInventory(new Scene(root));
        for (int i = 0; i < 3; i++) {
            Text text = (Text) root.lookup("#text" + (i + 1));
            new InventoryItems(text);
        }
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getInventory());
        Graphics.getInstance().getStage().setFullScreen(true);
    }

    class InventoryItems {

        public InventoryItems (Text text) {
            EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                        text.setStyle("-fx-font-size: 35; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                        text.setStyle("-fx-font-size: 30; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        switch (text.getText()) {
                            case "Card Inventory":
                                try {
                                    cardView.viewInventory();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Item Inventory":
                                itemView.viewItemInventory();
                                break;
                            case "Amulet Inventory":
                                break;

                        }
                    }
                }
            };
            text.addEventHandler(MouseEvent.ANY, onClick);
        }
    }
}
