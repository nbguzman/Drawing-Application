package anpaint;

import anpaint.BasicShapes.*;
import anpaint.Creators.*;
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
    private RectangleShapeCreator _rectangleFactory;
    private CircleShapeCreator _circleFactory;
    private LineShapeCreator _lineFactory;
    private TriangleShapeCreator _triangleFactory;

    Graphics _g;

    public DrawPanel(AppWindow app) {
        _shapeSet = new ArrayList<BasicShape>();
        //_window = AppWindow.getInstance();      /** not returning the unique instance **/
        _window = app;                          /** this is a temp fix **/
        _rectangleFactory = new RectangleShapeCreator();
        _circleFactory = new CircleShapeCreator();
        _lineFactory = new LineShapeCreator();
        _triangleFactory = new TriangleShapeCreator();
        this.setBackground(Color.white);
        addListeners();
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

    private void addListeners() {
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

                switch (_window.getShapeType()) {
                    case "Rectangle":
                        shape = _rectangleFactory.createShape(_point, e, _window);
                        break;
                    case "Triangle":
                        shape = _triangleFactory.createShape(_point, e, _window);
                        break;
                    case "Circle":
                        shape = _circleFactory.createShape(_point, e, _window);
                        break;
                    case "Line":
                        shape = _lineFactory.createShape(_point, e, _window);
                        break;
                    default:
                        shape = _lineFactory.createShape(_point, e, _window);
                        break;
                }

                _shapeSet.add(shape);
                repaint();
            }
        });
    }
}