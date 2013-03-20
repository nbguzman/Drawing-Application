package anpaint.Creators;

import anpaint.Graphics.Graphic;
import anpaint.Graphics.ShapeEnum;

/**
 * The ShapeCreator abstract class declares the method to be implemented by the
 * shape creator classes. This class should only handle calling createShape. 
 * This acts as the abstract factory class 
 * 
 * This is part of the Abstract Factory Pattern
 */
public abstract class ShapeCreator{
    
    protected abstract Graphic createShape(ShapeEnum shape);
    
    protected Graphic callCreator(ShapeEnum shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
