package anpaint.BasicShapes;

import anpaint.Creators.CircleShapeCreator;
import anpaint.Creators.LineShapeCreator;
import anpaint.Creators.RectangleShapeCreator;
import anpaint.Creators.TriangleShapeCreator;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

//the Group class a composite object which contains all leafs that are grouped together
public class Group extends BasicShape implements Serializable{
    //the contained children graphics
    private ArrayList<BasicShape> _graphicSet;

    public Group() {
        this._graphicSet = new ArrayList<>();
        this._pointSet = new ArrayList<>();
    }

    public Group(Group source) {
        _colour = source._colour;
        this._pointSet = new ArrayList<>();
        int n = source._pointSet.size();
        for (int i = 0; i < n; i++)
            this._pointSet.add(new Point(source._pointSet.get(i)));
        _style = source._style;
        _weight = source._weight;
        _selected = source._selected;
        _graphicSet = source._graphicSet;
        
        this._graphicSet = new ArrayList<>();
        n = source._graphicSet.size();
        BasicShape shape;
        RectangleShapeCreator _rectangleFactory = new RectangleShapeCreator();
        CircleShapeCreator _circleFactory = new CircleShapeCreator();
        LineShapeCreator _lineFactory = new LineShapeCreator();
        TriangleShapeCreator _triangleFactory = new TriangleShapeCreator();
        
        for (int i = 0; i < n; i++) {
            if (source._graphicSet.get(i) instanceof Circle) {
                shape = _circleFactory.cloneShape(source._graphicSet.get(i));
            } 
            else if (source._graphicSet.get(i) instanceof Line) {
                shape = _lineFactory.cloneShape(source._graphicSet.get(i));
            } 
            else if (source._graphicSet.get(i) instanceof Triangle) {
                shape = _triangleFactory.cloneShape(source._graphicSet.get(i));
            } 
            else if (source._graphicSet.get(i) instanceof Rectangle) {
                shape = _rectangleFactory.cloneShape(source._graphicSet.get(i));
            } 
            else {
                shape = new Group((Group) source._graphicSet.get(i));
            }
            
            this._graphicSet.add(shape);
        }
    }
    
    @Override
    public void add(BasicShape shape) {
        _graphicSet.add(shape);

        for (int i = 0; i < shape._pointSet.size(); i++)
            _pointSet.add(shape._pointSet.get(i));
    }

    @Override
    public void remove(BasicShape shape) {
        _graphicSet.remove(shape);
    }

    @Override
    public ArrayList<BasicShape> getChildren() {
        return _graphicSet;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).draw(g);
    }

    @Override
    public void moveShape(int dx, int dy) {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).moveShape(dx, dy);
    }

    @Override
    public void resize() {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).resize();
    }

    @Override
    public void toggleSelected() {
        _selected = ! _selected;

        for (int i =0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).toggleSelected();
    }
    
    public String toString() {
        return "Group";
    }
}