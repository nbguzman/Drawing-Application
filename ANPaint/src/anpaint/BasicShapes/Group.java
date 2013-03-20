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
    void add(BasicShape shape) {
        _graphicSet.add(shape);
    }

    @Override
    void remove(BasicShape shape) {
        _graphicSet.remove(shape);
    }

    @Override
    ArrayList<BasicShape> getChildren() {
        return _graphicSet;
    }

    @Override
    void paint(Graphics g) {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).paint(g);
    }

    @Override
    void move(int dx, int dy) {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).move(dx, dy);
    }

    @Override
    void resize() {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).resize();
    }
}