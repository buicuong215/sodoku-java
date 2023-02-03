package views;

import controllers.Controller;
import controllers.SoundController;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Views extends JPanel {
    private Game game;
    private float sfx,music;
    private String levelCurrent="Easy";
    private String[] text;

    private int musicVolume[]={10,10};
    private String viewCurrent="menu";
    public  String[] menuOptions={"Chơi mới","Chọn mức độ","Cài đặt","Thoát"};
    public  String[] levelOptions={"Easy","Medium","Difficult","Menu"};
    public  String[] configOptions={"SFX ","Âm nhạc ","Menu"};
    private  String[] overOptions={"Tiếp tục","Menu"};
    private Font font=new Font(Font.SERIF, Font.BOLD, 30);
    private Font font1=new Font(Font.SERIF, Font.BOLD, 50);
    private int margin=50;
    private int select=0;
    private SoundController soundController;
    private boolean win=false;
    private Image[] images;
    private String[] urlImage={
            "C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\images\\sodoku.jpg",
            "C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\images\\config.jpg",
            "C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\images\\level.jpg"};
    private Controller gameController;
    private Sodoku sodoku;
    private Clip musicBg;
    public Views(Game game){
        setFocusable(true);
        setPreferredSize(new Dimension(500,500));
        importImages();
        gameController=new Controller(this);
        addKeyListener(gameController);
        getVolume();
        this.soundController=new SoundController(sfx,music);
        this.game=game;
       musicBg=this.soundController.getClip()[0];
       musicBg.start();
    }

    public int[] getMusicVolume() {
        return musicVolume;
    }

    public int getSelect() {
        return select;
    }

    public void handlerViews(){
        String view=viewCurrent;
        if(viewCurrent=="menu"){
            if(select==0)viewCurrent="sodoku";
            else if(select==1) viewCurrent="level";
            else if(select==2) viewCurrent="config";
            else game.exit();
            if(viewCurrent!=view) select=0;
            repaint();

        }

        if(viewCurrent=="level"){
            if(select==0) levelCurrent="Easy";
            else if(select==1) levelCurrent="Medium";
            else if(select==2) levelCurrent="Difficult";
            else viewCurrent="menu";
            if(viewCurrent!=view)this.select=0;
            repaint();

        }

        if(viewCurrent=="config"){
            if(select==2) viewCurrent="menu";

            if(viewCurrent!=view){
                select=0;
            }
            repaint();

        }

        if(viewCurrent=="over"){
            if(select==0){
                this.startGame();
            }else {
                viewCurrent="menu";
            }
            if(viewCurrent!=view){
                this.select=0;
            }
            repaint();
        }


    }




    public int handlerSelect(int select){
        int options = 0;
        if(viewCurrent=="menu") options=menuOptions.length;
        if(viewCurrent=="config") options=configOptions.length;
        if(viewCurrent=="level") options=levelOptions.length;
        if(viewCurrent=="over") options=overOptions.length;
        if(select==options) return  0;
        if(select==-1)  return options-1;
        return select;

    }

    public void setSelect(int select) {
        int result=this.handlerSelect(select);
        this.select = result;
        repaint();
    }

    public void importImages(){
        this.images=new Image[urlImage.length];
        for(int i=0;i<urlImage.length;i++){
            images[i]=new ImageIcon(urlImage[i]).getImage();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawView(g);
    }

    public void setWin(boolean win) {
        if(win){
            new SoundController(sfx,music).start(4);
        }else {
            new SoundController(sfx,music).start(6);
        }
        this.win = win;
        viewCurrent="over";
        this.sodoku.setVisible(false);
        this.setVisible(true);
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
        repaint();
    }

    public float getSfx() {
        return sfx;
    }

    public float getMusic() {
        return music;
    }

    public void startGame(){
        new SoundController(sfx,music).start(3);
        this.sodoku=new Sodoku(levelCurrent,game);
        game.add(sodoku);
       this.setVisible(false);
   }

    public void drawView(Graphics graphics){
        String[] content = new String[0];
        Image imgBg = null;
        if(viewCurrent=="sodoku") {
            startGame();
            return;
        };
        if(viewCurrent=="menu"){
            content=menuOptions;
            imgBg=images[0];
            
        } 
        if(viewCurrent=="level"){
            content=levelOptions;
            imgBg=images[2];
        } 
        if(viewCurrent=="config"){
            content=configOptions;
            imgBg=images[1];
        } 
        if(viewCurrent=="over"){
            content=overOptions;
            String message;
            if(this.win){
                message="YOU WIN";
            }else {
                message="YOU LOSE";
            }

            graphics.setFont(font1);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0,0,getWidth(),getHeight());
            graphics.setColor(Color.WHITE);
            graphics.drawString(message,textCenterX(message,graphics),textCenterY(message,graphics)-100);
        }
        graphics.drawImage(imgBg,0,0,getWidth(),getHeight(),null);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        int top=50;
        int pointerGap=50;
        text=new String[content.length];
        for(int i=0;i<content.length;i++){
             text[i]=content[i];
            if(viewCurrent=="config"&&i!=content.length-1){
                text[i]=content[i]+" < "+musicVolume[i]+" >";
            }
            effectColor(graphics,i);
            graphics.drawString(text[i],textCenterX(text[i],graphics),top+textCenterY(text[i],graphics)+margin*i);
        }
        graphics.setColor(Color.RED);
        graphics.drawString("> ",textCenterX(text[select],graphics)-pointerGap,top+textCenterY(">",graphics)+margin*select);
    };


    public void effectColor(Graphics g,int i){
        if(select==i)
            g.setColor(Color.RED);
        else
            g.setColor(Color.WHITE);
    }

    public int textCenterX(String text,Graphics g){
        return (this.getWidth()-(int)g.getFontMetrics().getStringBounds(text,g).getWidth())/2;
    }
    public int textCenterY(String text,Graphics g){
        return (this.getHeight()-(int)g.getFontMetrics().getStringBounds(text,g).getHeight())/2;
    }


    public void upVolume() {
        if(this.musicVolume[select]==10||viewCurrent!="config") return;
        this.musicVolume[select]++;
        getVolume();
        if(select==1){
            resetMusic();
        }

        repaint();
    }
    public void downVolume() {
        if(this.musicVolume[select]==0||viewCurrent!="config") return;
        this.musicVolume[select]--;
        getVolume();
        if(select==1){
            resetMusic();
        }
        repaint();
    }

    public void resetMusic(){
       musicBg.stop();
        this.soundController=new SoundController(sfx,music);
        musicBg=this.soundController.getClip()[0];
        this.soundController.start(0);
    }

    public void getVolume(){
        this.sfx=(float) musicVolume[0]/10;
        this.music=(float)musicVolume[1]/10;
    }


}
