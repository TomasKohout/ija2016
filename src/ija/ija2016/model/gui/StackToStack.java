package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;

/**
 * Created by tom on 2.5.17.
 */
public class StackToStack{

    private String destination;
    private String source;

    public StackToStack()
    {

    }

    public void execute(CardStack source, CardStack dest, Card sourceCard, Card destCard) {

        if (destCard != null) {
            if (!(sourceCard.isTurnedFaceUp() && destCard.isTurnedFaceUp()))
                return;

            if (sourceCard.similarColorTo(destCard))
                return;

            if (sourceCard.value() + 1 != destCard.value())
                return;
        }
        else
        {
            if (sourceCard.value() != 13)
                return;
        }
        CardStack tmp = source.pop(sourceCard);
        if (dest.put(tmp))
        {
            int i = 0;
            while (i < tmp.size()) {
                tmp.get(i).getJLabel().setText(tmp.get(i).toString() + "-" + this.destination);
                i++;
            }
            if (!source.isEmpty())
                source.get().turnFaceUp();
        }
        else
        {
            while (!tmp.isEmpty())
                source.put(tmp.pop());
        }



    }

    public void setString(String source,String destination)
    {
        this.destination = destination;
        this.source = source;
    }

}
