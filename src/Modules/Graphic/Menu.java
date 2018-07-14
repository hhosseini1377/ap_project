package Modules.Graphic;

import Control.GameControll.GameControl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Menu {
    private GameControl gameControl;
    private static Menu menu = new Menu();
    private Hero hero;

    private Menu(){
    }

    public static Menu getInstance(){
        return menu;
    }

    public void startScreen (GameControl gameControl) throws IOException{
        loadScreenXML();
        Graphics.START_MUSIC_PLAYER.setCycleCount(Animation.INDEFINITE);
        Graphics.START_MUSIC_PLAYER.play();

        ImageCursor imageCursor = new ImageCursor(Graphics.CURSOR_IMAGE, 30, 30);
        Graphics.getInstance().getMainScene().setCursor(imageCursor);

        Parent root = Graphics.getInstance().getMainScene().getRoot();
        Text text = (Text)root.lookup("#beginText");
        text.setEffect(Graphics.REFLECTION);
        Timeline fadeAway = new Timeline(new KeyFrame(Duration.millis(.2), event -> {
            text.setOpacity(text.getOpacity() + OpaciyChange.opacityChange);
            if (text.getOpacity() < 0.01 && OpaciyChange.opacityChange < 0){
                text.setText(OpaciyChange.text[OpaciyChange.turn]);
                OpaciyChange.turn++;
                if (OpaciyChange.turn == 1)
                    text.setFont(Font.font("Nazli", 60));
                if (OpaciyChange.turn == 2) {
                    text.setEffect(null);
                    text.setFont(Font.font("Nazli", 50));
                }else if(OpaciyChange.turn == 3){
                    try {
                        gameControl.startGame();
                    }catch (IOException e){
                        System.out.println("problem in input of files");
                    }
                }
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }else if(text.getOpacity() > 0.99 && OpaciyChange.opacityChange > 0){
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }
        }));
        fadeAway.setCycleCount(5000);
        fadeAway.play();
    }

    private void loadScreenXML() throws IOException {
        Stage primaryStage = Graphics.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/startScreen.fxml"));
        Graphics.getInstance().setMainScene(new Scene(root, Graphics.SCREEN_WIDTH,
                Graphics.SCREEN_HEIGHT));
        primaryStage.setScene(Graphics.getInstance().getMainScene());
        primaryStage.setTitle("PELOPONNESIAN WAR");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    public String getNameFromUser()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/RegisterPage.fxml"));
        Graphics.getInstance().getStage().getScene().setRoot(root);
        StringBuilder name = new StringBuilder("MrNobody");

        Button register = ((Button)root.lookup("#registerButton"));
        String buttonCss = "-fx-border-radius: 60;" +
                " -fx-background-radius: 20;" +
                " -fx-min-width: 40px;" +
                " -fx-min-height: 35px;" +
                " -fx-font-weight: bold;";
        register.setEffect(Graphics.GLOW);

        EventHandler<MouseEvent> onButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    register.setStyle("-fx-background-color: rgba(137,137,137,0.17);" + buttonCss);
                    register.setTextFill(Color.rgb(45, 45, 45));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    register.setStyle("-fx-background-color: rgba(115,115,115,0.75);" + buttonCss);
                    register.setTextFill(Color.ALICEBLUE);
                }
                else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    name.replace(0, 8, ((TextField)root.lookup("#textfield")).getText());
                    if (!name.toString().equals(""))
                        gameControl.getUser().setName(name.toString());
                    try {
                        gameControl.game();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        register.addEventHandler(MouseEvent.ANY, onButton);
        return name.toString();
    }

    public void mainMenu() throws IOException {
        loadMenuXML();
        Parent root = Graphics.getInstance().getMenu().getRoot();

        Text welcome = ((Text)root.lookup("#welcome"));
        welcome.setEffect(Graphics.SHADOW);
        welcome.setText(welcome.getText() + gameControl.getUser().getName());

        addEventHandler();
            }

    private void loadMenuXML() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/MainMenu.fxml"));
        Scene scene = Graphics.getInstance().getMainScene();
        Graphics.getInstance().setMenu(scene);
        scene.setRoot(root);
    }

    private void addEventHandler(){
        Parent root = Graphics.getInstance().getMenu().getRoot();
        Text[] texts = new Text[7];
        for (int i = 0; i < 7; i++) {
            texts[i] = (Text) root.lookup("#menuText" + (i+1));
            new MenuItems(texts[i], gameControl);
        }
        new MenuItems((ImageView) root.lookup("#exitImage"), gameControl);
        ((VBox)root.lookup("#greeting")).maxWidthProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1.0));
        ((VBox)root.lookup("#greeting")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.4));
        ((VBox)root.lookup("#mainMenu")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5/2));
        ((VBox)root.lookup("#exit")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.5));

        EventHandler<KeyEvent> up_Down = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.UP){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number > 0) {
                        MenuItems.number--;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.DOWN){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number != 6) {
                        MenuItems.number++;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.ENTER){
                    switch (texts[MenuItems.number].getText()){
                        case "Single Player":
                            try {
                                dynamicMenu();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "MultiPlayer":
                            break;
                        case "Custom Game":
                            break;
                        case "Save Game":
                            break;
                        case "Settings":
                            //TODO
                            break;
                        case "Reset Game":
                            gameControl.resetGame();
                            break;
                    }
                }
            }
        };
        Graphics.getInstance().getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, up_Down);
    }

    public void endGameScreen(){
        Parent root = Graphics.getInstance().getMenu().getRoot();
        VBox dialogueScreen = null;
        try {
            dialogueScreen = FXMLLoader.load(getClass().getResource("../../Files/Resources/ExitBox.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dialogueScreen != null;
        dialogueScreen.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        dialogueScreen.minHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));

        VBox box = (VBox) dialogueScreen.lookup("#dialogBox");
        box.maxWidthProperty().bind(Bindings.divide(dialogueScreen.widthProperty(), 2.2));
        box.minHeightProperty().bind(Bindings.divide(dialogueScreen.heightProperty(), 2));

        VBox questionHolder = (VBox) dialogueScreen.lookup("#questionHolder");
        questionHolder.minHeightProperty().bind(Bindings.divide(box.minHeightProperty(), 1.7));

        String cssButton = "-fx-font-family: Purisa;" +
                "-fx-font-weight: bold;";
        Button[] buttons = new Button[2];
        buttons[0] = (Button) dialogueScreen.lookup("#Button1");
        buttons[1] = (Button) dialogueScreen.lookup("#Button2");
        for (int i = 0; i < 2; i++) {
            Button button = buttons[i];
            EventHandler<MouseEvent> onButton = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                        button.setStyle(cssButton + "-fx-background-color: #cea57f; -fx-font-size: 25;");
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                        button.setStyle(cssButton + "-fx-background-color:  #69443c; -fx-font-size: 23;");
                    }
                }
            };
            button.addEventHandler(MouseEvent.ANY, onButton);
        }
        EventHandler<MouseEvent> yesButtonClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                try {
                    gameControl.saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        };
        buttons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, yesButtonClick);
        VBox finalDialogueScreen = dialogueScreen;
        buttons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {((GridPane) root).getChildren().remove(finalDialogueScreen);});
        ((GridPane)root).getChildren().add(dialogueScreen);
    }

    public void dynamicMenu() throws IOException {
        dynamicMenuStart();
        hero = new Hero();
        Group root = (Group) Graphics.getInstance().getDynamicMenu().getRoot();
        root.getChildren().add(hero.getView());
        walkOperation(root);
    }

    private void walkOperation(Group root){
        EventHandler<KeyEvent> walkEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.UP ||
                        event.getCode() == KeyCode.LEFT ||
                        event.getCode() == KeyCode.RIGHT) {
                    hero.moveHero();
                    root.getChildren().remove(hero.getView());
                    if (event.getCode() == KeyCode.DOWN) {
                        if (hero.getDirection() == 2) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(2);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.LEFT) {
                        if (hero.getDirection() == 1) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(1);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.UP) {
                        if (hero.getDirection() == 0) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(0);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        if (hero.getDirection() == 3) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(3);
                            hero.setStateOfWalk(0);
                        }
                    }
                    root.getChildren().add(hero.getView());
                }
            }
        };
        Graphics.getInstance().getDynamicMenu().addEventHandler(KeyEvent.ANY, walkEvent);
    }

    private void dynamicMenuStart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/DynamicMenu.fxml"));
        Graphics.getInstance().setDynamicMenu(new Scene(root));
        ((GridPane)root.lookup("#gridPane")).minWidthProperty().bind(Bindings.divide(
                Graphics.getInstance().getStage().widthProperty(), 1));
        ((GridPane)root.lookup("#gridPane")).minHeightProperty().bind(Bindings.divide(
                Graphics.getInstance().getStage().heightProperty(), 1));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getDynamicMenu());
        Graphics.getInstance().getStage().setFullScreen(true);
    }

//    /**returns a scrollPane so you can zoom and pan with ease
//     * @param group takes a parent and adds it to the new zoomPane
//     * @return a zoomPane(actually a scroll pane)
//     */
//    private ScrollPane zoomPane(final Parent group){
//        final double SCALE_DELTA = 1.1;
//        final GridPane zoomPane = new GridPane();
//
//        zoomPane.getChildren().add(group);
//
//        final ScrollPane scroller = new ScrollPane();
//        final Group scrollContent = new Group(zoomPane);
//        scroller.setContent(scrollContent);
//
//        zoomPane.setOnScroll(new EventHandler<ScrollEvent>() {
//            @Override
//            public void handle(ScrollEvent event) {
//                event.consume();
//
//                if (event.getDeltaY() == 0) {
//                    return;
//                }
//
//                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA
//                        : 1 / SCALE_DELTA;
//
//                group.setScaleX(group.getScaleX() * scaleFactor);
//                group.setScaleY(group.getScaleY() * scaleFactor);
//
//            }
//        });
//
//        // Panning via drag....
//        final ObjectProperty<Point2D> lastMouseCoordinates = new SimpleObjectProperty<Point2D>();
//        scrollContent.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                lastMouseCoordinates.set(new Point2D(event.getX(), event.getY()));
//            }
//        });
//
//        scrollContent.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                double deltaX = event.getX() - lastMouseCoordinates.get().getX();
//                double extraWidth = scrollContent.getLayoutBounds().getWidth() - scroller.getViewportBounds().getWidth();
//                double deltaH = deltaX * (scroller.getHmax() - scroller.getHmin()) / extraWidth;
//                double desiredH = scroller.getHvalue() - deltaH;
//                scroller.setHvalue(Math.max(0, Math.min(scroller.getHmax(), desiredH)));
//
//                double deltaY = event.getY() - lastMouseCoordinates.get().getY();
//                double extraHeight = scrollContent.getLayoutBounds().getHeight() - scroller.getViewportBounds().getHeight();
//                double deltaV = deltaY * (scroller.getHmax() - scroller.getHmin()) / extraHeight;
//                double desiredV = scroller.getVvalue() - deltaV;
//                scroller.setVvalue(Math.max(0, Math.min(scroller.getVmax(), desiredV)));
//            }
//        });
//
//        return scroller;
//    }

    public GameControl getGameControl () {
        return gameControl;
    }

    public void setGameControl (GameControl gameControl) {
        this.gameControl = gameControl;
    }
}

class OpaciyChange{
    static double opacityChange = -0.001;
    final static String[] text = {"with High Performance...", "Represented by:" +
            "\nSajad the Gilga\nHossein the Haja\nKasra the Nigga", ""};
    static int turn = 0;
}

class MenuItems{
    public static int number = 0;

    public MenuItems(Text text, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                    text.setFill(Color.rgb(229,223,160));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                    text.setFill(Color.CORNSILK);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    switch (text.getText()){
                        case "Single Player":
                            try {
                                Menu.getInstance().dynamicMenu();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "MultiPlayer":
                            break;
                        case "Custom Game":
                            break;
                        case "Save Game":
                            break;
                        case "Settings":
                            //TODO
                            break;
                        case "Reset Game":
                            gameControl.resetGame();
                            break;
                    }
                }
            }
        };
        text.addEventHandler(MouseEvent.ANY, mouseEvent);
    }

    public MenuItems(ImageView exit, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    exit.setFitHeight(85);
                    exit.setFitWidth(75);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    exit.setFitHeight(80);
                    exit.setFitWidth(70);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    gameControl.endGame();
                }
            }
        };
        exit.addEventHandler(MouseEvent.ANY, mouseEvent);
    }
}

class Hero{
    private double x = Graphics.getInstance().getStage().getWidth() * 15 / 60;
    private double y = Graphics.getInstance().getStage().getHeight() * 30 / 40;
    private double width;
    private double height;
    private int direction = 3;
    private int stateOfWalk = 0;
    private final double speed = 3;
    private ImageView[][] views;
    private Map map = new Map();

    Hero () {
        Image image = null;
        try {
            image = new Image(getClass().getResource("../../Files/Images/heroSprite.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        views = new ImageView[4][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 4; j++){
                WritableImage wImage = new WritableImage(64, 64);
                assert image != null;
                PixelReader reader = image.getPixelReader();
                PixelWriter writer = wImage.getPixelWriter();
                for (int x = 64*i; x < 64*i + 64; x++){
                    for (int y = 64*j; y < 64*j + 64; y++){
                        if (x < 574)
                            writer.setColor(x - 64*i, y - 64*j, reader.getColor(x, y));
                    }
                }
                views[j][i] = new ImageView(wImage);

                //binding width and height property to stage's width and height
                views[j][i].fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 30));
                views[j][i].fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 20));
                width = views[j][i].getFitWidth();
                height = views[j][i].getFitHeight();
            }
        }
    }

    public ImageView getView () {
        views[direction][stateOfWalk].setX(x);
        views[direction][stateOfWalk].setY(y);
        return views[direction][stateOfWalk];
    }

    public ImageView[][] getViews () {
        return views;
    }

    public double getX () {
        return x;
    }

    public void setX (double x) {
        this.x = x;
    }

    public double getY () {
        return y;
    }

    public void setY (double y) {
        this.y = y;
    }

    public double getSpeed () {
        return speed;
    }

    public int getDirection () {
        return direction;
    }

    public void setDirection (int direction) {
        this.direction = direction;
    }

    public int getStateOfWalk () {
        return stateOfWalk;
    }

    public void setStateOfWalk (int stateOfWalk) {
        this.stateOfWalk = stateOfWalk % 9;
    }

    public void moveHero(){
        switch (direction) {
            case 0:
                y -= speed;
                if (map.isCellBlocked(x + (.5)*width, y + (3/4.0)*height)){
                    y += speed;
                }
                break;
            case 1:
                x -= speed;
                if (map.isCellBlocked(x + (.5)*width, y + (3/4.0)*height)){
                    x += speed;
                }
                break;
            case 2:
                y += speed;
                if (map.isCellBlocked(x + (.5)*width, y + (3/4.0)*height)){
                    y -= speed;
                }
                break;
            case 3:
                x += speed;
                if (map.isCellBlocked(x + (.5)*width, y + (3/4.0)*height)){
                    x -= speed;
                }
                break;
        }
    }
}

class Map{
    private String[] cells = new String[40];

    Map(){
        try {
            Scanner scanner = new Scanner(new File("./src/Files/initial/mapBlocks"));
            int index = 0;
            while (scanner.hasNextLine()){
                cells[index] = scanner.nextLine();
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isCellBlocked(double x, double y){
        double cellWidth = Graphics.getInstance().getStage().getWidth() / 60;
        double cellHeight = Graphics.getInstance().getStage().getHeight() / 40;

        return cells[(int)(y / cellHeight)].charAt((int)(x / cellWidth)) == '#';
    }

    public String[] getCells () {
        return cells;
    }

    public void setCells (String[] cells) {
        this.cells = cells;
    }
}