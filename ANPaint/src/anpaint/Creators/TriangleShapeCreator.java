package anpaint.Creators;

import anpaint.AppWindow;
import anpaint.BasicShapes.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The TriangleShapeCreator class will create a Triangle and return it
 */
public class TriangleShapeCreator extends ShapeCreator {

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

        int height = e.getY() - point.y;
        return new Triangle(point, new Point(point.x - height / 2, point.y + height), new Point(point.x + height / 2, point.y + height), colour, window.getLineType(), window.getWeight());
    }
}
