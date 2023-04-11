package com.main;


import static com.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame {

    private  GameBackground gameBackground;//Instantiate the gamebackground class

    private Bird bird;//Instantiate the Bird class

    private GameBarrierLayer gameBarrierLayer;//Instantiate the GameBarrierLayer class

    private final BufferedImage buffimg = new BufferedImage(FRAM_WIDTH,FRAM_HEIGTH,BufferedImage.TYPE_4BYTE_ABGR);

    public GameFrame() {

        setVisible(true);//Set the window to be visible


        setSize(FRAM_WIDTH,FRAM_HEIGTH);// The size of the window


        setTitle(FRAM_TITLE);// The name of the window


        setLocation(FRAM_X,FRAM_Y);// Initialization position of the window

        setResizable(false);


        // Set the close event for the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Initialize game object
        initGamg();
        new run().start();


        // Add key press listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });


    }


    //Initializing objects in the game
    public void initGamg(){
        gameBackground = new GameBackground();
        bird = new Bird();
        gameBarrierLayer = new GameBarrierLayer();
    }


    //Define a thread
    class run extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    // All the content we need to draw is called to draw in this method.
    @Override
    public void update(Graphics g) {
        if (bird.life){
            Graphics graphics = buffimg.getGraphics();
            gameBackground.draw(graphics);
            bird.draw(graphics);
            gameBarrierLayer.draw(graphics,bird);

            g.drawImage(buffimg,0,0,null);
        }else {
            String over = "GAME OVER";
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman", Font.BOLD,60));
            g.drawString(over,100,250);


           String reset = "Press The Space To Restart";
           g.setFont(new Font("Times New Roman", Font.BOLD,20));
           g.drawString(reset,180,350);
        }

    }


    //Define a method that controls the status of the bird when the arrow key is pressed.
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if (!bird.life){
                    restart();
                }
        }
    }

    // Defines a method to control the state of the bird after the down arrow key is released
    public void minu(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(5);
                break;
        }
    }

    // Define restart game method
    public void restart(){
        gameBarrierLayer.clearpool();
        bird.redraw();
    }

}

