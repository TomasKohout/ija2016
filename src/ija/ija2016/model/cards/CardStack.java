package ija.ija2016.model.cards;
/**
 * Created by xblaze31 on 24.3.2017.
 */
public interface CardStack extends CardDeck{
	boolean put(CardStack stack);

	CardStack pop(Card card);
	void forcePut(Card card);
	int size();
}
