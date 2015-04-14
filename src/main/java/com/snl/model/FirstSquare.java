/**
 * 
 */
package com.snl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tushar.Mahamuni
 *
 */
public class FirstSquare extends Square {
	private List<Player> players;

	public FirstSquare(Board board, int position) {
		super(board, position);
		players = new ArrayList<Player>();
	}
	
	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void enter(Player player) {
		if(!players.contains(player)) {
			players.add(player);
		}else {
			throw new UnsupportedOperationException("Player " + player + " already exists!");
		}
	}
	
	@Override
	public void leave(Player player) {
		if(players.contains(player)) {
			players.remove(player);
		}else {
			throw new UnsupportedOperationException("Player " + player + " already removed!");
		}
	}
	
	@Override
	public boolean isFirstSquare() {
		return true;
	}
	
	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}
	
	@Override
	public Square occupyOrBackToStart() {
		return this;
	}
	
	@Override
	protected String squareContent() {
		return "START";
	}
	
	@Override
	protected String player() {
		StringBuilder builder = new StringBuilder();
		for (Player player : players) {
			builder.append("<" + player + ">");
		}
		return builder.toString();
	}
}
