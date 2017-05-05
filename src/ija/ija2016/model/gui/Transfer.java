/**
 * Class implementing all moves between all possible Stacks, Decks and Target Deck with theirs undo moves.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;

import ija.ija2016.model.cards.*;

/**
 * Class implementing all moves between all possible Stacks, Decks and Target Deck with theirs undo moves.
 */
public class Transfer {
    public String destination; //Name
    private String source; //Name
    public Object src; //Deck
    private Object dst; //Deck
    private Card sourceCard; //Card
    private Card destCard; //Card
    private boolean turnBackLastCard = false;

    /**
     * Construct move that take source and destination Object, name of source and destination position and last need
     * is source and destination card.
     * @param source Name of source position
     * @param destination Name of destination position
     * @param src Source Object
     * @param dst Destination Object
     * @param sourceCard Source Card
     * @param destCard Destination Card
     */
    public Transfer(String source, String destination, Object src, Object dst, Card sourceCard, Card destCard){
        this.source = source;
        this.destination = destination;
        this.src = src;
        this.dst = dst;
        this.sourceCard = sourceCard;
        this.destCard = destCard;
    }

    /**
     * Move from Main deck to Swap Stack operation.
     * @return True when move have access.
     */
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

    /**
     *  Undo move to Main deck to Swap Stack operation.
     */
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

    /**
     * Move from Stack to Stack operation.
     * @return True when move have access.
     */
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

    /**
     *  Undo move to Stack to Stack operation.
     */
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

    /**
     * Move from Stack to Target Deck operation.
     * @return True when move have access.
     */
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
            return true;

        if(!source.get(source.size()-1).isTurnedFaceUp()) {
            source.get().turnFaceUp();
            this.turnBackLastCard = true;
        }
        sourceCard.getJLabel().setText(sourceCard.toString() + "-" + this.destination);

        return true;
    }

    /**
     * Undo move to Stack to Target Deck operation.
     */
    public void UndoStackToTargetDeck(){
        CardTargetDeck source = (CardTargetDeck) this.dst;
        CardStack dest = (CardStack) this.src;

        if(turnBackLastCard)
            dest.get(dest.size()-1).turn();

        dest.forcePut(source.pop(),this.source);
    }

    /**
     * Move from Target Deck to Stack operation.
     * @return True when move have access.
     */
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

    /**
     *  Undo move to Target Deck to Stack operation.
     */
    public void UndoTargetDeckToStack(){
        CardStack source = (CardStack) this.dst;
        CardTargetDeck dest = (CardTargetDeck) this.src;

        dest.forcePut(source.pop(),this.destination);
    }

}
