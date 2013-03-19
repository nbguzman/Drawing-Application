package anpaint.Graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

//the Group class a composite object which contains all leafs that are grouped together
public class Group extends Graphic{
    //the contained children graphics
    private ArrayList<Graphic> _graphicSet;

    public Group() {
        _graphicSet = new ArrayList<Graphic>();
    }

    @Override
    void addGraphic(Graphic g) {
        _graphicSet.add(g);
    }

    @Override
    void removeGraphic(Graphic g) {
        _graphicSet.remove(g);
    }

    @Override
    ArrayList<Graphic> getChildren() {
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