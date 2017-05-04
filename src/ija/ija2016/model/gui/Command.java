package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public interface Command {
    public boolean execute();
    public void undo();
}
