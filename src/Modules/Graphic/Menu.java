package Modules.Graphic;

public class Menu {
    private static Menu menu = new Menu();

    private Menu(){
    }

    public static Menu getInstace(){
        return menu;
    }
}