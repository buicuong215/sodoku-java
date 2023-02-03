package controllers;

import controllers.SoundController;
import views.Sodoku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SodokuController implements ActionListener, KeyListener {
    private Sodoku sodoku;
    private int checkFalse=0;
    public SodokuController(Sodoku sodoku){
        this.sodoku=sodoku;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int data=Integer.parseInt(e.getActionCommand());
        int value=data%10;
        if(value!=0){
            return;
        }

        new SoundController(sodoku.getGame().getViews().getSfx(),sodoku.getGame().getViews().getMusic()).start(7);
        int row=data/100;
        int col=data/10%10;
        sodoku.setBtnCurrent((JButton) e.getSource());
        sodoku.getPosition(row,col);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(sodoku.getSodokuModel().getRowCurrent()==0||sodoku.getSodokuModel().getNum()!=0){
            return;
        }
        int value= e.getKeyChar() -48;
        if(value<=0||value>9){
            return;
        }
        if(sodoku.checkData(value)){
           sodoku.update(value);
            new SoundController(sodoku.getGame().getViews().getSfx(),sodoku.getGame().getViews().getMusic()).start(5);
        }else{
            checkFalse=checkFalse+1;
            new SoundController(sodoku.getGame().getViews().getSfx(),sodoku.getGame().getViews().getMusic()).start(8);
        }
        if(sodoku.getSodokuModel().checkWin()){
            sodoku.getGame().getViews().setWin(true);

        }
        if(checkFalse==3){
           sodoku.getGame().getViews().setWin(false);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
