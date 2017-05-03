package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;

/**
 * Created by tom on 2.5.17.
 */
public class MainToSwap implements Command{
    private  Transfer move;

    public MainToSwap(Transfer move){
        this.move = move;
    }

    public void execute() {
        move.MainToSwap();
    }

    public void undo(){
        move.UndoMainToSwap();
    }
}
