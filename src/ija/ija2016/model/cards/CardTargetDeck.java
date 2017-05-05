/**
 * Class represents Target Deck extending class CardDeckMethods.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */


package ija.ija2016.model.cards;

import java.io.Serializable;

/**
 * Class represents Target Deck extending class CardDeckMethods.
 */
public class CardTargetDeck extends CardDeckMethods implements Serializable {
    private Card.Color targetColor;

    /**
     *
     * @return Card from top of target deck or null.
     */
    @Override
    public Card pop(){
        if(this.size() > 0) {
            if (this.size() == 1)
                this.targetColor = null;
            return this.s.pop();
        }
        else
            return null;
    }

    /**
     * Create target deck with given size and color.
     * @param color Color of deck.
     * @param size Max size of deck
     */
    public CardTargetDeck(Card.Color color,int size){
        super(size);
    }

    /**
     * Put card on top of target deck when card have similar color type and value is greater by 1.
     * @param card Card
     * @return True of operation have access.
     */
    public boolean put(Card card){
        if(this.size > this.size()){
            if(this.targetColor == card.color()){
                if(this.size()+1  == (card.value())){
                    this.s.push(card);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Set color of target deck
     * @param color Color type
     */
    public void setColor(Card.Color color)
    {
        this.targetColor = color;
    }

    /**
     * Check setup of color type.
     * @return False when color type is not setup yet.
     */
    public boolean getColor()
    {
        return this.targetColor == null;
    }


}
