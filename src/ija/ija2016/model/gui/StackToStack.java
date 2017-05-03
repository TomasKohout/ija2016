package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public class StackToStack implements Command{
    private  Transfer move;

    public StackToStack(Transfer move){
        this.move = move;
    }

    public void execute() {
        move.StackToStack();
    }

    public void undo(){
        move.UndoStackToStack();
    }
}
