package main;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player extends Object {

    private float velX = 0;
    private float velY = 0;
    private String playerImage = "/images/player.png";

    public Player(float x, float y, float radius) {
        super(x, y, radius);   // call the constructor in the parent class
    }

    public void update() {

        x += velX;
        y += velY;

        // COLLISION WITH OUTSIDE BORDERS
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > 578) {
            x = 578;
        }
        if (y > 395) {
            y = 395;
        }
    }

    // Top left corner of window is (0,0), so going up or left requires negative values
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            velX = 5;
        }
        if(key == KeyEvent.VK_A){
            velX = -5;
        }
        if(key == KeyEvent.VK_S){
            velY = 5;
        }
        if(key == KeyEvent.VK_W){
            velY = -5;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            velX = 0;
        }
        if(key == KeyEvent.VK_A){
            velX = 0;
        }
        if(key == KeyEvent.VK_S){
            velY = 0;
        }
        if(key == KeyEvent.VK_W){
            velY = 0;
        }
    }
    
    public void draw(Graphics2D g2d) {
        
        g2d.drawImage(getPlayerImage(), (int)x, (int)y, null);
    }

    public Image getPlayerImage() {
    
        ImageIcon i = new ImageIcon(getClass().getResource(playerImage));
        return i.getImage();
    }
}
