package main.com.javaexercices.qrcode;

import java.io.IOException;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class QRCode {
    public static int getSizePerId(int sizeID){
        return 21 + 4*(sizeID -1);
    }

    private final int size;
    private final boolean[][] map;

    public QRCode(int size){
        if(size>40) size=40;
        else if(size <=0) size=1;

        this.size = QRCode.getSizePerId(size);
        this.map = new boolean[this.size][this.size];

        int i=0;
        int j;
        for(boolean[]line:this.map){
            j = 0;
            while(j < this.size){
                this.map[i][j] = (int)(Math.random()*2+1) == 1;
                j++;
            }
            i++;
        }

        drawPositionPatternTopLeft();
        drawPositionPatternTopRight();
        drawPositionPatternBottomLeft();
        drawPositionPatternBottomRight();

    }

    private void drawPositionPatternTopLeft(){
//        TOP LINE
        int i=0;
        while(i<5){
            this.map[0][i] = true;
            this.map[1][i] = false;
            i++;
        }

//        RIGHT LINE
        i=0;
        while(i<=5){
            this.map[i+1][4] = false;
            this.map[i][5] = true;
            this.map[i][6] = false;
            i++;
        }
        this.map[6][6] = false;
        this.map[6][5] = false;

//        BOTTOM LINE
        i=0;
        while(i<5){
            this.map[5][i] = true;
            this.map[6][i] = false;
            i++;
        }
        this.map[4][2] = false;
        this.map[4][3] = false;

//        LEFT LINE
        i=4;
        while(i>0){
            this.map[i][0] = true;
            this.map[i][1] = false;
            i--;
        }

//        CENTER
        this.map[2][2] = true;
        this.map[2][3] = true;
        this.map[3][2] = true;
        this.map[3][3] = true;
    }
    private void drawPositionPatternTopRight(){
        //        TOP LINE
        int i=this.size-1;
        while(i > this.size - 7){
            this.map[0][i] = true;
            this.map[1][i] = false;
            i--;
        }

//        RIGHT LINE
        i=1;
        while(i<=5){
            this.map[i][this.size-1] = true;
            this.map[i][this.size-2] = false;
            i++;
        }
        this.map[6][this.size-1] = false;

//        BOTTOM LINE
        i=this.size-2;
        while(i>this.size-7){
            this.map[4][i] = false;
            this.map[5][i] = true;
            this.map[6][i] = false;
            i--;
        }
        this.map[6][i] = false;
        this.map[6][i-1] = false;

//        LEFT LINE
        i=4;
        while(i>0){
            this.map[i][this.size-5] = false;
            this.map[i][this.size-6] = true;
            this.map[i][this.size-7] = false;
            i--;
        }
        this.map[5][this.size-7] = false;
        this.map[0][this.size-7] = false;

//        CENTER
        this.map[2][this.size-4] = true;
        this.map[2][this.size-3] = true;
        this.map[3][this.size-4] = true;
        this.map[3][this.size-3] = true;
    }
    private void drawPositionPatternBottomLeft(){
        //        TOP LINE
        int i=0;
        while(i<6){
            this.map[this.size-7][i] = false;
            this.map[this.size-6][i] = true;
            this.map[this.size-5][i] = false;
            i++;
        }
        this.map[this.size-7][6] = false;

//        RIGHT LINE
        i=1;
        while(i<6){
            this.map[this.size-i][4] = false;
            this.map[this.size-i][5] = true;
            this.map[this.size-i][6] = false;
            i++;
        }
        this.map[this.size-i][6] = false;

//        BOTTOM LINE
        i=0;
        while(i<5){
            this.map[this.size-1][i] = true;
            this.map[this.size-2][i] = false;
            i++;
        }

//        LEFT LINE
        i=2;
        while(i<=5){
            this.map[this.size-i][0] = true;
            this.map[this.size-i][1] = false;
            i++;
        }

//        CENTER
        this.map[this.size-4][2] = true;
        this.map[this.size-4][3] = true;
        this.map[this.size-3][2] = true;
        this.map[this.size-3][3] = true;
    }
    private void drawPositionPatternBottomRight(){
        //        TOP LINE
        int i=8;
        while(i>3){
            this.map[this.size-8][this.size-i] = true;
            this.map[this.size-7][this.size-i] = false;
            i--;
        }

//        RIGHT LINE
        i=7;
        while(i>3){
            this.map[this.size-i][this.size-4] = true;
            this.map[this.size-i][this.size-5] = false;
            i--;
        }

//        BOTTOM LINE
        i=5;
        while(i<=8){
            this.map[this.size-4][this.size-i] = true;
            this.map[this.size-5][this.size-i] = false;
            i++;
        }

//        LEFT LINE
        i=5;
        while(i<8){
            this.map[this.size-i][this.size-8] = true;
            this.map[this.size-i][this.size-7] = false;
            i++;
        }

//        CENTER
        this.map[this.size-6][this.size-6]= true;
    }

    public void printThis(){
        for(boolean[]i:this.map){
            for(boolean j:i){
                System.out.print(j ? "[#]" : "   ");
            }
            System.out.println();
        }
    }

    public void createFile(){
        try {
            File file = new File("./qrcode.txt");
            FileWriter writer = new FileWriter(file);
            int i = 0;
            int j;
            while(i < this.map.length){
                j=0;
                while(j<this.map[i].length){
                    writer.write(this.map[i][j] ? '1' : '0');
                    j++;
                }
                writer.write(",\n");
                i++;
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}