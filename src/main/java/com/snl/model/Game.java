/**
 * 
 */
package com.snl.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Tushar.Mahamuni
 *
 */
public class Game implements IGame {

	private Board board;
	private Queue<Player> players;
	private Player winner;
	
	public Game(int squaresPerRow, int totalRows, Player[] participants) {
		this.board = new Board(squaresPerRow, totalRows);
		this.addPlayers(participants);
		assert isGameValid();
	}

	public Board gameBoard() {
		return this.board;
	}
	
	private boolean isGameValid() {
		return ((board != null) && (players.size() > 1));
	}
	
	/**
	 * @return the players
	 */
	public Queue<Player> getPlayers() {
		return players;
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#movePlayer(int)
	 */
	@Override
	public void movePlayer(final int roll) {
		assert roll >=Dice.MIN_FACE_VALUE  && roll <= Dice.MAX_FACES_VALUE;
		Player currentPlayer = null;
		if(isBonusRoll(roll)) {
			currentPlayer = players.peek();
		}else {
			currentPlayer = players.poll();
			players.add(currentPlayer);
		}
		
		currentPlayer.moveForward(roll);
		if(currentPlayer.wins()) {
			winner = currentPlayer;
		}
	}
	
	private boolean isBonusRoll(final int roll) {
		return roll == Dice.MAX_FACES_VALUE;
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#notOver()
	 */
	@Override
	public boolean notOver() {
		return this.winner == null;
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#isOver()
	 */
	@Override
	public boolean isOver() {
		return !this.notOver();
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#winner()
	 */
	@Override
	public Player winner() {
		return this.winner;
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#currentPlayer()
	 */
	@Override
	public Player currentPlayer() {
		return players.peek();
	}
	
	/* (non-Javadoc)
	 * @see com.snl.model.IGame#play()
	 */
	@Override
	public void play() {
		final Dice dice = new Dice();
		while(this.notOver()) {
			int roll = dice.roll();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("player " + this.currentPlayer() + " rolled dice and got " + roll);
			this.movePlayer(roll);
			System.out.println(this.board);
		}
		
		System.out.println("Winner is " + this.winner());
	}


	private void addPlayers(Player[] participants) {
		players = new LinkedList<Player>();
		for (Player player : participants) {
			player.joinGame(this);
			players.add(player);
		}
	}
}
