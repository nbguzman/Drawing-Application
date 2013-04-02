package anpaint;

import anpaint.BasicShapes.*;
import anpaint.Commands.DrawCommand;
import anpaint.Creators.*;
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
    private RectangleShapeCreator _rectangleFactory;
    private CircleShapeCreator _circleFactory;
    private LineShapeCreator _lineFactory;
    private TriangleShapeCreator _triangleFactory;
    Graphics _g;
    private ArrayList<BasicShape> _backup;

    public DrawPanel(AppWindow app) {
        _shapeSet = new ArrayList<>();
        //_window = AppWindow.getInstance();      /** not returning the unique instance **/
        _window = app;
        /**
         * this is a temp fix *
         */
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

        for (int i = 0; i < _shapeSet.size(); i++) {
            _shapeSet.get(i).draw(g);
        }
    }

    public void load() {
    }

    public void save() {
    }

    public void exit() {
        // maybe clear some stuff before executing the line below?
        System.exit(0);
    }

    public void copy() {
    }

    public void paste() {
    }

    //remove the last drawing done
    public void undoDraw() {
        if (_shapeSet.size() > 0) {
            int i = _shapeSet.size() - 1;
            _shapeSet.remove(_shapeSet.get(i));
            repaint();
        }
    }
    
    public ArrayList<BasicShape> getCurrentSet() {
        return _shapeSet;
    }
    
    public void setCurrentSet(ArrayList<BasicShape> source) {
        _shapeSet = new ArrayList<>(source);
        repaint();
    }
    
    public ArrayList<BasicShape> getBackupSet() {
        return _backup;
    }
    
    public void setBackupSet(ArrayList<BasicShape> source) {
        _backup = new ArrayList<>(source);
        repaint();
    }
    
    public void refreshCanvas() {
        repaint();
    }

    private void addListeners() {
        //this gets the initial click of the mouse
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                _point = new Point(e.getX(), e.getY());
            }
        });

        /**
         * This will get the release of the mouse button and draw a shape using
         * the clicked point and the released point based on what was selected
         */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                BasicShape shape;
                DrawCommand dc;

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
                _window.addCommand(new DrawCommand((DrawPanel)e.getComponent()));
                _window.clearBackup();
                repaint();
            }
        });
    }
}