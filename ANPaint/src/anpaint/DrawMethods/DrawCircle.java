package anpaint.DrawMethods;

import anpaint.BasicShapes.*;
import java.awt.Graphics;

public class DrawCircle extends DrawTemplate{

    public DrawCircle(BasicShape shape, Graphics g) {
        super(shape, g);
    }

    @Override
    void draw(int i, BasicShape shape, Graphics g) {
        Circle tempShape = (Circle)shape;
        g.drawOval(shape._pointSet[0].x + i, shape._pointSet[0].y + i, (tempShape._radius - i) * 2, (tempShape._radius - i) * 2);
    }
}