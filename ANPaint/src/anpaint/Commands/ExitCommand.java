package anpaint.Commands;

import anpaint.DrawPanel;

/**
 * The ExitCommand class will handle the exiting of the application. It should
 * exit gracefully
 * 
 * Current idea: make sure nothing is being used outside of the application 
 * and then exit
 * 
 * This is part of the Command Pattern
 */
public class ExitCommand implements Command {
    DrawPanel __drawPanel;
    
    public ExitCommand(DrawPanel dp) {
        __drawPanel = dp;
    }
    
    public void execute() {
        __drawPanel.exit();
    }
    
    // Not to be implemented - pointless
    public void undo() {
        throw new UnsupportedOperationException("Cannot undo an exit command");
    }
    
    // Not to be implemented - pointless
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo an exit command");
    }
    
     public String toString() {
        return "exit command";
    }
}
