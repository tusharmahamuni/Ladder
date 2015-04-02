/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class Ladder extends AbstractTransportSquare {
	
	public Ladder(Board board, int position, int transportLength) {
		super(board, position, transportLength);
	}
	
	@Override
	public String squareContent() {
		final StringBuilder labelBuilder = new StringBuilder();
		labelBuilder.append(position).append("->").append(this.destination().position());
		return labelBuilder.toString();
	}

	@Override
	protected boolean isTransportValid() {
		return transportLength > 0;
	}
}
