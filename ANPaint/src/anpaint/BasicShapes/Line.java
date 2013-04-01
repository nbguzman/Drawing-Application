package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Line class is a leaf that describes a straight line
public class Line extends BasicShape {

    public Line() {
        this(new Point(),new Point(),new Color(0),false,0);
    }

    public Line(Point p1, Point p2, Color colour, boolean style, int weight) {
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
        g.setColor(_colour);
        g.drawLine(_pointSet[0].x, _pointSet[0].y, _pointSet[1].x, _pointSet[1].y);
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