package anpaint.Graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/*
* The Shapes class defines the methods and members for the inherited classes,
* which are the various shapes that can be drawn.
* This is part of the Composite Pattern
*/
public abstract class Shapes {
    protected Color _colour;
    protected Point[] _pointSet;
    protected boolean _style; //true solid, false dotted
    protected int _weight;

    //the method definitions that must be implemented in the sub classes
    abstract void addGraphic(Shapes g);
    abstract void removeGraphic(Shapes g);
    abstract ArrayList<Shapes> getChildren();
    abstract void draw();
    abstract void move(int dx, int dy);
    abstract void resize();
}