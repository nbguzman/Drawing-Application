package anpaint.Creators;

import anpaint.AppWindow;
import anpaint.BasicShapes.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The CircleShapeCreator class will create a Circle and return it
 */
public class CircleShapeCreator extends ShapeCreator {

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

        int radius = (int) Math.sqrt(Math.pow(point.x - e.getX(), 2) + Math.pow(point.y - e.getY(), 2));
        return new Circle(new Point(point.x - radius, point.y - radius), radius, colour, window.getLineType(), window.getWeight());
    }

    @Override
    public BasicShape cloneShape(BasicShape src) {
        Circle circle = new Circle((Circle) src);
        return circle;
    }
}
