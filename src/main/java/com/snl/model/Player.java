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
	private Square boardSquare;
	
	/**
	 * @return the boardSquare
	 */
	public Square getBoardSquare() {
		return boardSquare;
	}

	/**
	 * @param boardSquare the boardSquare to set
	 */
	public void occupyBoardSquare(Square boardSquare) {
		this.boardSquare = boardSquare;
		this.boardSquare.enter(this);
	}

	public Player(String playerName) {
		this.name = playerName;
		validate();
	}
	
	public int currentPosition() {
			return boardSquare.position();
		}
	
	private void validate() {
		if((name == null) || (name.equals(" "))) {
			throw new IllegalArgumentException("name should not be empty or null");
		}
	}
	
	public void moveForward(int moves) {
		if(validMoves(moves)) {
			boardSquare.leave(this);
			boardSquare = boardSquare.getToOccupy(moves);
			boardSquare.enter(this);
		}else {
			throw new IllegalArgumentException("Invalid moves=" + moves);
		}
	}
	
	private boolean validMoves(final int moves) {
		return (moves >= 1) && (moves <= Dice.MAX_FACES_VALUE);
	}
	
	public boolean wins() {
		return boardSquare.isLastSquare();
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
