package Miscs;

import java.util.concurrent.TimeUnit;

public class Execution_Time_Calculator {

    public static void main(String[] args) throws InterruptedException {

        long start = System.nanoTime();

        /*
        Code Body
         */

        long end = System.nanoTime();

        long output = end - start;

        System.out.println("<<Execution Time is: " + (output / 1000000000.0) + " Seconds>>");
    }
}

