package Control;

import Modules.Graphic.Graphics;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiPlayerBattleControl {
    private GridPane multiPlayerGamePane = new GridPane();
    private Scene multiPlayerScene = new Scene(multiPlayerGamePane);
    private int port;
    private String ip;
    private Socket s = new Socket();

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
                        s.close();
                        serverSocket.close();
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
            dataOutputStream.close();
            Text text = new Text("Start the game");
            Button button = new Button("Start");
            HBox hBox = new HBox(text,button);
            multiPlayerGamePane.getChildren().addAll(hBox);
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
