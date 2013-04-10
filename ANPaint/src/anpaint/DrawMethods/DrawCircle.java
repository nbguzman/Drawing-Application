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
        int size = (tempShape._radius - i) * 2;
        g.drawOval(shape._pointSet.get(0).x + i, shape._pointSet.get(0).y + i, size, size);
    }
}