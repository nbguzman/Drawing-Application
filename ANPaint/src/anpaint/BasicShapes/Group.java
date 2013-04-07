package anpaint.BasicShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//the Group class a composite object which contains all leafs that are grouped together
public class Group extends BasicShape{
    //the contained children graphics
    private ArrayList<BasicShape> _graphicSet;

    public Group() {
        _graphicSet = new ArrayList<BasicShape>();
    }

    @Override
    public void add(BasicShape shape) {
        _graphicSet.add(shape);
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
        for (int i =0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).toggleSelected();
    }
}