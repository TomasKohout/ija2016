package ija.ija2016.model.cards;
import java.util.*;


/**
 * Created by xblaze31 on 24.3.2017.
 */
public class CardStackMethods implements CardStack{
    //Atributy
    protected int size;
    protected Stack<Card> s = new Stack<Card>();

    public CardStackMethods(int size){
        this.size = size;
    }


    public boolean put(CardStack stack){
        boolean flag = true;

        for(int i = 0; i < stack.size();i++){
            flag = this.put(stack.get(i));
        }
        return flag;
    }

    public void forcePut(Card card)
    {
        this.s.push(card);
    }

    public CardStack pop(Card card){
        CardStackMethods tmp_stack = new CardStackMethods(52);
        CardStackMethods card_stack = new CardStackMethods(52);
        Card tmp_card;


        while(this.size() > 0)
        {
            tmp_card = this.s.pop();
            tmp_stack.my_put(tmp_card);
            //System.out.println(tmp_card + "aaaaa" + card);

            if(tmp_card.toString().equals(card.toString()))
            {
                //Karta nalezena
                for (int i = tmp_stack.size(); i > 0; i--){
                    tmp_card = tmp_stack.pop();
                    card_stack.my_put(tmp_card);
                }
                card_stack.size = card_stack.size();
                return card_stack;
            }
        }

        return null;
    }

    public Card get(int index){
        if (this.size() > index) {
            return this.s.get(index);
        }else
            return null;
    }

    public Card get(){
        if(this.isEmpty()){
            return null;
        }

        Card c = this.s.pop();
        this.s.push(c);
        return c;
    }

    public boolean my_put(Card card){
        if(this.size > this.size()){
            this.s.push(card);
            return true;
        }
        else
            return false;
    }

    public boolean put(Card card){
        if(this.size > this.size()){
            int Sorting = 13 - this.size();
            //System.out.println(this.size() + "aaa"+ Sorting + card.value());

            if (this.get() == null && card.value() == 13) {
                this.s.push(card);
                return true;
            } else if (Sorting == (card.value())) {
                if(!this.get().similarColorTo(card)) {
                    this.s.push(card);
                    return true;
                }
            }

        }
        return false;
    }

    public Card pop(){
        return this.s.pop();
    }

    public boolean isEmpty(){
        return this.s.empty();
    }

    public int size(){
        return this.s.size();
    }

//    public void suffleDeck(CardDeck deck){
//
//    }

}
