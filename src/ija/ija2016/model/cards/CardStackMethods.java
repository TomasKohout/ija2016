package ija.ija2016.model.cards;
import java.io.Serializable;
import java.util.*;


/**
 * Created by xblaze31 on 24.3.2017.
 */

/**
 * Class represents card stack.
 */
public class CardStackMethods implements CardStack, Serializable{
    //Atributy
    protected int size;
    protected Stack<Card> s = new Stack<Card>();

    /**
     * Create card stack by size.
     * @param size Stack size
     */
    public CardStackMethods(int size){
        this.size = size;
    }

    /**
     * Insert whole stack to stack.
     * @param stack Input stack
     * @return True if operation have access.
     */
    public boolean put(CardStack stack){
        boolean flag = true;

        for(int i = 0; i < stack.size();i++){
            flag = this.put(stack.get(i));
        }
        return flag;
    }

    /**
     * Always put card into deck.
     * @param card Card
     * @param src Destination place name.
     */
    public void forcePut(Card card, String src) {
        card.getJLabel().setText(card.toString() + "-" + src);
        this.s.push(card);
    }

    /**
     * Getting cards from stack to output stack until card in parameter is not equal to card in stack.
     * @param card Compare card
     * @return Stack of card after the given card. When card is not there return empty stack.
     */
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

    /**
     * Get reference to card on index in deck.
     * @param index Index
     * @return Card from index.
     */
    public Card get(int index){
        if (this.size() > index) {
            return this.s.get(index);
        }else
            return null;
    }

    /**
     * Get reference to card on top of deck.
     * @return Card from top of deck
     */
    public Card get(){
        if(this.isEmpty()){
            return null;
        }

        Card c = this.s.pop();
        this.s.push(c);
        return c;
    }

    /**
     * Put card into deck when is not full.
     * @param card Card
     * @return True when operation have access.
     */
    public boolean my_put(Card card){
        if(this.size > this.size()){
            this.s.push(card);
            return true;
        }
        else
            return false;
    }

    /**
     * Put card into deck when is not full and top card of stack is different color and less value by one then input card.
     * @param card Card
     * @return True when operation have access.
     */
   public boolean put (Card card)
   {
       if (this.s.size() + 1 > this.size)
           return false;

       CardMethods c = (CardMethods) this.get();

       if (c == null)
       {
           if (card.value() == 13)
           {
               this.s.push((CardMethods) card);
               return true;
           }
           return false;
       }

       if (card.similarColorTo(c))
           return false;

       if (c.compareValue(card) != 1)
           return false;

       this.s.push((CardMethods) card);
       return true;
   }

    /**
     * Remove card from top of stack.
     * @return Card from top of stack.
     */
    public Card pop(){
        return this.s.pop();
    }

    /**
     * Check if stack is empty.
     * @return True when deck is empty.
     */
    public boolean isEmpty(){
        return this.s.empty();
    }

    /**
     * @return Actual size of stack.
     */
    public int size(){
        return this.s.size();
    }


}
