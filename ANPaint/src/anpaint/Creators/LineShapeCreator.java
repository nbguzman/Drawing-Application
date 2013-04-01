package anpaint.Creators;

import anpaint.AppWindow;
import anpaint.BasicShapes.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The LineShapeCreator class will create a Line and return it
 */
public class LineShapeCreator extends ShapeCreator {

    @Override
    public BasicShape createShape(Point point, MouseEvent e, AppWindow window) {
        Color colour;

        switch (window.getColour()) {
            case "Black":
                colour = Color.black;
                break;
            case "Blue":
                colour = Color.blue;
                break;
            case "Red":
                colour = Color.red;
                break;
            case "Green":
                colour = Color.green;
                break;
            default:
                colour = Color.black;
                break;
        }

        return new Line(point, new Point(e.getX(), e.getY()), colour, window.getLineType(), window.getWeight());
    }
}
