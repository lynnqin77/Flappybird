package com.main;


import com.util.Constant;
import com.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.util.Constant.BIRD_IMG;

public class Bird {

    // Convert the bird into a rectangle object
    private Rectangle rect;

    // Define the acceleration of the bird
    private int acceleration;


    // Define the HP of the bird
    public boolean life = true;



    // Store bird images
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;

    // Define the state of the bird
    private int state;
    public static final int STATE_NORMAL = 0;//Fly level
    public static final int STATE_UP = 1;//Fly up
    public static final int STATE_DOWN = 2;//Fly down


    // Location of the bird
    private int x = 200, y = 200;


    // Define the movement direction of the bird as up and down.
    private boolean up = false, down = false;


    // Define the speed of bird
    private int speed = 4;


    // Constructor method to initialize the image
    public Bird() {
        images = new BufferedImage[BIRD_IMG_COUNT];
        for (int i = 0; i < BIRD_IMG_COUNT; i++) {
            images[i] = GameUtil.loadBuffedImage(BIRD_IMG[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w,h);


    }


    // Draw a bird
    public void draw(Graphics g) {
        flyLogic();

        g.drawImage(images[state], x, y, null);

        // Draw the rectangle of the bird
//        g.drawRect(x,y,(int) rect.getWidth(), rect.height);
        rect.x = this.x;
        rect.y= this.y;
    }


    // Define a method to control the movement direction of the bird
    public void flyLogic() {
        if (up) {
            acceleration--;
            y += acceleration;
            if (acceleration < -4) {
                acceleration = -4;
            }
            if (y < 20) {
                y = 20;
                acceleration = 0;
            }
        }
        if (!up) {
            acceleration++;
            y += acceleration;
            if (acceleration > 3) {
                acceleration = 3;
            }
            if (y > 475) {
                y = 475;
                acceleration = 0;
            }
        }
    }


    // Define a method to control the direction of movement of the bird.
    public void fly(int fly) {
        switch (fly) {
            case 1:
                state = 1;
                up = true;
                break;
            case 5:
                state = 2;
                up=false;
                break;
        }
    }


    public Rectangle getRect() {
        return rect;
    }

    // Redraw the position of the bird when the game restarts
    public void redraw(){
        life= true;
        x=200;
        y=200;

    }


}
