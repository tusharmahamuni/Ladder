/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class LastSquare extends Square {

	public LastSquare(Board board, int position) {
		super(board, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
	
	@Override
	protected String squareContent() {
		return "FINISH";
	}
}
