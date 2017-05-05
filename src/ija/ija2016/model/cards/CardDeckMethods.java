package ija.ija2016.model.cards;
import java.io.Serializable;
import java.util.*;


/**
 * Created by xblaze31 on 24.3.2017.
 */

/**
 * Class represents card deck.
 */
public class CardDeckMethods implements CardDeck, Serializable{
    //Atributy
    protected int size;
    protected Stack<Card> s = new Stack<Card>();

    //Contructor

    /**
     * Create card deck by size.
     * @param size Size of deck
     */
    public CardDeckMethods(int size){
        this.size = size;
    }

    /**
     * Create standard playing deck contains every card and is size of 52 cards.
     * @return Standard CardDeck.
     */
    public static CardDeck createStandardDeck(){
        CardDeckMethods deck = new CardDeckMethods(52);
        for (Card.Color color : Card.Color.values()){
            for(int i = 1; i <= 13; i++)
            {
                CardMethods new_card = new CardMethods(color,i);
                deck.put(new_card);
            }
        }
        Collections.shuffle(deck.s);

        return deck;
    }

    /**
     * @return Actual size of deck.
     */
    public int size(){
        return this.s.size();
    }

    /**
     * Put card into deck when is not full.
     * @param card Card
     * @return True when operation have access.
     */
    public boolean put(Card card){
        if(this.size > this.size()){
            this.s.push(card);
            return true;
        }
        else
            return false;
    }

    /**
     * Always put card into deck.
     * @param card Card
     * @param src Destination place name.
     */
    public void forcePut(Card card, String src){
        if(this.size > this.size()){
            this.s.push(card);
            card.getJLabel().setText(card.toString() + "-" + src);
        }
    }

    /**
     * Remove card from top of deck.
     * @return Card from top of deck.
     */
    public Card pop(){
        if(this.size() > 0)
            return this.s.pop();
        else
            return null;
    }

    /**
     * Get reference to card on top of deck.
     * @return Card from top of deck
     */
    public Card get(){
        Card c = this.s.pop();
        if(c != null) {
            this.s.push(c);
            return c;
        }else{
            return null;
        }
    }

    /**
     * Get reference to card on index in deck.
     * @param index Index
     * @return Card from index.
     */
    public Card get(int index){
        return this.s.get(index);
    }

    /**
     * Check if deck is empty.
     * @return True when deck is empty.
     */
    public boolean isEmpty(){
        return this.s.empty();
    }
}
