package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawLineX extends DrawTemplate{

    public DrawLineX(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        g.drawLine(shape._pointSet[0].x + i,shape. _pointSet[0].y, shape._pointSet[1].x + i, shape._pointSet[1].y);
    }
}