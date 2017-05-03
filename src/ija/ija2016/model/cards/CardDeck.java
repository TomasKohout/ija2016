package ija.ija2016.model.cards;
/**
 * Created by xblaze31 on 24.3.2017.
 */
public interface CardDeck{
    int size();

    boolean put(Card card);

    void forcePut(Card card, String src);

    Card pop();

    Card get();

    Card get(int index);

    boolean isEmpty();

}
