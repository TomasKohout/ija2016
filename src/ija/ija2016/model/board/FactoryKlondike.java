package ija.ija2016.model.board;

import ija.ija2016.model.cards.*;

/**
 * Created by xblaze31 on 24.3.2017.
 */
public class FactoryKlondike extends AbstractFactorySolitaire {
    private CardDeckMethods cd;

    public FactoryKlondike(){
    }

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

    @Override
    public CardDeck createCardDeck() {
        return cd.createStandardDeck();
    }

    @Override
    public CardDeck createTargetPack(Card.Color color) {
        return new CardTargetDeck(color,13);
    }

    @Override
    public CardStack createWorkingPack() {
        return new CardStackMethods(52);
    }
}
