package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
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
