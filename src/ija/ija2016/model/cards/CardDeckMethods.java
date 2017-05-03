package ija.ija2016.model.cards;
import java.io.Serializable;
import java.util.*;


/**
 * Created by xblaze31 on 24.3.2017.
 */
public class CardDeckMethods implements CardDeck, Serializable{
    //Atributy
    protected int size;
    protected Stack<Card> s = new Stack<Card>();

    //Contructor
    public CardDeckMethods(int size){
        this.size = size;
    }

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

    public int size(){
        return this.s.size();
    }

    public boolean put(Card card){
        if(this.size > this.size()){
            this.s.push(card);
            return true;
        }
        else
            return false;
    }

    public void forcePut(Card card, String src){
        if(this.size > this.size()){
            this.s.push(card);
            card.getJLabel().setText(card.toString() + "-" + src);
        }
    }

    public Card pop(){
        if(this.size() > 0)
            return this.s.pop();
        else
            return null;
    }

    public Card get(){
        Card c = this.s.pop();
        if(c != null) {
            this.s.push(c);
            return c;
        }else{
            return null;
        }
    }

    public Card get(int index){
        return this.s.get(index);
    }

    public boolean isEmpty(){
        return this.s.empty();
    }


}
