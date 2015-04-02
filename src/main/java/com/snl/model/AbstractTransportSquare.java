/**
 * 
 */
package com.snl.model;

/**
 * Base class for classes Snake and Ladder
 * @author Tushar.Mahamuni
 *
 */
public abstract class AbstractTransportSquare extends AbstractSquare {
	protected int transportLength;
	
	public AbstractTransportSquare(Board board, int position, int transportLength) {
		super(board, position);
		this.isTransportValid();
		this.transportLength = transportLength;
	}

	protected abstract boolean isTransportValid();
	
	@Override
	public ISquare occupyOrBackToStart() {
		return this.destination().occupyOrBackToStart();
	}
	
	protected ISquare destination() {
		return this.board.getBoardSquare(this.position + this.transportLength);
	}
}
