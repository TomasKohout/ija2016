package ija.ija2016.model.gui;

/**
 * Created by tom on 2.5.17.
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
