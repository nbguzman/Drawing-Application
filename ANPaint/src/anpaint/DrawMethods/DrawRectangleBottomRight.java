package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawRectangleBottomRight extends DrawTemplate {

    public DrawRectangleBottomRight(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        int y0 = shape._pointSet.get(0).y;
        int x0 = shape._pointSet.get(0).x;
        g.drawRect(x0 + i, y0 + i, shape._pointSet.get(1).x - x0 - (i * 2), shape._pointSet.get(1).y - y0 - (i * 2));
    }
}
