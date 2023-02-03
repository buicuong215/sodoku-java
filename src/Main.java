import views.Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Game();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}