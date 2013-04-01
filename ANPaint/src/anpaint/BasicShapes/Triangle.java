package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Triangle class is a leaf that describes an equilateral triangle
public class Triangle extends BasicShape{
    int _xSet[];
    int _ySet[];

    public Triangle() {
        this(new Point(),new Point(),new Point(),new Color(0),false,0);
    }

    public Triangle(Point p1, Point p2, Point p3, Color colour, boolean style, int weight) {
        _xSet = new int[] { p1.x, p2.x, p3.x };
        _ySet = new int[] { p1.y, p2.y, p3.y };
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
        g.drawPolygon(_xSet, _ySet, 3);
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