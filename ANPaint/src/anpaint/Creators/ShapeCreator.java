package anpaint.Creators;

import anpaint.AppWindow;
import anpaint.BasicShapes.*;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The ShapeCreator abstract class declares the method to be implemented by the
 * shape creator classes. This class should only handle calling createShape.
 * This acts as the abstract factory class
 *
 * This is part of the Abstract Factory Pattern
 */
public abstract class ShapeCreator{

    public abstract BasicShape createShape(Point point, MouseEvent e, AppWindow window);
    public abstract BasicShape cloneShape(BasicShape src);

    protected BasicShape callCreator(ShapeEnum shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
