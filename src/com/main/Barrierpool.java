package com.main;

import java.util.ArrayList;
import java.util.List;

/**
 To avoid repeated creation and destruction of objects,
 use an object pool to pre-create some objects
 */
public class Barrierpool {
    // Container for managing all objects in the pool.
    private static List<Barrier> pool = new ArrayList<>();
    // The initial number of objects in the pool.
    public static final int initCount = 16;
    // The maximum number of objects in the object pool
    public static final int maxCount = 20;

    static {
        // Initialize objects in the object pool
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }


    // Get an object from the object pool
    public static Barrier getPool(){
        int size = pool.size();
        // get an object if there is one in the object pool
        if (size > 0) {

            System.out.println("Take one");
            return pool.remove(size-1);
        }else {

            System.out.println("New one");
            return new Barrier();
        }
    }


    // Return the object to the container
    public static void setPool(Barrier barrier){
        if (pool.size() < maxCount) {
            pool.add(barrier);
            System.out.println("return");
        }
    }


}