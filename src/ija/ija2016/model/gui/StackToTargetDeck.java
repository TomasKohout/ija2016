package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public class StackToTargetDeck {
    private String source;
    private String destination;
    public StackToTargetDeck()
    {

    }

    public void execute(CardStack source, CardTargetDeck dest, Card sourceCard, Card destCard)
    {
        if (destCard == null)
        {
            if (sourceCard.value() != 1)
                return;

            dest.setColor(sourceCard.color());
        }
        else
        {
            if(sourceCard.color() != destCard.color())
                return;

            if(sourceCard.value() != destCard.value() + 1)
                return;
        }

        if (source.get().toString().compareTo(sourceCard.toString()) != 0)
            return;

        dest.put(source.pop());
        if (source.isEmpty())
            return;
        source.get().turnFaceUp();
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);
    }
        public void setString(String source, String destination)
    {
        this.destination = destination;
        this.source = source;
    }

}
