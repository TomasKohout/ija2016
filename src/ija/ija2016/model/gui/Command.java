package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public interface Command {
    public void execute();

    public void execute(CardTargetDeck source, CardStack dest, Card sourceCard, Card destCard);

    public void execute(CardStack source, CardStack dest, Card sourceCard, Card destCard);

    public void execute(CardStack source, CardTargetDeck dest, Card sourceCard, Card destCard);
}
