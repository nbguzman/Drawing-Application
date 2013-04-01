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
    private Point _point;
    private AppWindow _window;
    Graphics _g;

    public DrawPanel() {
        _shapeSet = new ArrayList<BasicShape>();
        //_window = AppWindow.getInstance();      /** not returning the unique instance **/
        this.setBackground(Color.white);

        //this gets the initial click of the mouse
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed (MouseEvent e) {
                _point = new Point(e.getX(),e.getY());
            }
        });

       /**
        * This will get the release of the mouse button
        * and draw a shape using the clicked point and the released point
        * based on what was selected
        */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased (MouseEvent e) {
//                Rectangle rect = new Rectangle(_point,new Point(e.getX(),e.getY()),Color.black,true,1);
//                _shapeSet.add(rect);
//                repaint();

//                Line line = new Line(_point,new Point(e.getX(),e.getY()),Color.black,true,1);
//                _shapeSet.add(line);
//                repaint();

//  incomplete
//                Point released = new Point(e.getX(),e.getY());
//                int radius =
//                Point centroid = new Point(_point.x - released.x, _point.y - released.y);
//                Circle circle = new Circle();
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