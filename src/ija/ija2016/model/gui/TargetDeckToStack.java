package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public class TargetDeckToStack {
    private String source;
    private String destination;
    public TargetDeckToStack()
    {

    }

    public void execute(CardTargetDeck source, CardStack dest, Card sourceCard, Card destCard)
    {
        if(destCard != null) {
            if (sourceCard.value() + 1 != destCard.value())
                return;
            if (sourceCard.similarColorTo(destCard))
                return;
            if (!(sourceCard.isTurnedFaceUp() && destCard.isTurnedFaceUp()))
                return;
        }
        else
        {
            if (sourceCard.value() != 13)
                return;
        }

        dest.put(source.pop());
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);
    }
    public void setString(String source, String destination)
    {
        this.destination = destination;
        this.source = source;
    }

}
