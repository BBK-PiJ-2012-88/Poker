/**
 * 
 */
package poker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author 86
 *
 */
public class CheckerImplTest {
	private static Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);
	private static Card NINE_OF_SPADES = new Card(Rank.Nine, Suit.Spades);
	
	private static Card JACK_OF_HEARTS = new Card(Rank.Jack, Suit.Hearts);
	private static Card JACK_OF_CLUBS = new Card(Rank.Jack, Suit.Clubs);
	private static Card JACK_OF_DIAMONDS = new Card(Rank.Jack, Suit.Diamonds);
	
	private static Card TEN_OF_CLUBS = new Card(Rank.Ten, Suit.Clubs);
	
	@Test
	public void testCheckStraightFlush() {
		Card[] straightFlush = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		CheckResult result = checkHandConditionNoMultiples(straightFlush);
		
		assertEquals(ConditionType.StraightFlush, result.getConditionType());
		assertEquals(Arrays.asList(straightFlush), result.getSupportingCards().getCards());
	}
	
	@Test
	public void testCheckFlush() {
		Card[] flush = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, NINE_OF_SPADES};
		CheckResult result = checkHandConditionNoMultiples(flush);
		
		assertEquals(ConditionType.Flush, result.getConditionType());
		assertEquals(Arrays.asList(flush), result.getSupportingCards().getCards());
	}
	
	@Test
	public void testCheckStraight() {
		Card[] cardArray = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_CLUBS};
		Card[] multiplesExpected = new Card[] {};
		
		testForConditionType(ConditionType.Straight, cardArray, multiplesExpected);
		/*CheckResult result = checkHandConditionNoMultiples(straight);
		
		assertEquals(ConditionType.Straight, result.getConditionType());
		assertEquals(Arrays.asList(straight), result.getSupportingCards().getCards());*/
	}
	
	@Test
	public void testCheckFourOfAKind() {
		Card[] cardArray = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		
		testForConditionType(ConditionType.FourOfAKind, cardArray, multiplesExpected);
	}
	
	@Test
	public void testFullHouse() {
		Card[] cardArray = new Card[] {JACK_OF_HEARTS, TEN_OF_CLUBS, TEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS, TEN_OF_SPADES};
		
		testForConditionType(ConditionType.FullHouse, cardArray, multiplesExpected);
	}

	private void testForConditionType(ConditionType conditionType, Card[] cardArray, Card[] multiplesExpected) {
		List<Card> list = Arrays.asList(cardArray);
		Hand mockHand = mock(Hand.class);
		
		MultiplesChecker mockChecker = mock(MultiplesChecker.class);
		MultiplesCheckerFactory.getInstance().setMockChecker(mockChecker);
		
		when(mockHand.getCards()).thenReturn(list).thenReturn(list);
		when(mockHand.iterator()).thenReturn(list.iterator()).thenReturn(list.iterator());
				
		List<Card> multiplesExpectedList = Arrays.asList(multiplesExpected);
		CheckResult fakeResult = new CheckResult(conditionType, new HandImpl(multiplesExpectedList));
		List<CheckResult> multiplesResults = new LinkedList<CheckResult>();
		multiplesResults.add(fakeResult);
		
		when(mockChecker.checkMultiples()).thenReturn(multiplesResults);
		
		CheckResult result = (new CheckerImpl(mockHand)).check();
		
		assertEquals(conditionType, result.getConditionType());
		assertEquals(multiplesExpectedList, result.getSupportingCards().getCards());
	}

	/*private CheckResult checkHandConditionNoMultiples(Card[] cards) {
		List<Card> list = Arrays.asList(cards);
		Hand flushHand = mock(Hand.class);
		
		when(flushHand.getCards()).thenReturn(list).thenReturn(list);
		when(flushHand.iterator()).thenReturn(list.iterator()).thenReturn(list.iterator());
		
		MultiplesChecker mockChecker = mock(MultiplesChecker.class);
		MultiplesCheckerFactory.getInstance().setMockChecker(mockChecker);
		
		when(mockChecker.checkMultiples()).thenReturn(new ArrayList<CheckResult>());
		CheckResult result = (new CheckerImpl(flushHand)).check();
		return result;
	}*/

}
