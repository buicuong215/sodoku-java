package views;

import views.Sodoku;
import views.Views;

import javax.swing.*;


public class Game extends JFrame {
    private Views views;
    private Sodoku sodoku;

    public Game(){
        setTitle("SODOKU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        views=new Views(this);
        this.add(views);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public Views getViews() {
        return views;
    }

    public void exit(){
        System.exit(0);
        this.dispose();
        this.setVisible(false);
    }
}
