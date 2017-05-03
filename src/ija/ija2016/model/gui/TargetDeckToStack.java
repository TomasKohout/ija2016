package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public class TargetDeckToStack implements Command{
    private  Transfer move;

    public TargetDeckToStack(Transfer move){
        this.move = move;
    }

    public void execute() {
        move.TargetDeckToStack();
    }

    public void undo(){
        move.UndoTargetDeckToStack();
    }
}
