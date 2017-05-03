package ija.ija2016.model.gui;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;
import ija.ija2016.model.cards.CardDeck;
import javafx.beans.binding.ObjectExpression;

/**
 * Created by Tom on 3.5.2017.
 */
public class Transfer {
    public String destination; //Name
    public String source; //Name
    public Object src; //Deck
    public Object dst; //Deck
    public Card sourceCard; //Card
    public Card destCard; //Card
    private boolean turnBackLastCard = false;


    public Transfer(String source, String destination, Object src, Object dst, Card sourceCard, Card destCard){
        this.source = source;
        this.destination = destination;
        this.src = src;
        this.dst = dst;
        this.sourceCard = sourceCard;
        this.destCard = destCard;
    }

    public void MainToSwap()
    {
        CardDeck source = (CardDeck) this.src;
        CardStack dest = (CardStack) this.dst;
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
            tmp.turn();
            dest.forcePut(tmp, this.source);
        }
    }

    public void UndoMainToSwap() {
        CardStack source = (CardStack) this.dst;
        CardDeck dest = (CardDeck) this.src;

        if (source.isEmpty() && dest.isEmpty())
            return;

        if (source.isEmpty()) {
            while (!dest.isEmpty()) {
                source.forcePut(dest.pop().turn(), this.destination);
            }
        }
        else {
            Card tmp = source.pop();
            tmp.turn();
            dest.forcePut(tmp, this.source);
        }

    }

    public void StackToStack() {
        CardStack source = (CardStack) this.src;
        CardStack dest = (CardStack) this.dst;

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
            if (!source.isEmpty()) {
                source.get().turnFaceUp();
                this.turnBackLastCard = true;
            }
        }
        else
        {
            while (!tmp.isEmpty())
                source.put(tmp.pop());
        }
    }

    public void UndoStackToStack() {
        CardStack source = (CardStack) this.dst;
        CardStack dest = (CardStack) this.src;

        CardStack tmp = source.pop(sourceCard);

        if(turnBackLastCard)
            dest.get(dest.size()-1).turn();

        while (!tmp.isEmpty()){
            dest.forcePut(tmp.pop(),this.source);
        }
    }


    public void StackToTargetDeck()
    {
        CardStack source = (CardStack) this.src;
        CardTargetDeck dest = (CardTargetDeck) this.dst;

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
        this.turnBackLastCard = true;
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);
    }

    public void UndoStackToTargetDeck(){
        CardTargetDeck source = (CardTargetDeck) this.dst;
        CardStack dest = (CardStack) this.src;

        if(turnBackLastCard)
            dest.get(dest.size()-1).turn();

        dest.forcePut(source.pop(),this.source);
    }


    public void TargetDeckToStack()
    {
        CardTargetDeck source = (CardTargetDeck) this.src;
        CardStack dest = (CardStack) this.dst;

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

    public void UndoTargetDeckToStack(){
        CardStack source = (CardStack) this.dst;
        CardTargetDeck dest = (CardTargetDeck) this.src;

        dest.forcePut(source.pop(),this.source);
    }

    public void setString(String source,String destination)
    {
        this.destination = destination;
        this.source = source;
    }
}
