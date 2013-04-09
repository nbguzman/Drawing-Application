package anpaint;

import anpaint.BasicShapes.*;
import anpaint.Commands.DrawCommand;
import anpaint.Creators.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    // will have to find a way to parse the file
    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("shapes"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
        } catch (Exception ex) {
            System.out.println("Loading error, StackTrace:");
            ex.printStackTrace();
        }
    }

    // will have to redo the file formatting to make it easier to parse
    public void save() {
        ArrayList<BasicShape> circles = new ArrayList<>();
        ArrayList<BasicShape> lines = new ArrayList<>();
        ArrayList<BasicShape> triangles = new ArrayList<>();
        ArrayList<BasicShape> rectangles = new ArrayList<>();

        if (!_shapeSet.isEmpty()) {
            try {
                FileWriter fstream = new FileWriter("shapes");
                BufferedWriter out = new BufferedWriter(fstream);

                for (BasicShape bs : _shapeSet) {
                    //System.out.println(bs.toString());
                }

                // add shapes from _shapeSet to corresponding arraylist shape
                for (BasicShape bs : _shapeSet) {
                    if (Circle.class == bs.getClass()) {
                        circles.add(bs);
                    } else if (Line.class == bs.getClass()) {
                        lines.add(bs);
                    } else if (Triangle.class == bs.getClass()) {
                        triangles.add(bs);
                    } else if (Rectangle.class == bs.getClass()) {
                        rectangles.add(bs);
                    }
                }

                if (!circles.isEmpty()) {
                    out.write("Circles " + circles.size() + "\n");
                    for (BasicShape bs : circles) {
                        out.append(bs.toString());
                    }
                }
                if (!lines.isEmpty()) {
                    out.write("Lines " + lines.size() + "\n");
                    for (BasicShape bs : lines) {
                        out.append(bs.toString());
                    }
                }
                if (!triangles.isEmpty()) {
                    out.write("Triangles " + triangles.size() + "\n");
                    for (BasicShape bs : triangles) {
                        out.append(bs.toString());
                    }
                }
                if (!rectangles.isEmpty()) {
                    out.write("Rectangles " + rectangles.size() + "\n");
                    for (BasicShape bs : rectangles) {
                        out.append(bs.toString());
                    }
                }

                out.close();
            } catch (Exception ex) {
                System.out.println("Saving error, StackTrace:");
                ex.printStackTrace();
            }
        }
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
                if (_window._draw) {
                    drawShape(e);
                }

                else {
                    selectShapes(e);
                }
                repaint();
            }
        });
    }

    private void drawShape(MouseEvent e) {
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
        _window.addCommand(new DrawCommand((DrawPanel)e.getComponent()));
        _window.clearBackup();
    }

    private void selectShapes(MouseEvent e) {
        int smallX, bigX, smallY, bigY;

        if (e.getX() < _point.x) {
            bigX = _point.x;
            smallX = e.getX();
        }

        else {
            bigX = e.getX();
            smallX = _point.x;
        }

        if (e.getY() < _point.y) {
            bigY = _point.y;
            smallY = e.getY();
        }

        else {
            bigY = e.getY();
            smallY = _point.y;
        }

        for (int i = 0; i < _shapeSet.size(); i++) {
            ArrayList<Point> pointSet = _shapeSet.get(i)._pointSet;

            if (_shapeSet.get(i)._selected)
                _shapeSet.get(i).toggleSelected();

            for (int j = 0; j < pointSet.size(); j++) {
                int x = pointSet.get(j).x;
                int y = pointSet.get(j).y;

                if (x < bigX && x > smallX && y < bigY && y > smallY) {
                    _shapeSet.get(i).toggleSelected();
                    System.out.println("Shape " + i + " Selected: " + _shapeSet.get(i)._selected);
                    j = pointSet.size();
                }
            }
        }
    }

    public void groupShapes() {
        Group group = new Group();
        int n = _shapeSet.size();
        int removed = 0;

        for (int i = 0; i < n ; i++) {
            if (_shapeSet.get(i - removed)._selected) {
                _shapeSet.get(i - removed).toggleSelected();
                group.add(_shapeSet.get(i - removed));
                _shapeSet.remove(i - removed);
                removed++;
            }
        }

        _shapeSet.add(group);
    }

    public void unGroupShapes() {
        int n = _shapeSet.size();

        for (int i = 0; i < n; i++) {
            if (_shapeSet.get(i)._selected && _shapeSet.get(i) instanceof Group) {
                ArrayList<BasicShape> adding = _shapeSet.get(i).getChildren();
                _shapeSet.remove(i);

                for (int j = 0; j < adding.size(); j++)
                    _shapeSet.add(adding.get(j));
            }
        }
    }
}