package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawRectangleTopLeft extends DrawTemplate{

    public DrawRectangleTopLeft(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        int x1 = shape._pointSet.get(1).x;
        int y1 = shape._pointSet.get(1).y;
        g.drawRect(x1 + i, y1 + i, shape._pointSet.get(0).x - x1 - (i * 2), shape._pointSet.get(0).y - y1 - (i * 2));
    }
}