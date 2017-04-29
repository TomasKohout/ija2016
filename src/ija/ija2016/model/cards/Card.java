package ija.ija2016.model.cards;

import javax.swing.*;

/**
 * Created by xblaze31 on 24.3.2017.
 */
public interface Card {
    enum Color{
        CLUBS 	, DIAMONDS 	, HEARTS 	, SPADES //Diamod pika , club krize, spaded list, heart srdce
    }

    int value();

    boolean isTurnedFaceUp();

    boolean turnFaceUp();

    Card.Color color();

    boolean isBlackOrRed();

    boolean similarColorTo(Card c);

    int compareValue(Card c);

    JLabel getJLabel();

}
