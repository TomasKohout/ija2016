/**
 * Class implements methods from abstract class AbstractFactorySolitaire.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */


package ija.ija2016.model.board;

import ija.ija2016.model.cards.*;

/**
 * Class implements methods from abstract class AbstractFactorySolitaire.
 */
public class FactoryKlondike extends AbstractFactorySolitaire {
    private CardDeckMethods cd;

    public FactoryKlondike(){
    }

    /**
     * Create card with given value and color type.
     * @param color Color type
     * @param value Value of card
     * @return Object Card.
     */
    @Override
    public Card createCard(Card.Color color, int value) {
        Card c;
        if((value >= 1)&&(value <=13)) {
            c = new CardMethods(color, value);
        }
        else {
            return null;
        }

        return c;
    }

    /**
     * Create card deck with zero card 52 unique cards.
     * @return Object CardDeck.
     */
    @Override
    public CardDeck createCardDeck() {
        return cd.createStandardDeck();
    }

    /**
     * Create target pack with given color type.
     * @param color Color type
     * @return Object CardDeck.
     */
    @Override
    public CardDeck createTargetPack(Card.Color color) {
        return new CardTargetDeck(color,13);
    }

    /**
     * Create working pack with zero cards in there.
     * @return Object CardStack.
     */
    @Override
    public CardStack createWorkingPack() {
        return new CardStackMethods(25);
    }
}
