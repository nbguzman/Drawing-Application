package anpaint.BasicShapes;

import anpaint.DrawMethods.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

//the Triangle class is a leaf that describes an equilateral triangle
public class Triangle extends BasicShape implements Serializable{

    public Triangle() {
        this(new Point(),new Point(),new Point(),new Color(0),false,0);
    }

    public Triangle(Triangle source) {
       this._colour = source._colour;
        this._pointSet = new ArrayList<>();
        int n = source._pointSet.size();
        for (int i = 0; i < n; i++)
            this._pointSet.add(new Point(source._pointSet.get(i)));
        this._style = source._style;
        this._weight = source._weight;
        this._selected = source._selected;
        this._backupColor = _colour;
    }

    public Triangle(Point p1, Point p2, Point p3, Color colour, boolean style, int weight) {
        _pointSet = new ArrayList<>();
        _pointSet.add(p1);
        _pointSet.add(p2);
        _pointSet.add(p3);
        _colour = colour;
        _style = style;
        _weight = weight;
        _selected = false;
        this._backupColor = _colour;
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
        for (int i = 0; i < _pointSet.size(); i++) {
            _pointSet.get(i).translate(dx, dy);
        }
    }

    @Override
    public void resize(boolean increase) {
        if (increase) {
            if (_pointSet.get(1).y > _pointSet.get(0).y) {
                _pointSet.get(1).x -= 5;
                _pointSet.get(1).y += 10;
                _pointSet.get(2).x += 5;
                _pointSet.get(2).y += 10;
            }

            else {
                _pointSet.get(1).x += 5;
                _pointSet.get(1).y -= 10;
                _pointSet.get(2).x -= 5;
                _pointSet.get(2).y -= 10;
            }
        }

        else {
            if (_pointSet.get(1).y > _pointSet.get(0).y && _pointSet.get(1).y - _pointSet.get(0).y > 10) {
                _pointSet.get(1).x += 5;
                _pointSet.get(1).y -= 10;
                _pointSet.get(2).x -= 5;
                _pointSet.get(2).y -= 10;
            }

            else if (_pointSet.get(1).y < _pointSet.get(0).y && _pointSet.get(0).y - _pointSet.get(1).y > 10) {
                _pointSet.get(1).x -= 5;
                _pointSet.get(1).y += 10;
                _pointSet.get(2).x += 5;
                _pointSet.get(2).y += 10;
            }
        }
    }

    @Override
    public void toggleSelected() {
        _selected = !_selected;
    }

    public String toString() {
        return "Triangle";
    }
}