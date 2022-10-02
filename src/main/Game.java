package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.awt.Image;


public class Game extends JPanel implements ActionListener{
    
    // VARIABLES
    private String background = "/images/background.png";
    private String endGame = "/images/endGame.png";

    // TIMER
    Timer gameLoopTimer; // Timer class is used for anything that fires an ActionEvent in intervals, like a game loop

    // OBJECTS
    Player p;
    Controller c;

    // CONSTRUCTOR - First method to initialize the game
    public Game() {
        setFocusable(true);
        gameLoopTimer = new Timer(10, this); // every 10 milliseconds the Timer runs
        gameLoopTimer.start();  // starts the game loop

        p = new Player(300, 200, 23); // initialize objects
        c = new Controller(p, this);

        this.addKeyListener(new KeyInput(p)); // key input for Player object
        this.addMouseListener(new MouseInput(c));
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;    // Graphics2D is better for 2d games

        if (gameLoopTimer.isRunning()) {
            g2d.drawImage(getBackgroundImage(), 0, 0, this);
        }
        else {
            g2d.drawImage(getBackgroundImage(), 0, 0, this);
            g2d.drawImage(getEndGameImage(), 0, 0, this);
        }
        

        p.draw(g2d);    // call draw method inside the player class
        c.draw(g2d);    // call draw method inside the controller class
    }

    public Image getBackgroundImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(background));
        return i.getImage();
    }

    public Image getEndGameImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(endGame));
        return i.getImage();
    }

    // GAME LOOP
    @Override
    public void actionPerformed(ActionEvent e) { 
        repaint();  // call the paint method
        p.update(); // call update method in player class
        c.update(); // call update method in controller class
    }


    public void endGame() {
        gameLoopTimer.stop(); // end the game
    }

}
