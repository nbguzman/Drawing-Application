package anpaint.Commands;

import anpaint.DrawPanel;

/**
 * The LoadCommand class will handle the loading of previous files saved
 * by this application. Will implement a way to load coordinates for each shape
 * saved from a file and plot them onto the DrawPanel
 * 
 * Current idea: text file contains shape and quantity and then 
 * the proceeding lines will contain the coordinates. Parse through that and
 * plot the shapes
 * 
 * This is part of the Command Pattern
 */
public class LoadCommand implements Command {
    DrawPanel __drawPanel;
    
    public LoadCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    public void execute() {
        __drawPanel.load();
    }
    
    // Will not be implemented - should not undo a Load
    public void undo() {
        throw new UnsupportedOperationException("Cannot undo a load command");
    }
    
    // Will not be implemented - cannot redo a Load
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo a load command");
    }
    
     public String toString() {
        return "load command";
    }
}
