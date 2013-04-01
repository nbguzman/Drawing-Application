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
        /**
         * The draw for line is complicated due to the nature of how it is affected by thickness
         * The additional thickness lines must be drawn either vertically or horizontally depending on the direction of the line
         * The calculations determine the angle the the line makes with a hypothetical triangle,
         * and depending on that angle it will either draw the additional thickness lines either vertically or horizontally
         */
        int adjacent = _pointSet[1].x - _pointSet[0].x;
        int opposite = _pointSet[1].y - _pointSet[0].y;

        if (adjacent < 0)
            adjacent *= -1;
        if (opposite < 0)
            opposite *= -1;

        double theta = 0;

        if (adjacent != 0)
            theta = Math.toDegrees(Math.atan(opposite / adjacent));

        if (theta != 0) {
            if (theta > 45) {
                for (int i = 0; i < _weight; i++) {
                    g.setColor(_colour);
                    g.drawLine(_pointSet[0].x + i, _pointSet[0].y, _pointSet[1].x + i, _pointSet[1].y);
                }
            }

            else {
                for (int i = 0; i < _weight; i++) {
                    g.setColor(_colour);
                    g.drawLine(_pointSet[0].x, _pointSet[0].y + i, _pointSet[1].x, _pointSet[1].y + i);
                }
            }
        }

        else {
            if (adjacent == 0) {
                for (int i = 0; i < _weight; i++) {
                    g.setColor(_colour);
                    g.drawLine(_pointSet[0].x + i, _pointSet[0].y, _pointSet[1].x + i, _pointSet[1].y);
                }
            }

            else {
                for (int i = 0; i < _weight; i++) {
                    g.setColor(_colour);
                    g.drawLine(_pointSet[0].x, _pointSet[0].y + i, _pointSet[1].x, _pointSet[1].y + i);
                }
            }
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