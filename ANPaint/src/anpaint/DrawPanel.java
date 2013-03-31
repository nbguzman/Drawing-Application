package anpaint;

import anpaint.BasicShapes.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The DrawPanel class will contain the implementations of the various commands
 * that a user can do. It acts as a receiver to the menu commands
 *
 * This is part of the Command Pattern
 */
public class DrawPanel extends JPanel {
    private ArrayList<BasicShape> _shapeSet;
    Graphics _g;

    public DrawPanel() {
        _shapeSet = new ArrayList<BasicShape>();
        this.setBackground(Color.white);

        //this allows for the panel to be clicked
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e) {
                Rectangle rect = new anpaint.BasicShapes.Rectangle(new Point(),new Point(),new Point(),new Point(),Color.black,true,1,e.getX(),e.getY());
                _shapeSet.add(rect);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < _shapeSet.size(); i++)
            _shapeSet.get(i).draw(g);
    }

    public void load() {

    }

    public void save() {

    }

    public void exit() {

    }

    public void copy() {

    }

    public void paste() {

    }
}