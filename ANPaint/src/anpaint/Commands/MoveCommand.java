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
public class MoveCommand implements Command {
    DrawPanel __drawPanel;
    ArrayList<BasicShape> _current;
    ArrayList<BasicShape> _backup;
    
    //initialize the DrawPanel and the current ShapeSet that drawpanel has
    //set the drawpanel's backupset to what it currently is
    public MoveCommand(DrawPanel dp) {
        __drawPanel = dp;
        _current = __drawPanel.copySet(__drawPanel.getCurrentSet());//new ArrayList<>(__drawPanel.getCurrentSet());
        //__drawPanel.setBackupSet(_current); 
        _backup =  __drawPanel.copySet(__drawPanel.getBackupSet());//new ArrayList<>(__drawPanel.getBackupSet());
    }
    
    //set the current set to what it currently is, essentially repainting it
    @Override
    public void execute() {
    }
    
    //set the backup set to what it was before then undo the draw
    @Override
    public void undo() {
        __drawPanel.setBackupSet(_backup);
        __drawPanel.undoMove();
    }
    
    //set the current set to the backup set made in drawpanel
    //and then set the current set of drawpanel to the backup set
    @Override
    public void redo() {
        _current =  __drawPanel.copySet(__drawPanel.getBackupSet());
        __drawPanel.setCurrentSet(_backup);
        __drawPanel.setBackupSet(_current);
    }
    
     public String toString() {
        return "move command";
    }
}
