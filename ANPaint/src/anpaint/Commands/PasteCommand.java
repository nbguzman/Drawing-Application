package anpaint.Commands;

import anpaint.BasicShapes.BasicShape;
import anpaint.DrawPanel;
import java.util.ArrayList;

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
    DrawPanel __drawPanel;
    
    public PasteCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    @Override
    public void execute() {
        __drawPanel.paste(); 
        __drawPanel.addCommand(this);
    }
    
    @Override
    public void undo() {
        __drawPanel.undoPaste();
    }
    
    @Override
    public void redo() {
        __drawPanel.redoPaste();
    }
    
     public String toString() {
        return "paste command";
    }
}
