/**
 * Abstract class factory functions which need to be implemented in KlondikeFactory.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */


package ija.ija2016.model.board;

import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;



/**
 * Abstract class factory functions which need to be implemented in KlondikeFactory.
 */
public abstract class AbstractFactorySolitaire {

    public AbstractFactorySolitaire(){

    }

    /**
     * Create card deck with zero card 52 unique cards.
     * @return Object CardDeck.
     */
    public abstract CardDeck createCardDeck();

    /**
     * Create card with given value and color type.
     * @param color Color type
     * @param value Value of card
     * @return Object Card.
     */
    public abstract Card createCard(Card.Color color, int value);

    /**
     * Create target pack with given color type.
     * @param color Color type
     * @return Object CardDeck.
     */
    public abstract CardDeck createTargetPack(Card.Color color);

    /**
     * Create working pack with zero cards in there.
     * @return Object CardStack.
     */
    public abstract CardStack createWorkingPack();
}
