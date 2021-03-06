package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Color;
import java.awt.Graphics;

public class DrawTriangle extends DrawTemplate {

    public DrawTriangle(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        int _xSet[] = new int[] { shape._pointSet.get(0).x, shape._pointSet.get(1).x + i, shape._pointSet.get(2).x - i };
        int _ySet[] = new int[] { shape._pointSet.get(0).y + i, shape._pointSet.get(1).y - i, shape._pointSet.get(2).y - i };

        g.drawPolygon(_xSet, _ySet, 3);
    }
}