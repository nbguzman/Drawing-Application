package anpaint.BasicShapes;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public abstract class BasicShape extends Object {
    protected Color _colour;
    protected Point[] _pointSet;
    protected boolean _style; //true solid, false dotted
    protected int _weight;
    protected boolean _selected;

    //the method definitions that must be implemented in the sub classes
    abstract void add(BasicShape shape);
    abstract void remove(BasicShape shape);
    abstract ArrayList<BasicShape> getChildren();
    //abstract void paint(Graphics g);
    abstract void moveShape(int dx, int dy);
    public abstract void draw(Graphics g);
    abstract void resize();
    abstract void toggleSelected();
    public String toString() {
        String rc = "";
        
        rc += "\tcolor\t" + _colour.toString() + "\n" 
           + "\tstyle\t" + _style + "\n"
           + "\tweight\t" + _weight + "\n"
           + "\tpoints " + "\n";
        
        for (Point p: _pointSet) {
            rc += "\t\t" + p.x + ", " + p.y + "\n";
        }
        rc += "\n";
        
        return rc;
    }
}