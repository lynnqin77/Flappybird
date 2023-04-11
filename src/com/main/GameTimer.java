package com.main;

public class GameTimer {

    // start
    private long beginTime;

    private long endTime;
    private long difference;

    public GameTimer(){}

    public void begin(){
        beginTime= System.currentTimeMillis();

    }

    public long difference(){
        endTime = System.currentTimeMillis();
        return difference=(endTime-beginTime)/1000;
    }
}
