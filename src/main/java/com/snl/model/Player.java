/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class Player {

	private String name;
	private ISquare boardSquare;
	
	public Player(String playerName) {
		this.name = playerName;
	}
	
	public int currentPosition() {
		assert isPlayerValid();
		return boardSquare.position();
	}
	
	private boolean isPlayerValid() {
		return ((name != null && !name.equals(" ")) && boardSquare != null);
	}
	
	public void moveForward(int moves) {
		assert moves > 0;
		boardSquare.leave(this);
		boardSquare = boardSquare.moveToOccupy(moves);
		boardSquare.enter(this);
	}
	
	public boolean wins() {
		return boardSquare.isLastSquare();
	}
	
	public void joinGame(final Game game) {
		this.boardSquare = game.gameBoard().firstBoardSquare();
		boardSquare.enter(this);
		assert isPlayerValid();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
