package anpaint.Graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

//the Triangle class is a leaf that describes an equilateral triangle
public class Triangle extends Graphic{

    public Triangle(Point p1, Point p2, Point p3, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1, p2, p3};
        _colour = colour;
        _style = style;
        _weight = weight;
    }

    @Override
    void addGraphic(Graphic g) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    void removeGraphic(Graphic g) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    ArrayList<Graphic> getChildren() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    void draw() {
        //unfinished implementation
    }

    @Override
    void move(int dx, int dy) {
        for (int i = 0; i < _pointSet.length; i++) {
            _pointSet[i].translate(dx, dy);
        }

        draw();
    }

    @Override
    void resize() {
        //unfinished implementation
        draw();
    }
}