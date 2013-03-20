package anpaint.Graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

//the Group class a composite object which contains all leafs that are grouped together
public class Group extends Shapes{
    //the contained children graphics
    private ArrayList<Shapes> _graphicSet;

    public Group() {
        _graphicSet = new ArrayList<Shapes>();
    }

    @Override
    void addGraphic(Shapes g) {
        _graphicSet.add(g);
    }

    @Override
    void removeGraphic(Shapes g) {
        _graphicSet.remove(g);
    }

    @Override
    ArrayList<Shapes> getChildren() {
        return _graphicSet;
    }

    @Override
    void draw() {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).draw();
    }

    @Override
    void move(int dx, int dy) {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).move(dx, dy);

        draw();
    }

    @Override
    void resize() {
        for (int i = 0; i < _graphicSet.size(); i++)
            _graphicSet.get(i).resize();

        draw();
    }
}