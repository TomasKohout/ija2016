/**
 * Class which represent move from Stack to Stack and Undo.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;

/**
 * Class which represent move from Stack to Stack and Undo.
 */
public class StackToStack implements Command{
    private  Transfer move;

    public StackToStack(Transfer move){
        this.move = move;
    }

    public boolean execute() {
        return move.StackToStack();
    }

    public void undo(){
        move.UndoStackToStack();
    }
}
