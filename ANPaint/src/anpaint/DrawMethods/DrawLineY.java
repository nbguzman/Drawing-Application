package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Graphics;

public class DrawLineY extends DrawTemplate{

    public DrawLineY(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        g.drawLine(shape._pointSet.get(0).x, shape._pointSet.get(0).y + i, shape._pointSet.get(1).x, shape._pointSet.get(1).y + i);
    }
}