package models;

public class SodokuModel {
    private int[][] data;
    private int row=9;
    private int col=9;

    private int rowCurrent=0;
    private int colCurrent=0;

    public int start;
    public int end;

    public int[] arrayRowCurrent;
    public int[] arrayColCurrent;
    public int[] threeXThreeCurrent;

    private int easy=5;
    private int medium=6;
    private int difficulty=7;

    private int level;

    public int getLevel() {
        return level;
    }

    public int getRowCurrent() {
        return rowCurrent;
    }

    public int getColCurrent() {
        return colCurrent;
    }


    public SodokuModel(String level){
        int max=80;
        int min=0;
        if(level=="Easy"){
            this.level=easy;
        }else if(level=="Medium"){
            this.level=medium;
        }else if(level=="Difficult") {
            this.level=difficulty;
        }
        String[] dataSodoku={
                "254367891893215674716984253532698147178432569649571328421753986365849712987126435",
                "546329187978154236123678459764813925352967841819542673485796312631285794297431568",
                "958274163123698754746153928674315289532789416819462375285941637397526841461837592",
                "865379412924581376713642895397164528482795631156823947541236789679418253238957164",
                "268495317194673852735128964872549631651387249943216785326951478589764123417832596",
        };
        double randomData=Math.random()*(4+1);
        String result=dataSodoku[(int)randomData];
        String[] a=result.split("");
        for (int i=0;i<Math.pow(this.getLevel(),2);i++){
            double random=Math.random()*(max-min+1)+min;
            a[(int)random]="0";
        }
        int count=0;
        data=new int[10][10];
        for(int i=1;i<=row;i++){
            for(int j=1;j<=col;j++){
                data[i][j]=Integer.parseInt(a[count]);
                count++;
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int[][] getData() {
        return data;
    }

    public void setColCurrent(int colCurrent) {
        this.colCurrent = colCurrent;
    }

    public void setRowCurrent(int rowCurrent) {
        this.rowCurrent = rowCurrent;
    }

    public Boolean checkNum(int x,int[] a){
        for(int i=1;i<=9;i++){
            if(a[i]==x){
                return false;
            }
        }
        return true;
    }

    public void getArrayRowCurrent(){
        int j=1;
        this.arrayRowCurrent=new int[10];
        for(int i=1;i<=9;i++){
            this.arrayRowCurrent[i]=this.getData()[rowCurrent][j];
            j++;
        }

    }

    public void getArrayColCurrent(){
        int i=1;
        this.arrayColCurrent=new int[10];
        for(int j=1;j<=9;j++){

            this.arrayColCurrent[j]=this.getData()[i][this.colCurrent];
            i++;
        }

    }

    public void startAndEnd(int x){
        if(x<=3){
            this.start=1;
            this.end=3;
        } else if (x<=6&&x>3) {
            this.start=4;
            this.end=6;
        }else {
            this.start=7;
            this.end=9;
        }
    }
    public void setData(int value){
        this.getData()[this.rowCurrent][this.colCurrent]=value;
    }
    public int getNum(){
        return this.getData()[this.rowCurrent][this.colCurrent];
    }
    public void getThreeXThreeCurrent(){
        this.startAndEnd(this.rowCurrent);
        int rowStart=this.start;
        int rowEnd=this.end;
        this.startAndEnd(this.colCurrent);
        int colStart=this.start;
        int colEnd=this.end;
        int c=1;
        this.threeXThreeCurrent=new int[10];
        for(int i=rowStart;i<=rowEnd;i++){
            for(int j=colStart;j<=colEnd;j++){

                this.threeXThreeCurrent[c]=this.getData()[i][j];
                c++;
            }
        }


    }

    public Boolean checkWin(){
        for(int i=1;i<=9;i++){
            for(int j=1;j<=9;j++){
                if(this.getData()[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
}
