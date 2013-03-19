package anpaint.Commands;

/**
 * The Command interface declares the methods a command will have to
 * implement. Most, if not all, commands will have to handle an execute, undo,
 * and a redo command
 * 
 * This is part of the Command Pattern
 */
public interface Command {
    public void execute();
    public void undo();
    public void redo();
}
