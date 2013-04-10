package anpaint.Creators;

import anpaint.AppWindow;
import anpaint.BasicShapes.BasicShape;
import anpaint.BasicShapes.Group;
import anpaint.BasicShapes.Line;
import anpaint.BasicShapes.ShapeEnum;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The GroupCreator class will create a Group and return it
 */
public class GroupCreator extends ShapeCreator {

    @Override
    public BasicShape createShape(Point point, MouseEvent e, AppWindow window) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public BasicShape cloneShape(BasicShape src) {
        Group group = new Group((Group) src);
        return group;
    }
}