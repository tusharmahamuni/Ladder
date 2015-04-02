/**
 * 
 */
package com.snl.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.snl.model.Game;
import com.snl.model.IGame;
import com.snl.model.Player;

/**
 * @author Tushar.Mahamuni
 *
 */
public class SnakesAndLaddersMain {

	private IGame game;
	
	/**
	 * @return the game
	 */
	public IGame getGame() {
		return game;
	}

	public void startGame() {
		this.game.play();
	}
	
	public SnakesAndLaddersMain(int squaresPerRow, int noOfRows, Player[] participants) {
		this.game = new Game(squaresPerRow, noOfRows, participants);
		this.addLadders(createLadders());
		this.addSnakes(createSnakes());
	}
	
	private void addSnakes(final Map<Integer, Integer> snakeMap) {
		assert ((snakeMap != null) && (!snakeMap.isEmpty()));
		for(Entry<Integer, Integer> entry: snakeMap.entrySet()) {
			this.game.gameBoard().convertBoardSquareToSnake(entry.getKey(), entry.getValue());
		}
	}

	private void addLadders(final Map<Integer, Integer> ladderMap) {
		assert ((ladderMap != null) && (!ladderMap.isEmpty()));
		for(Entry<Integer, Integer> entry: ladderMap.entrySet()) {
			this.game.gameBoard().convertBoardSquareToLadder(entry.getKey(), entry.getValue());
		}
	}
	
	private Map<Integer, Integer> createSnakes() {
		final Map<Integer, Integer> snakeMap = new HashMap<Integer, Integer>();
		snakeMap.put(37, -30);
		snakeMap.put(74, -40);
		snakeMap.put(82, -63);
		snakeMap.put(107, -83);
		snakeMap.put(113, -40);
		snakeMap.put(115, -40);
		snakeMap.put(119, -41);
		return snakeMap;
	}

	private Map<Integer, Integer> createLadders() {
		final Map<Integer, Integer> ladderMap = new HashMap<Integer, Integer>();
		ladderMap.put(4, 30); //34
		ladderMap.put(9, 42); //51
		ladderMap.put(20, 38);//58
		ladderMap.put(28, 76);//104
		ladderMap.put(40, 39);//79
		ladderMap.put(51, 36);//87
		ladderMap.put(71, 40);//111
		return ladderMap;
	}
	public static void main(String[] args) {
		final int squaresPerRow = 10;
		final int totalRows = 12;
		final Player tom = new Player("TOM");
		final Player jerry = new Player("JERRY");
		final Player[] players = {tom, jerry};
		SnakesAndLaddersMain snakesAndLaddersMain = new SnakesAndLaddersMain(squaresPerRow, totalRows, players);
		System.out.println(snakesAndLaddersMain.getGame().gameBoard());
		snakesAndLaddersMain.startGame();
	}
	
	
	
	
}
