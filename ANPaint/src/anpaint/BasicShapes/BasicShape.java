package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/*
* The BasicShape class defines the methods and members for the inherited classes,
* which are the various shapes that can be drawn.
* This is part of the Composite Pattern
*/
public abstract class BasicShape {
    protected Color _colour;
    protected Point[] _pointSet;
    protected boolean _style; //true solid, false dotted
    protected int _weight;

    //the method definitions that must be implemented in the sub classes
    abstract void add(BasicShape shape);
    abstract void remove(BasicShape shape);
    abstract ArrayList<BasicShape> getChildren();
    abstract void paint(Graphics g);
    abstract void move(int dx, int dy);
    abstract void resize();
}