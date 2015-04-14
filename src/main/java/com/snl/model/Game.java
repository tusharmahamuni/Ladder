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
public class Game {

	private Board board;
	private Queue<Player> players;
	private Player winner;
	
	public Game(int squaresPerRow, int totalRows, Player[] participants) {
		this.board = new Board(squaresPerRow, totalRows);
		this.addPlayers(participants);
		validate();
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Board gameBoard() {
		return this.board;
	}
	
	private void validate() {
		if(!(players.size() > 1)) {
			throw new IllegalArgumentException("No of players should be atleast 2");
		}
		
		if(board == null) {
			throw new IllegalArgumentException("board should not be null");
		}
	}
	
	/**
	 * @return the players
	 */
	public Queue<Player> getPlayers() {
		return players;
	}
	
	public void movePlayer(final int roll) {
		if(isRollValid(roll)){
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
		}else {
			throw new IllegalArgumentException("Invalid roll value=" + roll);
		}
	}
	
	private boolean isBonusRoll(final int roll) {
		return roll == Dice.MAX_FACES_VALUE;
	}
	
	private boolean isRollValid(final int roll) {
		return (roll >=Dice.MIN_FACE_VALUE  && roll <= Dice.MAX_FACES_VALUE);
	}
	public boolean notOver() {
		return this.winner == null;
	}
	
	public boolean isOver() {
		return !this.notOver();
	}
	
	public Player winner() {
		return this.winner;
	}
	
	public Player currentPlayer() {
		return players.peek();
	}
	
	
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
		final Square boardSquare = this.gameBoard().firstBoardSquare();
		for (Player player : participants) {
			player.occupyBoardSquare(boardSquare);
			players.add(player);
		}
	}
}
