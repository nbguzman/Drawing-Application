package anpaint;

import anpaint.BasicShapes.*;
import anpaint.Commands.Command;
import anpaint.Commands.DrawCommand;
import anpaint.Creators.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    public void removeCmdHistory() {
        _window.clearCommandsBackup();
    }

    // will have to find a way to parse the file
    public void load() {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Neal Paint Images", "nap");
            chooser.setFileFilter(filter);
            int returnVal;
            returnVal = chooser.showOpenDialog(_window);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                setCurrentSet((ArrayList<BasicShape>) in.readObject());
                in.close();
                fileIn.close();
                setBackupSet(_shapeSet);
                removeCmdHistory();
                refreshCanvas();
            }


        } catch (Exception ex) {
            System.out.println("Loading error, StackTrace:");
            ex.printStackTrace();
        }
    }

    // will have to redo the file formatting to make it easier to parse
    public void save() {
        if (!_shapeSet.isEmpty()) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Neal Paint Images", "nap");
                chooser.setFileFilter(filter);
                int returnVal;
                returnVal = chooser.showSaveDialog(_window);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    if (!chooser.getSelectedFile().getAbsolutePath().endsWith(".nap")) {
                        file = new File(chooser.getSelectedFile() + ".nap");
                    }
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(_shapeSet);
                    out.close();
                    fileOut.close();
                }
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
                } else {
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
        _window.addCommand(new DrawCommand((DrawPanel) e.getComponent()));
        _window.clearBackup();
    }

    private void selectShapes(MouseEvent e) {
        int smallX, bigX, smallY, bigY;

        if (e.getX() < _point.x) {
            bigX = _point.x;
            smallX = e.getX();
        } else {
            bigX = e.getX();
            smallX = _point.x;
        }

        if (e.getY() < _point.y) {
            bigY = _point.y;
            smallY = e.getY();
        } else {
            bigY = e.getY();
            smallY = _point.y;
        }

        for (int i = 0; i < _shapeSet.size(); i++) {
            ArrayList<Point> pointSet = _shapeSet.get(i)._pointSet;

            if (_shapeSet.get(i)._selected) {
                _shapeSet.get(i).toggleSelected();
            }

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

        for (int i = 0; i < n; i++) {
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

                for (int j = 0; j < adding.size(); j++) {
                    _shapeSet.add(adding.get(j));
                }
            }
        }
    }
}