package anpaint.BasicShapes;

import anpaint.DrawMethods.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Rectangle class is a leaf that describes a rectangle with varying width and height
public class Rectangle extends BasicShape {

    public Rectangle() {
        this(new Point(),new Point(),new Color(0),false,0);
    }

    public Rectangle(Point p1, Point p2, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1, p2};
        _colour = colour;
        _style = style;
        _weight = weight;
        _selected = false;
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
    public void draw(Graphics g) {
        int width = _pointSet[1].x - _pointSet[0].x;
        int height = _pointSet[1].y - _pointSet[0].y;
        DrawTemplate draw;

        if (width >= 0 && height >= 0) {
            draw = new DrawRectangleBottomRight(this, g);
        }

        else if (width < 0 && height < 0) {
            draw = new DrawRectangleTopLeft(this, g);
        }

        else if (width < 0 && height >= 0) {
            draw = new DrawRectangleBottomLeft(this, g);
        }

        else {
            draw = new DrawRectangleTopRight(this, g);
        }
    }

    @Override
    void moveShape(int dx, int dy) {
        for (int i = 0; i < _pointSet.length; i++) {
            _pointSet[i].translate(dx, dy);
        }
    }

    @Override
    void resize() {
        //unfinished implementation
    }

    @Override
    void toggleSelected() {
        _selected = !_selected;
    }
}