package anpaint.BasicShapes;

import anpaint.DrawMethods.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Triangle class is a leaf that describes an equilateral triangle
public class Triangle extends BasicShape{

    public Triangle() {
        this(new Point(),new Point(),new Point(),new Color(0),false,0);
    }

    public Triangle(Point p1, Point p2, Point p3, Color colour, boolean style, int weight) {
        _pointSet = new Point[] { p1, p2, p3 };
        _colour = colour;
        _style = style;
        _weight = weight;
        _selected = false;
    }

    @Override
    public void add(BasicShape shape) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public void remove(BasicShape shape) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public ArrayList<BasicShape> getChildren() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public void draw(Graphics g) {
        DrawTemplate draw = new DrawTriangle(this, g);
    }

    @Override
    public void moveShape(int dx, int dy) {
        for (int i = 0; i < _pointSet.length; i++) {
            _pointSet[i].translate(dx, dy);
        }
    }

    @Override
    public void resize() {
        //unfinished implementation
    }

    @Override
    public void toggleSelected() {
        _selected = !_selected;
    }
}