package com.util;

import java.awt.*;
import java.util.Collections;

public class Constant {

    // The size of the window
    public static final int FRAM_WIDTH = 600;
    public static final int FRAM_HEIGTH = 500;

    // The name of the window
    public static final String FRAM_TITLE = "Flappybird";

    // Initialization position of the window
    public static final int FRAM_X = 200;
    public static final int FRAM_Y = 200;


    // Path of the background image
    public static final String BK_IMG_OATH = "img/bird_bk.png";


    // Background color of the game
    public static final Color BK_COLOR=new Color(0x4B4CF);


    // Load the image of the bird
    public static final String[] BIRD_IMG =
            {"img/bird_normal.png", "img/bird_up.png", "img/bird_down.png"
    };

    // Load the image of the barriers
    public static final String[] BARRIER_IMG_PATH =
            {"img/barrier.png", "img/barrier_up.png", "img/barrier_down.png"
    };

}
