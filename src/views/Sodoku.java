package views;

import controllers.SodokuController;
import models.SodokuModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class Sodoku extends JPanel {
    private SodokuModel sodokuModel;
    private Game game;
    private JButton btnCurrent;

    public Game getGame() {
        return game;
    }

    public void setBtnCurrent(JButton numCurrent) {
        this.btnCurrent = numCurrent;
    }

    public SodokuModel getSodokuModel() {
        return sodokuModel;
    }


    public Sodoku(String level, Game game){
        setPreferredSize(new Dimension(500,500));
        this.game=game;
        ActionListener action=new SodokuController(this);
        KeyListener key=new SodokuController(this);
        this.sodokuModel=new SodokuModel(level);
        for(int i=1;i<=this.sodokuModel.getRow();i++){
            for(int j=1;j<=this.sodokuModel.getCol();j++){
                String valueStr=this.sodokuModel.getData()[i][j]+"";
                if(this.sodokuModel.getData()[i][j]==0){
                    valueStr="";
                }
                JButton num=new JButton(valueStr);
                num.setFont(new Font("TimesRoman", Font.BOLD, 20));
                this.add(num);
                num.setActionCommand(i+""+j+""+this.sodokuModel.getData()[i][j]);
                this.btnCurrent=null;
                num.addActionListener(action);
                num.addKeyListener(key);
            }

        }

        this.setLayout(new GridLayout(9,9));



    }

    public void update(int value){
        sodokuModel.setData(value);
        btnCurrent.setText(""+value);
        btnCurrent.setActionCommand(sodokuModel.getRowCurrent()+""+sodokuModel.getColCurrent()+""+value);
        btnCurrent.setForeground(Color.red);
    }

    public boolean checkData(int value){
        sodokuModel.getArrayColCurrent();
        sodokuModel.getArrayRowCurrent();
        sodokuModel.getThreeXThreeCurrent();
        if(sodokuModel.checkNum(value,sodokuModel.arrayColCurrent)&&sodokuModel.checkNum(value,sodokuModel.arrayRowCurrent)&&sodokuModel.checkNum(value,sodokuModel.threeXThreeCurrent)){
            return true;
        }
        return false;
    }

    public void getPosition(int row,int col){
        sodokuModel.setColCurrent(col);
        sodokuModel.setRowCurrent(row);
    }




}
