package ija.ija2016.model.gui;


/**
 * Created by tom on 2.5.17.
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
