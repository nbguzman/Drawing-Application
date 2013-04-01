package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/*
* the Circle class is a leaf that describes a circle
* the Circle class needs a radius to know how to paint
* the containing point in its _pointSet is the top left corner (of a containing square)
*/
public class Circle extends BasicShape{

    private int _radius;

    public Circle() {
        this(new Point(),0,new Color(0),false,0);
    }

    public Circle(Point p1, int radius, Color colour, boolean style, int weight) {
        _pointSet = new Point[] {p1};
        _radius = radius;
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
        g.setColor(_colour);
        g.drawOval(_pointSet[0].x, _pointSet[0].y, _radius * 2, _radius * 2);
    }

    @Override
    void moveShape(int dx, int dy) {
        _pointSet[0].translate(dx, dy);
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