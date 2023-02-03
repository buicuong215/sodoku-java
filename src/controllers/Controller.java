package controllers;

import views.Views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Views views;
    public Controller(Views views){
        this.views=views;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        int select=views.getSelect();
        if(code==40||code==38||code==37||code==39)  new SoundController(views.getSfx(),views.getMusic()).start(1);
        switch ( code ) {
            case  40:
                views.setSelect(select+1);
                break;
            case  38:
                views.setSelect(select-1);
                break;
            case 10:
                new SoundController(views.getSfx(),views.getMusic()).start(2);
                views.handlerViews();
                break;
            case 37:
                views.downVolume();
                break;
            case 39:
                views.upVolume();
                break;
            default:
                break;

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
