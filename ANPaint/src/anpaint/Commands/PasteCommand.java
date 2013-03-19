package anpaint.Commands;

/**
 * The PasteCommand class will handle the pasting of an object or group of
 * objects. Will try to implement pasting using the shortcut "Ctrl+v". It should
 * essentially paste all the saved points (taken from copy) and paste it onto
 * the same location it was before (for simplicity's sake, first off)
 * 
 * Current idea: iterate through all points from the a temporary set 
 * and draw them all onto DrawPanel. Discard the set after use
 * 
 * This is part of the Command Pattern
 */
public class PasteCommand implements Command{
    public PasteCommand() {
        
    }
    
    public void execute() {
        
    }
    
    public void undo() {
        
    }
    
    public void redo() {
        
    }
}
