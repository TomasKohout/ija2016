/**
 * Class which represent move from Stack to Target Deck and Undo.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;

/**
 * Class which represent move from Stack to Target Deck and Undo.
 */
public class StackToTargetDeck implements Command{
    private  Transfer move;

    public StackToTargetDeck(Transfer move){
        this.move = move;
    }

    public boolean execute() {
        return move.StackToTargetDeck();
    }

    public void undo(){
        move.UndoStackToTargetDeck();
    }
}
