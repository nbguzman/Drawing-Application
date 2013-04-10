package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawRectangleBottomLeft extends DrawTemplate{

    public DrawRectangleBottomLeft(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        int x1 = shape._pointSet.get(1).x;
        int y1 = shape._pointSet.get(1).y;
        int y0 = shape._pointSet.get(0).y;
        g.drawRect(x1 + i, y1 - (y1 - y0) + i, shape._pointSet.get(0).x - x1 - (i * 2), y1 - y0 - (i * 2));
    }
}