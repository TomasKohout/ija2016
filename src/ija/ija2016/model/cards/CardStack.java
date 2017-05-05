/**
 * Interface describe Stack of cards extending Card deck.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.cards;


/**
 * Interface describe Stack of cards extending Card deck.
 */
public interface CardStack extends CardDeck{

	/**
	 * Insert whole stack to stack.
	 * @param stack Input stack
	 * @return True if operation have access.
	 */
	boolean put(CardStack stack);

	/**
	 * Getting cards from stack to output stack until card in parameter is not equal to card in stack.
	 * @param card Compare card
	 * @return Stack of card after the given card. When card is not there return empty stack.
	 */
	CardStack pop(Card card);

	/**
	 * Always put card into deck.
	 * @param card Card
	 * @param src Destination place name.
	 */
	void forcePut(Card card, String src);

	/**
	 *
	 * @return Actual size of stack.
	 */
	int size();
}
