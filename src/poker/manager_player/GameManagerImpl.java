package poker.manager_player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Deck;
import poker.hand_card.DeckFactory;



/**
 * This is the manager class for the poker game
 *
 */
public class GameManagerImpl implements GameManager {
	
	private CircularArrayList<Player> players;
	private Deck deck;
	//private final GameType type;	//TODO: decide where this is used
	
	public GameManagerImpl(GameType type, CircularArrayList<Player> players) {
		//this.type = type;
		this.deck = null;
		this.players = players;
		for(Player player: players){
			player.changeGameType(type);
		}
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#addPlayer(poker.manager_player.Player)
	 */
	@Override
	public void addPlayer(Player player){
		players.add(player);
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#deal()
	 */
	@Override
	public void deal(){
		deck = DeckFactory.getDeckFactory().getDeck();
		for(Player player: players){
			player.recieveCards(deck.dealCards(5));
		}
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#playRound()
	 */
	@Override
	public void playRound(){
		deal();
		playersChangeCards();
		evaluateWinner();
		deletePlayerCards();
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#playersChangeCards()
	 */
	@Override
	public void playersChangeCards(){
		for(Player player: players){
			int cardsToSwap = player.exchangeCards();
			if(cardsToSwap > 0){
				List<Card> cards = new ArrayList<Card>();
				for(int i = 0; i < cardsToSwap; i++){
					cards.add(deck.getCards().get(0));
					deck.getCards().remove(0);
				}
				player.recieveCards(cards);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#evaluateWinner()
	 */
	@Override
	public void evaluateWinner(){
		List<Player> tempList = new ArrayList<Player>();
		for(Player player: players){
			tempList.add(player);
		}
		Comparator<Player> c = players.getTheDealer().getCheckResultRanking();
		Collections.sort(tempList, c);
		System.out.println("The winner is " + tempList.get(0));
		System.out.println("Their hand was " + tempList.get(0).getHand().toString());
	}
	
	/* (non-Javadoc)
	 * @see poker.manager_player.GameManager#deletePlayerCards()
	 */
	@Override
	public void deletePlayerCards(){
		for(Player player: players){
			player.removeCards();
		}
	}
	
	public static void main(String [] args){
		CircularArrayList<Player> list = new CircularArrayList<Player>();
		GameManager game = new GameManagerImpl(GameType.fiveCardDraw, list);
		game.launch();
	}
	
}
