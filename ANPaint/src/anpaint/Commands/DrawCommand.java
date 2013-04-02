package anpaint.Commands;

import anpaint.BasicShapes.BasicShape;
import anpaint.DrawPanel;
import java.util.ArrayList;

/**
 * The DrawCommand class will handle the redoing and undoing of the draw
 * functionality within the DrawPanel
 * 
 * This is part of the Command Pattern
 */
public class DrawCommand implements Command {
    DrawPanel __drawPanel;
    ArrayList<BasicShape> _current;
    
    //initialize the DrawPanel and the current ShapeSet that drawpanel has
    //set the drawpanel's backupset to what it currently is
    public DrawCommand(DrawPanel dp) {
        __drawPanel = dp;
        _current = __drawPanel.getCurrentSet();
        __drawPanel.setBackupSet(_current);
    }
    
    //set the current set to what it currently is, essentially repainting it
    @Override
    public void execute() {
        __drawPanel.setCurrentSet(_current);
    }
    
    //set the backup set to what it was currently then undo the draw
    /*
     * *NOTE*: reason why redo doesn't work more than once is because when
     * you undo, it creates a backup of what it is CURRENTLY. It forgets the
     * backup it made from before
     */
    @Override
    public void undo() {
        __drawPanel.setBackupSet(_current);
        __drawPanel.undoDraw();
    }
    
    //set the current set to the backup set made in drawpanel
    //and then set the current set of drawpanel to the backup set
    @Override
    public void redo() {
        _current = __drawPanel.getBackupSet();
        __drawPanel.setCurrentSet(_current);
        __drawPanel.setBackupSet(_current);
    }
}
