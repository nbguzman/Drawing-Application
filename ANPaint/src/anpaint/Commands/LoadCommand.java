package anpaint.Commands;

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
    public LoadCommand() {
        
    }
    
    public void execute() {
        
    }
    
    public void undo() {
        
    }
    
    // Will not be implemented - cannot redo a Load
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo a load command");
    }
}
