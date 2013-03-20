package anpaint.Creators;

import anpaint.Graphics.Graphic;
import anpaint.Graphics.ShapeEnum;

/**
 * The Creator abstract class declares the method to be implemented by the
 * family creator (shape creator) class. This class should only handle
 * calling createShape. This acts as the abstract factory class 
 * 
 * This is part of the Abstract Factory Pattern
 */
public abstract class Creator {
    
    protected abstract Graphic createShape(ShapeEnum shape);
    
    public Graphic callCreateShape(ShapeEnum shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
