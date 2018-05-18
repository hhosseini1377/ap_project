import Control.GameControll.GameControl;

import java.io.IOException;

class Main {
    public static void main(String[] args) {
        GameControl gameControl = new GameControl("/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/save/");
        try {
            gameControl.startGame();
        }catch (IOException e){
            System.out.println("problem in input of files");
        }
        gameControl.game();
    }
}