package Control.MultiPlayer;

import Modules.Graphic.Graphics;
import Modules.User.User;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiPlayerBattleControl {
    private User user;
    private GridPane multiPlayerGamePane = new GridPane();
    private Scene multiPlayerScene = new Scene(multiPlayerGamePane);
    private MultiBattleControl multiBattleControl;
    private int port;
    private String ip;
    private Socket s = new Socket();

    public MultiPlayerBattleControl(User user){
        this.user = user;
    }

    public void multiPlayerEntrance(){
        multiPlayerGamePane.setAlignment(Pos.CENTER);

        multiPlayerGamePane.setStyle("-fx-background-image: url(/Files/Images/BackGround/networkBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        Text text = new Text("You entered multiPlayer mode \n  Do you want to play as Host?");
        text.setFont(Font.font(18));
        text.setFill(Color.WHITE);


        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
        noButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");

        HBox buttonHBox = new HBox(50);
        buttonHBox.getChildren().addAll(yesButton,noButton);
        buttonHBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(100);
        vBox.getChildren().addAll(text,buttonHBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(Graphics.SCREEN_WIDTH/2.5,Graphics.SCREEN_HEIGHT/2.5);
        vBox.setStyle("-fx-background-image: url(/Files/Images/BackGround/DialogueBg.png); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        multiPlayerGamePane.getChildren().addAll(vBox);


        yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playAsHost();
            }
        });

        noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playAsClient();
            }
        });

        Graphics.getInstance().getStage().setScene(multiPlayerScene);
    }

    private void playAsHost(){
        multiPlayerGamePane= new GridPane();
        multiPlayerScene.setRoot(multiPlayerGamePane);
        multiPlayerGamePane.setAlignment(Pos.CENTER);

        multiPlayerGamePane.setStyle("-fx-background-image: url(/Files/Images/BackGround/networkBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");



        TextField textField = new TextField("Enter Port");
        Button button = new Button("Next");
        HBox hBox = new HBox(textField,button);

        multiPlayerGamePane.getChildren().add(hBox);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                port = Integer.parseInt(textField.getCharacters().toString());
                multiPlayerGamePane.getChildren().remove(hBox);
                startServer();
            }
        });
        Graphics.getInstance().getStage().setScene(multiPlayerScene);
    }

    private void startServer(){
        try {
            Text text = new Text("wait until another player joins ...");
            text.setFill(Color.BLACK);
            multiPlayerGamePane.getChildren().add(text);
            ServerSocket serverSocket = new ServerSocket(port);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        s = serverSocket.accept();
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        String string = dis.readUTF();
                        if(string.equals("start the game")) {
                            text.setText("a player successfully joined the server \nwait until he starts the game" );
                        }
                        string = dis.readUTF();
                        if(string.equals("game started")){
                            multiBattleControl = new MultiBattleControl(1, s,user);
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Battle.fxml"));
                                Graphics.getInstance().setBattle(new Scene(root));
                                Graphics.getInstance().getStage().setScene(Graphics.getInstance().getBattle());
                                Graphics.getInstance().getStage().setFullScreen(true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Parent root = Graphics.getInstance().getBattle().getRoot();
                            assert root != null;
                            Graphics.MAP_MUSIC_PLAYER.stop();
                            Graphics.BATTLE_MUSIC_PLAYER.setCycleCount(-1);
                            Graphics.BATTLE_MUSIC_PLAYER.play();
                            Graphics.getInstance().setMusicPlayer(Graphics.BATTLE_MUSIC_PLAYER);

                            playerPartControlSize();
                            multiBattleControl.startBattle();
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            }).start();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void playAsClient(){
        multiPlayerGamePane= new GridPane();
        multiPlayerScene.setRoot(multiPlayerGamePane);
        multiPlayerGamePane.setAlignment(Pos.CENTER);

        multiPlayerGamePane.setStyle("-fx-background-image: url(/Files/Images/BackGround/networkBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        TextField ipField = new TextField("Enter ip");
        Button ipButton = new Button("Next");

        HBox ipHBox = new HBox(ipField,ipButton);

        ipButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ip = ipField.getCharacters().toString();

                multiPlayerGamePane.getChildren().remove(ipHBox);

                TextField portField = new TextField("Enter port");
                Button portButton = new Button("Next");

                HBox portHBox = new HBox(portField,portButton);

                multiPlayerGamePane.getChildren().add(portHBox);

                portButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        port = Integer.parseInt(portField.getCharacters().toString());
                        multiPlayerGamePane.getChildren().remove(portHBox);
                        joinServer();
                    }
                });
            }
        });

        multiPlayerGamePane.getChildren().add(ipHBox);
    }

    private void joinServer(){
        try {
            s = new Socket(ip,port);
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            dataOutputStream.writeUTF("start the game");
            dataOutputStream.flush();
            Text text = new Text("Start the game");
            Button button = new Button("Start");
            HBox hBox = new HBox(text,button);
            multiPlayerGamePane.getChildren().addAll(hBox);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    multiBattleControl = new MultiBattleControl(0, s,user);
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Battle.fxml"));
                            Graphics.getInstance().setBattle(new Scene(root));
                            Graphics.getInstance().getStage().setScene(Graphics.getInstance().getBattle());
                            Graphics.getInstance().getStage().setFullScreen(true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Parent root = Graphics.getInstance().getBattle().getRoot();
                        assert root != null;
                        Graphics.MAP_MUSIC_PLAYER.stop();
                        Graphics.BATTLE_MUSIC_PLAYER.setCycleCount(-1);
                        Graphics.BATTLE_MUSIC_PLAYER.play();
                        Graphics.getInstance().setMusicPlayer(Graphics.BATTLE_MUSIC_PLAYER);


                        playerPartControlSize();
                        try {
                            dataOutputStream.writeUTF("game started");
                            dataOutputStream.flush();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    multiBattleControl.startBattle();
                }
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private void playerPartControlSize(){
        double width = Graphics.SCREEN_WIDTH;
        double height = Graphics.SCREEN_HEIGHT;
        Parent root = Graphics.getInstance().getBattle().getRoot();
        VBox playerPart1 = (VBox) root.lookup("#playerPart1");
        VBox playerPart2 = (VBox) root.lookup("#playerPart2");

        //fixing the size of parts according to the page
        playerPart1.setMinHeight(height/2 - 20);
        playerPart1.setMaxHeight(height/2 - 20);
        playerPart2.setMinHeight(height/2 - 50);
        playerPart2.setMaxHeight(height/2 - 50);
        playerPart1.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart1.maxWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart2.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart2.maxWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));

        //fixing size of fields
        HBox field1 = (HBox) root.lookup("#fieldP1");
        HBox field2 = (HBox) root.lookup("#fieldP2");
        HBox detail1 = (HBox) root.lookup("#detailP1");
        HBox detail2 = (HBox) root.lookup("#detailP2");
        field1.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 3/2));
        detail1.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 3));
        field2.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 2));
        detail2.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 2));

        //fixing size of hands
        HBox hand1 = (HBox) root.lookup("#handP1");
        hand1.minWidthProperty().bind(Bindings.divide(playerPart1.minWidthProperty(), 1.2));
        hand1.maxWidthProperty().bind(Bindings.divide(playerPart1.minWidthProperty(), 1.2));
        HBox hand2 = (HBox) root.lookup("#handP2");
        hand2.minWidthProperty().bind(Bindings.divide(playerPart2.minWidthProperty(), 1.2));
        hand2.maxWidthProperty().bind(Bindings.divide(playerPart2.minWidthProperty(), 1.2));

        //fixing images
        VBox pic1 = (VBox) root.lookup("#picContP1");
        VBox frame1 = (VBox) root.lookup("#frameContP1");
        VBox pic2 = (VBox) root.lookup("#picContP2");
        VBox frame2 = (VBox) root.lookup("#frameContP2");
        pic1.setLayoutY(60);
        frame1.setLayoutY(60);
        pic1.setLayoutX(width/2 - 40);
        frame1.setLayoutX(width/2 - 40);
        pic2.setLayoutY(height - 160);
        frame2.setLayoutY(height - 160);
        pic2.setLayoutX(width/2 - 40);
        frame2.setLayoutX(width/2 - 40);
    }



}
