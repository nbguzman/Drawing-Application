package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/*
* the Circle class is a leaf that describes a circle
* the Circle class needs a radius to know how to paint
* the containing point in its _pointSet is the centroid
*/
public class Circle extends BasicShape{

    private int _radius;

    public Circle(Point p1, int radius, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1};
        _radius = radius;
        _colour = colour;
        _style = style;
        _weight = weight;
    }

    @Override
    void add(BasicShape shape) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    void remove(BasicShape shape) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    ArrayList<BasicShape> getChildren() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    void paint(Graphics g) {
        //unfinished implementation
    }

    @Override
    void move(int dx, int dy) {
        _pointSet[0].translate(dx, dy);
    }

    @Override
    void resize() {
        //unfinished implementation
    }
}