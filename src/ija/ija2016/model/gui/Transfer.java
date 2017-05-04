package ija.ija2016.model.gui;

import ija.ija2016.model.cards.*;
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

    public boolean MainToSwap()
    {
        CardDeck source = (CardDeck) this.src;
        CardStack dest = (CardStack) this.dst;
        if (source.isEmpty() && dest.isEmpty())
            return false;

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
        return true;
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

    public boolean StackToStack() {
        CardStack source = (CardStack) this.src;
        CardStack dest = (CardStack) this.dst;

        if (destCard != null) {
            if (!(sourceCard.isTurnedFaceUp() && destCard.isTurnedFaceUp()))
                return false;

            if (sourceCard.similarColorTo(destCard))
                return false;

            if (sourceCard.value() + 1 != destCard.value())
                return false;
        }
        else
        {
            if (sourceCard.value() != 13)
                return false;
        }
        CardStack tmp = source.pop(sourceCard);
        if (dest.put(tmp))
        {
            int i = 0;
            while (i < tmp.size()) {
                tmp.get(i).getJLabel().setText(tmp.get(i).toString() + "-" + this.destination);
                i++;
            }
            if (!source.isEmpty() && !source.get(source.size()-1).isTurnedFaceUp()) {
                source.get().turnFaceUp();
                this.turnBackLastCard = true;
            }
        }
        else
        {
            while (!tmp.isEmpty())
                source.put(tmp.pop());
        }

        return true;
    }

    public void UndoStackToStack() {
        CardStack source = (CardStack) this.dst;
        CardStack dest = (CardStack) this.src;

        CardStack tmp = source.pop(sourceCard);

        if(turnBackLastCard)
            dest.get(dest.size()-1).turn();

        CardStack help = new CardStackMethods(20);

        while (!tmp.isEmpty()) {
            help.forcePut(tmp.pop(), this.source);
        }

        while (!help.isEmpty()) {
            dest.forcePut(help.pop(), this.source);
        }
    }


    public boolean StackToTargetDeck()
    {
        CardStack source = (CardStack) this.src;
        CardTargetDeck dest = (CardTargetDeck) this.dst;

        if (destCard == null)
        {
            if (sourceCard.value() != 1)
                return false;

            dest.setColor(sourceCard.color());
        }
        else
        {
            if(sourceCard.color() != destCard.color())
                return false;

            if(sourceCard.value() != destCard.value() + 1)
                return false;
        }

        if (source.get().toString().compareTo(sourceCard.toString()) != 0)
            return false;

        dest.put(source.pop());
        if (source.isEmpty())
            return false;

        if(!source.get(source.size()-1).isTurnedFaceUp()) {
            source.get().turnFaceUp();
            this.turnBackLastCard = true;
        }
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);

        return true;
    }

    public void UndoStackToTargetDeck(){
        CardTargetDeck source = (CardTargetDeck) this.dst;
        CardStack dest = (CardStack) this.src;

        if(turnBackLastCard)
            dest.get(dest.size()-1).turn();

        dest.forcePut(source.pop(),this.source);
    }


    public boolean TargetDeckToStack()
    {
        CardTargetDeck source = (CardTargetDeck) this.src;
        CardStack dest = (CardStack) this.dst;

        if(destCard != null) {
            if (sourceCard.value() + 1 != destCard.value())
                return false;
            if (sourceCard.similarColorTo(destCard))
                return false;
            if (!(sourceCard.isTurnedFaceUp() && destCard.isTurnedFaceUp()))
                return false;
        }
        else
        {
            if (sourceCard.value() != 13)
                return false;
        }

        dest.put(source.pop());
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);
        return true;
    }

    public void UndoTargetDeckToStack(){
        CardStack source = (CardStack) this.dst;
        CardTargetDeck dest = (CardTargetDeck) this.src;

        dest.forcePut(source.pop(),this.destination);
    }

    public void setString(String source,String destination)
    {
        this.destination = destination;
        this.source = source;
    }
}
