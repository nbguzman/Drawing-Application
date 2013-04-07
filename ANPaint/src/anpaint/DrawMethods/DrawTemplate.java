package anpaint.DrawMethods;

import anpaint.BasicShapes.BasicShape;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This pattern will be used to reduce the amount replicated code in the draw methods of each shape,
 * and isolate the differences of the algorithm in draw
 */
public abstract class DrawTemplate {

    protected DrawTemplate(BasicShape shape, Graphics g) {
        for (int i = 0; i < shape._weight; i++) {
            g.setColor(shape._colour);
            draw(i, shape, g);
        }
    }

    abstract void draw(int i, BasicShape shape, Graphics g);
}