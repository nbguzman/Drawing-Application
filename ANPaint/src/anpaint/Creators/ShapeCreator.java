package anpaint.Creators;

import anpaint.Graphics.Graphic;
import anpaint.Graphics.ShapeEnum;

/**
 * The ShapeCreator class determines which shape to create and calls that 
 * specific creator's constructor. This acts as the concrete factory class
 */
public class ShapeCreator extends Creator {
    
    @Override
    protected Graphic createShape(ShapeEnum shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
