package poker.hand_card;

import java.util.List;

/**
 * A Deck encapsulates a standard 52 card deck of cards.
 * It allows shuffling of the deck and access to the cards.
 *
 */
public interface Deck {

	/**
	 * @return a list of the cards in the deck
	 */
	public abstract List<Card> getCards();

	/**
	 * Shuffles the deck
	 */
	public abstract void shuffleDeck();

}