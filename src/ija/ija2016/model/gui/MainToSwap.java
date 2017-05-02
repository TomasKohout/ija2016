package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;

/**
 * Created by tom on 2.5.17.
 */
public class MainToSwap {
    private String source;
    private String destination;

    public MainToSwap(){

    }

    public void execute(CardDeck source, CardStack dest)
    {
        if (source.isEmpty() && dest.isEmpty())
            return;

        if (source.isEmpty()) {
            while (!dest.isEmpty()) {
                source.put(dest.pop().turn());
            }
        }
        else
        {
            Card tmp = source.pop();
            tmp.turnFaceUp();
            dest.forcePut(tmp, this.destination);
        }
    }
    public void setString(String source, String destination)
    {
        this.destination = destination;
        this.source = source;
    }


}
