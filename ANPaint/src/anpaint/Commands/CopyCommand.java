package anpaint.Commands;

import anpaint.DrawPanel;

/**
 * The CopyCommand class will handle the copying of the current group's 
 * coordinates. It will save the selection's coordinates into a temporary
 * set
 * 
 * Current idea: save all coordinate points into a temporary set
 * 
 * This is part of the Command Pattern
 */
public class CopyCommand implements Command{
    DrawPanel __drawPanel;
    
    public CopyCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    public void execute() {
        __drawPanel.copy();
    }
    
    // Will not be implemented - cannot undo a Copy (must have pasted first)
    public void undo() {
        throw new UnsupportedOperationException("Cannot undo a copy command");
    }
    
    // Will not be implemented - cannot redo a Copy
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo a copy command");
    }
    
    public String toString() {
        return "copy command";
    }
}
