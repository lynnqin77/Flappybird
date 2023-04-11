package com.main;

import com.util.Constant;
import com.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {

    private Rectangle rect;// Convert the barriers into a rectangle object

    private int speed = 3;// Define the speed of barrier

    private boolean visible;// Define the statue of barriers

    private static BufferedImage[] imgs; // Define the images of barriers

    static {
        final int COUNT = 3;
        // Initializing the image when the class is loaded.
        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBuffedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    // Define the position width and height of barriers
    private int x, y;
    private int width, height;

    // Define the type of the barriers
    private int type;
    public static final int TYPE_TOP_NORMAL = 0;
    public static final int TYPE_BOTTOM_NORMAL = 2;
    public static final int TYPE_HOVER_NORMAL = 4;

    public static final int BARRIER_WIDTH = imgs[0].getWidth();
    public static final int BARRIER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();


    public Barrier() {
        rect= new Rectangle();
    }

    public Barrier(int x, int y, int height, int type) {
        this.x = x;
        this.y = y;
        this.width = BARRIER_WIDTH;
        this.height = height;
        this.type = type;
    }

    // Define a method to draw different type of barrier
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;

            case TYPE_BOTTOM_NORMAL:
                drawNormalBottom(g);
                break;

        }

    }

    // Draw barriers going down from the top
    public void drawTopNormal(Graphics g) {
        //Calculate the number of barriers needed
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIER_HEIGHT, null);
        }

        int y = height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x-=speed;
        if (x< -50){
            visible =false;
        }

        rect(g);
    }

    // Draw barriers going up from the bottom
    private void drawNormalBottom(Graphics g) {
        //Calculate the number of barriers needed
        int count = height / BARRIER_HEIGHT + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAM_HEIGTH - i * BARRIER_HEIGHT, null);
        }

        int y = Constant.FRAM_HEIGTH - height;
        g.drawImage(imgs[1], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x-=speed;
        if (x< -50){
            visible =false;
        }


        rect(g);
    }



    //Draw the rectangle of the barrier
    public void rect(Graphics g){
        int x1 = this.x;
        int y1 = this.y;
        int w1 = imgs[0].getWidth();
//        g.setColor(Color.blue);
//        g.drawRect(x1,y1,w1,height);
        setRectangle(x1,y1,w1,height);
    }

    public void setRectangle(int x,int y, int width,int height){
        rect.x= x;
        rect.y= y;
        rect.width = width;
        rect.height =height;
    }



    // Define a method to check when to draw next barrier
    public boolean isInFrame(){
        return 600-x >150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }
}
