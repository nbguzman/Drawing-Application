package anpaint.Graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

//the Rectangle class is a leaf that describes a rectangle with varying width and height
public class Rectangle extends Graphic {

    public Rectangle(Point p1, Point p2, Point p3, Point p4, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1, p2, p3, p4};
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