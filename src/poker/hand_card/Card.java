package poker.hand_card;

import java.util.Comparator;

import lombok.Data;


@Data public class Card {
	
	private final Rank rank;
	private final Suit suit;
	
	@Override
	public String toString(){
		String result = rank.toString() + " of " + suit.toString();
		return result;
	}

	private static final Comparator<Card> cardRankComparator = new Comparator<Card>() {

		@Override
		public int compare(Card card1, Card card2) {
			return card1.getRank().compareTo(card2.getRank());
		}		
	};
	
	public static Comparator<Card> getCardRankComparator() {
		return cardRankComparator;
	}
	
	
	
	
	/*public Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public Suit getSuit(){
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}*/
}