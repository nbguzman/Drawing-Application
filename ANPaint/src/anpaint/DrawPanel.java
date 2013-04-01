package anpaint;

import anpaint.BasicShapes.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.lang.Math;

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

    public DrawPanel(AppWindow app) {
        _shapeSet = new ArrayList<BasicShape>();
        //_window = AppWindow.getInstance();      /** not returning the unique instance **/
        _window = app;                          /** this is a temp fix **/
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
                BasicShape shape;
                Color colour;

                switch (_window.getColour()) {
                    case "Black":
                        colour = Color.black;
                        break;
                    case "Blue":
                        colour = Color.blue;
                        break;
                    case "Red":
                        colour = Color.red;
                        break;
                    case "Green":
                        colour = Color.green;
                        break;
                    default:
                        colour = Color.black;
                        break;
                }
                
                switch (_window.getShapeType()) {
                    case "Rectangle":
                        shape = new Rectangle(_point, new Point(e.getX(), e.getY()), colour , _window.getLineType(), _window.getWeight());
                        break;
                    case "Triangle":
                        int height = e.getY() - _point.y;
                        shape = new Triangle(_point, new Point(_point.x - height / 2, _point.y + height), new Point(_point.x + height / 2, _point.y + height), colour, _window.getLineType(), _window.getWeight());
                        break;
                    case "Circle":
                        int radius = (int) Math.sqrt(Math.pow(_point.x - e.getX(), 2) + Math.pow(_point.y - e.getY(), 2));
                        shape = new Circle(new Point(_point.x - radius, _point.y - radius), radius, colour, _window.getLineType(), _window.getWeight());
                        break;
                    case "Line":
                        shape = new Line(_point, new Point(e.getX(), e.getY()), colour, _window.getLineType(), _window.getWeight());
                        break;
                    default:
                        shape = new Line(_point, new Point(e.getX(), e.getY()), colour, _window.getLineType(), _window.getWeight());
                        break;
                }

                _shapeSet.add(shape);
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