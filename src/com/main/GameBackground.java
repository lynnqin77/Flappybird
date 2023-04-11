package com.main;

import com.util.Constant;
import com.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static com.util.Constant.*;
import static java.awt.Color.black;

public class GameBackground {

    private BufferedImage bkimg;

    // Constructor to initialize resources.
    public GameBackground(){
       bkimg = GameUtil.loadBuffedImage(Constant.BK_IMG_OATH);
    }


    // Draw picture
    public void draw(Graphics g){

        // Fill background color
        g.setColor(BK_COLOR);
        g.fillRect(0,0,FRAM_WIDTH,FRAM_HEIGTH);
        g.setColor(Color.black);



        int height = bkimg.getHeight();
        int weight = bkimg.getWidth();

        int count = FRAM_WIDTH/weight+1;
        for (int i = 0; i < count; i++) {
            g.drawImage(bkimg,weight*i,Constant.FRAM_HEIGTH-height,null);
        }

    }
}
