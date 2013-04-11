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
    
    public MoveCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    @Override
    public void execute() {
        
    }
    
    @Override
    public void undo() {
        __drawPanel.undoMove();
    }

    @Override
    public void redo() {
    }
}
