/**
 * Class which represent move from Target Deck to Stack and Undo.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;

/**
 * Class which represent move from Target Deck to Stack and Undo.
 */
public class TargetDeckToStack implements Command{
    private  Transfer move;

    public TargetDeckToStack(Transfer move){
        this.move = move;
    }

    public boolean execute() {
        return move.TargetDeckToStack();
    }

    public void undo(){
        move.UndoTargetDeckToStack();
    }
}
