package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Rectangle class is a leaf that describes a rectangle with varying width and height
public class Rectangle extends BasicShape {

    public Rectangle(Point p1, Point p2, Point p3, Point p4, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1, p2, p3, p4};
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
        for (int i = 0; i < _pointSet.length; i++) {
            _pointSet[i].translate(dx, dy);
        }
    }

    @Override
    void resize() {
        //unfinished implementation
    }
}