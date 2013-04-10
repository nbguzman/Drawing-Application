package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawRectangleTopRight extends DrawTemplate{

    public DrawRectangleTopRight(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        int y1 = shape._pointSet.get(1).y;
        int x0 = shape._pointSet.get(0).x;
        int x1 = shape._pointSet.get(1).x;
        g.drawRect(x1 - (x1 - x0) + i, y1 + i, x1 - x0 - (i * 2), shape._pointSet.get(0).y - y1 - (i * 2));
    }
}