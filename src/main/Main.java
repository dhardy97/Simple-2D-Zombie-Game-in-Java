package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();    // JFrame class makes the window our game will be in 
        frame.pack();
        frame.setSize(640, 480);    // 640 x 480 pixels
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // close program on window close
        frame.add(new Game());  // creates a Game instance in that window
        frame.setVisible(true);
    } 
}