package ija.ija2016.model.cards;
/**
 * Created by xblaze31 on 24.3.2017.
 */

/**
 * Interface describe basic deck for draw.
 */
public interface CardDeck{

    /**
     * @return Actual size of deck.
     */
    int size();

    /**
     * Put card into deck when is not full.
     * @param card Card
     * @return True when operation have access.
     */
    boolean put(Card card);

    /**
     * Always put card into deck.
     * @param card Card
     * @param src Destination place name.
     */
    void forcePut(Card card, String src);

    /**
     * Remove card from top of deck.
     * @return Card from top of deck.
     */
    Card pop();

    /**
     * Get reference to card on top of deck.
     * @return Card from top of deck
     */
    Card get();

    /**
     * Get reference to card on index in deck.
     * @param index Index
     * @return Card from index.
     */
    Card get(int index);

    /**
     * Check if deck is empty.
     * @return True when deck is empty.
     */
    boolean isEmpty();

}
