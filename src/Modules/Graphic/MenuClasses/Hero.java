package Modules.Graphic.MenuClasses;

import Control.GameControll.GameControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import javafx.beans.binding.Bindings;
import javafx.scene.image.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public
class Hero{
    private double x = Graphics.getInstance().getStage().getWidth() * 15 / 60;
    private double y = Graphics.getInstance().getStage().getHeight() * 30 / 40;
    private double width;
    private double height;
    private int direction = 3;
    private int stateOfWalk = 0;
    private final double speed = 5;
    private ImageView[][] views;
    private Map map;

    public Hero (GameControl gameControl) {
        map = new Map(gameControl);
        Image image = null;
        try {
            image = new Image(getClass().getResource("../../../Files/Images/heroSprite.png").toURI().toString());
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

    public Hero(){
        x += 120;
        y -= 40;
        map = new Map();
        Image image = null;
        try {
            image = new Image(getClass().getResource("../../../Files/Images/heroSprite.png").toURI().toString());
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
    private boolean isCastle = false;

    Map(GameControl gameControl){
        try {
            Scanner scanner = new Scanner(new File("./src/Files/initial/mapBlocks" + gameControl.getUser().getLevel() + ".txt"));
            int index = 0;
            while (scanner.hasNextLine()){
                cells[index] = scanner.nextLine();
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Map(){
        isCastle = true;
        try {
            Scanner scanner = new Scanner(new File("./src/Files/initial/castleMap1.txt"));
            int index = 0;
            while (scanner.hasNextLine()){
                cells[index] = scanner.nextLine();
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkCell(double x, double y){
        double cellWidth = Graphics.getInstance().getStage().getWidth() / 60;
        double cellHeight = Graphics.getInstance().getStage().getHeight() / 40;
        int col = (int)(x / cellWidth);
        int row = (int)(y / cellHeight);
        switch (cells[row].charAt(col)){
            case 'G':
                if (isCastle)
                    Menu.getInstance().startBattle(1);
                else {
                    try {
                        Menu.getInstance().castleMap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 'O':
                Menu.getInstance().startBattle(2);
                break;
            case 'V':
                Menu.getInstance().startBattle(3);
                break;
            case 'D':
                Menu.getInstance().startBattle(4);
                break;
            case 'I':
                break;
            case 'S':
                Menu.getInstance().shopEntrance();
                break;
        }
    }

    public boolean isCellBlocked(double x, double y){
        checkCell(x, y);
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
