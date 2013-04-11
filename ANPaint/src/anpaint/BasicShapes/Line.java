package anpaint.BasicShapes;

import anpaint.DrawMethods.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

//the Line class is a leaf that describes a straight line
public class Line extends BasicShape implements Serializable{

    public Line() {
        this(new Point(),new Point(),new Color(0),false,0);
    }

    public Line(Line source) {
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

    public Line(Point p1, Point p2, Color colour, boolean style, int weight) {
        _pointSet = new ArrayList<>();
        _pointSet.add(p1);
        _pointSet.add(p2);
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
        /**
         * The draw for line is complicated due to the nature of how it is affected by thickness
         * The additional thickness lines must be drawn either vertically or horizontally depending on the direction of the line
         * The calculations determine the angle the the line makes with a hypothetical triangle,
         * and depending on that angle it will either draw the additional thickness lines either vertically or horizontally
         */
        int adjacent = _pointSet.get(1).x - _pointSet.get(0).x;
        int opposite = _pointSet.get(1).y - _pointSet.get(0).y;
        DrawTemplate draw;

        if (adjacent < 0)
            adjacent *= -1;
        if (opposite < 0)
            opposite *= -1;

        double theta = 0;

        if (adjacent != 0)
            theta = Math.toDegrees(Math.atan(opposite / adjacent));

        if (theta > 45 || adjacent == 0) {
            draw = new DrawLineX(this, g);
        }

        else {
            draw = new DrawLineY(this, g);
        }
    }

    @Override
    public void moveShape(int dx, int dy) {
        for (int i = 0; i < _pointSet.size(); i++) {
            _pointSet.get(i).translate(dx, dy);
        }
    }

    @Override
    public void resize(boolean increase) {
        int dx = _pointSet.get(1).x - _pointSet.get(0).x;
        int dy = _pointSet.get(1).y - _pointSet.get(0).y;

        if (increase) {
            if (dx > 0 && dy > 0) {
                _pointSet.get(1).x += 10;
                _pointSet.get(1).y += 10;
            }

            else if (dx > 0 && dy < 0) {
                _pointSet.get(1).x += 10;
                _pointSet.get(1).y -= 10;
            }

            else if (dx < 0 && dy > 0) {
                _pointSet.get(1).x -= 10;
                _pointSet.get(1).y += 10;
            }

            else if (dx < 0 && dy < 0) {
                _pointSet.get(1).x -= 10;
                _pointSet.get(1).y -= 10;
            }

            else if (dx == 0 && dy < 0) {
                _pointSet.get(1).y -= 10;
            }

            else if (dx == 0 && dy > 0) {
                _pointSet.get(1).y += 10;
            }

            else if (dy == 0 && dx < 0) {
                _pointSet.get(1).x -= 10;
            }

            else if (dy == 0 && dx > 0) {
                _pointSet.get(1).x += 10;
            }
        }

        else {
            if (dx > 0 && dy > 0 && dx > 10 && dy > 10) {
                _pointSet.get(1).x -= 10;
                _pointSet.get(1).y -= 10;
            }

            else if (dx > 0 && dy < 0 && dx > 10 && dy < -10) {
                _pointSet.get(1).x -= 10;
                _pointSet.get(1).y += 10;
            }

            else if (dx < 0 && dy > 0 && dx < -10 && dy > 10) {
                _pointSet.get(1).x += 10;
                _pointSet.get(1).y -= 10;
            }

            else if (dx < 0 && dy < 0 && dx < -10 && dy < -10) {
                _pointSet.get(1).x += 10;
                _pointSet.get(1).y += 10;
            }

            else if (dx == 0 && dy < 0 && dy < -10) {
                _pointSet.get(1).y += 10;
            }

            else if (dx == 0 && dy > 0 && dy > 10) {
                _pointSet.get(1).y -= 10;
            }

            else if (dy == 0 && dx < 0 && dx < -10) {
                _pointSet.get(1).x += 10;
            }

            else if (dy == 0 && dx > 0 && dx > 10) {
                _pointSet.get(1).x -= 10;
            }
        }
    }

    @Override
    public void toggleSelected() {
        _selected = !_selected;
    }

    public String toString() {
        return "Line";
    }
}