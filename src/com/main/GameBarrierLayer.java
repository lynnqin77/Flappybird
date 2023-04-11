package com.main;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Define an array to store barriers.
public class GameBarrierLayer {

    private GameTimer gameTimer;

    private int txt;

    private Random random =new Random();
    private List<Barrier> barriers;

    public GameBarrierLayer(){
        barriers = new ArrayList<>();
        gameTimer = new GameTimer();

    }

    // Define the method to draw the barriers
    public void draw(Graphics g, Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);

            if (barrier.isVisible()) {
                barrier.draw(g);
            }else {
                Barrier remove = barriers.remove(i);
                Barrierpool.setPool(remove);
                i--;
            }
        }
        collideBird(bird);
        logic(g);

    }


    public void logic(Graphics g) {
        if (barriers.size() == 0) {
            ran();
            gameTimer.begin();
            insert(600, 0, numberTop, 0);
            insert(600, 500 - numberDown, numberDown, 2);
        } else {
            long difference = gameTimer.difference();
            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman", 1, 20));
            g.drawString("You persisted " + difference + " seconds so far", 30, 50);
            g.setFont(new Font("Times New Roman", 1, 20));
            g.drawString("Pressing the up arrow key controls the bird", 30, 70);


            txt = getTxt();
            if (difference <= txt){
                g.drawString("Best Score: "+txt,450,50);
            }else {
                setTxt(String.valueOf(difference));
                g.drawString("Best Score: "+getTxt(),450,50);

            }


            //Check if the last barrier has entered the screen.
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                ran();
                insert(600, 0, numberTop, 0);
                insert(600, 500 - numberDown, numberDown, 2);
            }
        }
    }


    // Used to get an object from the object pool, encapsulate the parameters as a barrier, and store it in the barriers array.
    public void insert(int x,int y ,int num,int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }

    //The height of the barriers above.
    private int numberTop;

    //The height of the barriers below.
    private int numberDown;
    public void ran(){
        numberTop = random.nextInt(420)+100;
        numberDown = random.nextInt(420)+100;

        if (numberTop+numberDown> 420){
            ran();
        }
    }


    // Determine whether the bird collides with the barrier.
    public  boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (barrier.getRect().intersects(bird.getRect())){
                System.out.println("Collided");
                bird.life= false;
            }
        }
        return false;
    }

    // Define a method to clear the barrier pool
    public void clearpool(){
        barriers.clear();

    }


    // Read game record file data
    File file = new File("GameRecord.txt");
    public int getTxt(){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int read = 0;
        try {
            read = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return read;
    }

    // Define a method to store game records to a file
    public void setTxt(String str){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fw.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
