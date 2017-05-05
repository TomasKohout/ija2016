/**
 * Class which represent move from Main deck to Swap Stack and Undo.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;


/**
 * Class which represent move from Main deck to Swap Stack and Undo.
 */
public class MainToSwap implements Command{
    private  Transfer move;

    public MainToSwap(Transfer move){
        this.move = move;
    }

    public boolean execute() {
        return move.MainToSwap();
    }

    public void undo(){
        move.UndoMainToSwap();
    }
}
