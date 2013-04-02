/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anpaint.Commands;

import anpaint.BasicShapes.BasicShape;
import anpaint.DrawPanel;
import java.util.ArrayList;

/**
 *
 * @author besuto
 */
public class DrawCommand implements Command {
    DrawPanel __drawPanel;
    ArrayList<BasicShape> _current;
    
    public DrawCommand(DrawPanel dp) {
        __drawPanel = dp;
        _current = __drawPanel.getCurrentSet();
    }
    
    public void execute() {
        __drawPanel.refreshCanvas();
    }
    
    // Not to be implemented - pointless
    public void undo() {
        __drawPanel.setBackupSet(_current);
        __drawPanel.undoDraw();
    }
    
    public void redo() {
        _current = __drawPanel.getBackupSet();
        __drawPanel.setCurrentSet(_current);
    }
}
