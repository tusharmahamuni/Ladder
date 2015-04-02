/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class Snake extends AbstractTransportSquare {
	
	public Snake(Board board, int position, int transportLength) {
		super(board, position, transportLength);
	}
	
	@Override
	public String squareContent() {
		final StringBuilder labelBuilder = new StringBuilder();
		labelBuilder.append(this.destination().position()).append("<-").append(position);
		return  labelBuilder.toString();
	}

	@Override
	protected boolean isTransportValid() {
		return this.transportLength < 0;
	}
}
