package anpaint.Commands;

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
    public ExitCommand() {
        
    }
    
    public void execute() {
        
    }
    
    // Not to be implemented - pointless
    public void undo() {
        throw new UnsupportedOperationException("Cannot undo an exit command");
    }
    
    // Not to be implemented - pointless
    public void redo() {
        throw new UnsupportedOperationException("Cannot redo an exit command");
    }
}
