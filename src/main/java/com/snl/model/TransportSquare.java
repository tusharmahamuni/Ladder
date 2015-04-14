/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class TransportSquare extends Square {
	private int transportLength;
	private TransportType transportType;
	
	public enum TransportType {
		LADDER,
		SNAKE;
	}
	
	protected int transportLength() {
		return transportLength;
	}
	
	protected TransportType transportType() {
		return transportType;
	}
	
	public TransportSquare(Board board, int position, int transportLength, TransportType transportType) {
		super(board, position);
		this.transportType = transportType;
		this.transportLength = transportLength;
		validate();
	}

	@Override
	protected void validate() {
		if((TransportType.LADDER  == transportType && transportLength < 0) || (TransportType.SNAKE == transportType && transportLength > 0)) {
			throw new IllegalArgumentException("Invalid transportLength=" + transportLength);
		}
	}

	@Override
	public Square occupyOrBackToStart() {
		return this.destination().occupyOrBackToStart();
	}
	
	protected Square destination() {
		return this.board.getBoardSquare(this.position + this.transportLength);
	}
	
	@Override
	protected String squareContent() {
		final StringBuilder labelBuilder = new StringBuilder();
		switch (transportType) {
		case LADDER:
			labelBuilder.append(position).append("->").append(this.destination().position());
			break;
		case SNAKE:
			labelBuilder.append(this.destination().position()).append("<-").append(position);
			break;
		default:	
		}
		return labelBuilder.toString();
	}
}
