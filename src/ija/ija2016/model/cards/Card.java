/**
 * Interface describes one card.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.cards;
import javax.swing.*;

/**
 * Interface describes one card.
 */
public interface Card {

    /**
     * Enumeration of colors of card.
     */
    enum Color{
        CLUBS 	, DIAMONDS 	, HEARTS 	, SPADES //Diamod pika , club krize, spaded list, heart srdce
    }

    /**
     * Check value of card.
     * @return Value of card.
     */
    int value();

    /**
     * Check that card is faced up.
     * @return True if card is turned face up, else false.
     */
    boolean isTurnedFaceUp();

    /**
     * Turn card face up.
     *
     * @return False if card is already face up, else true.
     */
    boolean turnFaceUp();

    /**
     * Check color type of card and return color type from enumeration type.
     * @return Color of card.
     */
    Card.Color color();

    /**
     * Check color of card.
     * @return If card is black return true otherwise false.
     */
    boolean isBlackOrRed();

    /**
     * Compare two cards in color.
     * @param c  Card
     * @return True if colors of cards are similar.
     */
    boolean similarColorTo(Card c);

    /**
     *
     * @param c Card
     * @return Function return zero when card values are same.
     */
    int compareValue(Card c);

    /**
     *
     * @return JLabel of card.
     */
    JLabel getJLabel();

    /**
     * Turn card.
     * @return Turned card.
     */
    Card turn();

}
