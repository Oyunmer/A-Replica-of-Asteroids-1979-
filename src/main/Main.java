package main;

import game.DasMeteorit;
import game.Display;

public class Main {

    public static void main(String[] args) {
    	DasMeteorit asteroidsGame = new DasMeteorit();
        Display display = new Display(asteroidsGame);
        display.start();
    }
    
}
