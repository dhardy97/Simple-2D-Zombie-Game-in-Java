package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    Controller c;

    public MouseInput(Controller c) {
        this.c = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        c.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {   
    }

    @Override
    public void mouseExited(MouseEvent e) {  
    }
    
}
