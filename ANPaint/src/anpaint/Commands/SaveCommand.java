package anpaint.Commands;

import anpaint.DrawPanel;

/**
 * The SaveCommand class will handle the saving of files. Will implement a way 
 * to save coordinates for each shape and put them into some sort of file. 
 * Would like to try out coding binary files
 * 
 * Current idea: file saves the current shapes on the draw panel (everything
 * is grouped into 1 object) and for each shape save the type of shape, the 
 * number of shapes, and the coordinates for each instance per line
 * 
 * This is part of the Command Pattern
 */
public class SaveCommand implements Command {
    DrawPanel __drawPanel;
    
    public SaveCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    public void execute() {
        __drawPanel.save();
    }
    
    // Might be implemented - could make temp files while running
    public void undo() {
        throw new UnsupportedOperationException("Cannot redo a save command");
    }
    
    // Will not be implemented - cannot redo a save
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo a save command");
    }
    
     public String toString() {
        return "save command";
    }
}
