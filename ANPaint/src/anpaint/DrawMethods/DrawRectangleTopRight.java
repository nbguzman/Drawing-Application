package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawRectangleTopRight extends DrawTemplate{

    public DrawRectangleTopRight(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        g.drawRect(shape._pointSet[1].x - (shape._pointSet[1].x - shape._pointSet[0].x) + i, shape._pointSet[1].y + i, shape._pointSet[1].x - shape._pointSet[0].x - (i * 2), shape._pointSet[0].y - shape._pointSet[1].y - (i * 2));
    }
}