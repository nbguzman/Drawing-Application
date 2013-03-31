package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Rectangle class is a leaf that describes a rectangle with varying width and height
public class Rectangle extends BasicShape {

    public Rectangle() {
        this(new Point(),new Point(),new Point(),new Point(),new Color(0),false,0,0,0);
    }

    public Rectangle(Point p1, Point p2, Point p3, Point p4, Color colour, boolean style, int weight, int x, int y) {
        _pointSet = new Point[] {p1, p2, p3, p4};
        _colour = colour;
        _style = style;
        _weight = weight;
        _selected = false;
        _x = x;
        _y = y;
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
        g.setColor(_colour);
        g.drawRect(_x, _y, 100, 100);
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