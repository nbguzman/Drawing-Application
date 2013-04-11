package anpaint;

import anpaint.BasicShapes.*;
import anpaint.Commands.Command;
import anpaint.Commands.DrawCommand;
import anpaint.Commands.MoveCommand;
import anpaint.Creators.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
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
    private ArrayList<BasicShape> _copyBuffer;
    private ArrayList<BasicShape> _copyBufferBackup;
    private PanelState _state;
    java.awt.Rectangle selection_;
    Point anchor;

    public DrawPanel(AppWindow app) {
        _shapeSet = new ArrayList<>();
        _backup = new ArrayList<>();
        _copyBuffer = new ArrayList<>();
        _copyBufferBackup = new ArrayList<>();
        _shapeSet = new ArrayList<>();
        _state = PanelState.DRAW;
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

        if (selection_ != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(selection_);
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
        try {
            clearCopyBuffer();
            BasicShape tempBS = null;

            for (BasicShape bs : _shapeSet) {
                if (bs.getSelected()) {
                    bs._colour = bs._backupColor;

                    if (bs instanceof Circle) {
                        tempBS = _circleFactory.cloneShape(bs);
                    }
                    else if (bs instanceof Line) {
                        tempBS = _lineFactory.cloneShape(bs);
                    }
                    else if (bs instanceof Triangle) {
                        tempBS = _triangleFactory.cloneShape(bs);
                    }
                    else if (bs instanceof Rectangle) {
                        tempBS = _rectangleFactory.cloneShape(bs);
                    }
                    else if (bs instanceof Group) {
                        for (int i = 0; i < bs.getChildren().size(); i++){
                            bs.getChildren().get(i)._colour = bs.getChildren().get(i)._backupColor;
                        }

                        tempBS = new Group((Group) bs);

                    }
                    if (tempBS != null) {
                        tempBS.moveShape(-tempBS._pointSet.get(0).x, -tempBS._pointSet.get(0).y);
                        _copyBuffer.add(tempBS);
                        _copyBufferBackup = _copyBuffer;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Copying error, StackTrace:");
            ex.printStackTrace();
        }
    }

    public void paste() {
        try {
            if (_copyBuffer != null) {
                _shapeSet.addAll(_copyBuffer);
                refreshCanvas();
            }
        } catch (Exception ex) {
            System.out.println("Pasting error, StackTrace:");
            ex.printStackTrace();
        }
    }

    public void undoPaste() {
        try {
            if (_shapeSet != null && _copyBuffer != null) {
                _shapeSet.removeAll(_copyBuffer);
                _copyBufferBackup = _copyBuffer;
            }
            refreshCanvas();

        } catch (Exception ex) {
            System.out.println("Undoing paste error, StackTrace:");
            ex.printStackTrace();
        }
    }

    public void redoPaste() {
        try {
            if (_copyBufferBackup != null) {
                _shapeSet.addAll(_copyBufferBackup);
                _copyBuffer = _copyBufferBackup;
            }
            refreshCanvas();
        } catch (Exception ex) {
            System.out.println("Redoing paste error, StackTrace:");
            ex.printStackTrace();
        }
    }

    //remove the last drawing done
    public void undoDraw() {
        if (_shapeSet.size() > 0) {
            int i = _shapeSet.size() - 1;
            _shapeSet.remove(_shapeSet.get(i));
            repaint();
        }
    }

    //undo last move
    public void undoMove() {
        setCurrentSet(_backup);
    }

    public ArrayList<BasicShape> getCurrentSet() {
        return _shapeSet;
    }

    public void setCurrentSet(ArrayList<BasicShape> source) {
        try {
            BasicShape tempBS = null;
            if (source != null) {
                _shapeSet.clear();
                for (BasicShape bs : source) {
                    bs._colour = bs._backupColor;
                    if (bs instanceof Circle) {
                        tempBS = _circleFactory.cloneShape(bs);
                    } else if (bs instanceof Line) {
                        tempBS = _lineFactory.cloneShape(bs);
                    } else if (bs instanceof Triangle) {
                        tempBS = _triangleFactory.cloneShape(bs);
                    } else if (bs instanceof Rectangle) {
                        tempBS = _rectangleFactory.cloneShape(bs);
                    } else if (bs instanceof Group) {
                        tempBS = new Group((Group) bs);

                    }
                    if (tempBS != null) {
                        _shapeSet.add(tempBS);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Setting BackupSet error, StackTrace:");
            ex.printStackTrace();
        }
        repaint();
    }

    public ArrayList<BasicShape> getBackupSet() {
        return _backup;
    }
    
    public ArrayList<BasicShape> copySet(ArrayList<BasicShape> source) {
        ArrayList<BasicShape> rc = new ArrayList<>();
        try {
            BasicShape tempBS = null;
            if (source != null) {
                for (BasicShape bs : source) {
                    bs._colour = bs._backupColor;
                    if (bs instanceof Circle) {
                        tempBS = _circleFactory.cloneShape(bs);
                    } else if (bs instanceof Line) {
                        tempBS = _lineFactory.cloneShape(bs);
                    } else if (bs instanceof Triangle) {
                        tempBS = _triangleFactory.cloneShape(bs);
                    } else if (bs instanceof Rectangle) {
                        tempBS = _rectangleFactory.cloneShape(bs);
                    } else if (bs instanceof Group) {
                        tempBS = new Group((Group) bs);
                    }
                    if (tempBS != null) {
                        rc.add(tempBS);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Setting BackupSet error, StackTrace:");
            ex.printStackTrace();
        }
        return rc;
    }

    public void setBackupSet(ArrayList<BasicShape> source) {
        try {
            BasicShape tempBS = null;
            if (source != null) {
                _backup.clear();
                for (BasicShape bs : source) {
                    bs._colour = bs._backupColor;
                    if (bs instanceof Circle) {
                        tempBS = _circleFactory.cloneShape(bs);
                    } else if (bs instanceof Line) {
                        tempBS = _lineFactory.cloneShape(bs);
                    } else if (bs instanceof Triangle) {
                        tempBS = _triangleFactory.cloneShape(bs);
                    } else if (bs instanceof Rectangle) {
                        tempBS = _rectangleFactory.cloneShape(bs);
                    } else if (bs instanceof Group) {
                        tempBS = new Group((Group) bs);
                    }
                    if (tempBS != null) {
                        _backup.add(tempBS);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Setting BackupSet error, StackTrace:");
            ex.printStackTrace();
        }
        repaint();
    }

    public void clearCopyBuffer() {
        _copyBuffer.clear();
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
                anchor = e.getPoint();
                selection_ = new java.awt.Rectangle(anchor);
            }
        });

        /**
         * This will get the release of the mouse button and draw a shape using
         * the clicked point and the released point based on what was selected
         */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                setBackupSet(_shapeSet);
                if (_state == PanelState.DRAW) {
                    //setBackupSet(_shapeSet);
                    drawShape(e);
                } else if (_state == PanelState.SELECT) {
                    selectShapes(e);
                } else if (_state == PanelState.MOVE) {
                    //setBackupSet(_shapeSet);
                    moveShape(e);
                }
                selection_ = null;
                repaint();

            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (_state == PanelState.SELECT) {
                    selection_.setBounds((int) Math.min(anchor.x, e.getX()), (int) Math.min(anchor.y, e.getY()),
                            (int) Math.abs(e.getX() - anchor.x), (int) Math.abs(e.getY() - anchor.y));
                    repaint();
                }
            }
        });

    }

    private void moveShape(MouseEvent e) {
        int n = _shapeSet.size();
        int x = e.getX();
        int y = e.getY();

        for (int i = 0; i < n; i++) {
            if (_shapeSet.get(i)._selected) {
                _shapeSet.get(i).moveShape(x - _shapeSet.get(i)._pointSet.get(0).x, y - _shapeSet.get(i)._pointSet.get(0).y);
            }
        }

        _window.addCommand(new MoveCommand(this));
    }

    private void resizeShape(MouseEvent e) {
    }

    public void addCommand(Command cmd) {
        _window.addCommand(cmd);
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
                if (_shapeSet.get(i) instanceof Group) {
                    for (int k = 0; k < _shapeSet.get(i).getChildren().size(); k++) {
                        _shapeSet.get(i).getChildren().get(k)._colour = _shapeSet.get(i).getChildren().get(k)._backupColor;
                    }
                }

                else {
                    _shapeSet.get(i)._colour = _shapeSet.get(i)._backupColor;
                }
            }

            for (int j = 0; j < pointSet.size(); j++) {
                int x = pointSet.get(j).x;
                int y = pointSet.get(j).y;

                if (x < bigX && x > smallX && y < bigY && y > smallY) {
                    _shapeSet.get(i).toggleSelected();
                    if (_shapeSet.get(i) instanceof Group) {
                        for (int k = 0; k < _shapeSet.get(i).getChildren().size(); k++) {
                            _shapeSet.get(i).getChildren().get(k)._colour = Color.lightGray;
                        }
                    }
                    else {
                        _shapeSet.get(i)._colour = Color.lightGray;
                    }

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
                _shapeSet.get(i - removed)._colour = _shapeSet.get(i - removed)._backupColor;
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

    public void changeState(PanelState state) {
        _state = state;
    }

    public void increaseShapeSize() {
        int n = _shapeSet.size();

        for (int i = 0; i < n; i++)
            if (_shapeSet.get(i)._selected)
                _shapeSet.get(i).resize(true);

        repaint();
    }

    public void decreaseShapeSize() {
        int n = _shapeSet.size();

        for (int i = 0; i < n; i++)
            if (_shapeSet.get(i)._selected)
                _shapeSet.get(i).resize(false);

        repaint();
    }
}