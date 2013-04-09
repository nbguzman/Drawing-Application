package anpaint.BasicShapes;

import java.awt.*;
import java.util.ArrayList;

public abstract class BasicShape extends Object {
    public Color _colour;
    public ArrayList<Point> _pointSet;
    public boolean _style; //true solid, false dotted
    public int _weight;
    public boolean _selected;

    //the method definitions that must be implemented in the sub classes
    public abstract void add(BasicShape shape);
    public abstract void remove(BasicShape shape);
    public abstract ArrayList<BasicShape> getChildren();
    //abstract void paint(Graphics g);
    public abstract void moveShape(int dx, int dy);
    public abstract void draw(Graphics g);
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
    public abstract void resize();
    public abstract void toggleSelected();
}