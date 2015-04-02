/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class LastSquare extends AbstractSquare {

	public LastSquare(Board board, int position) {
		super(board, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
	
	@Override
	public String squareContent() {
		return "FINISH";
	}
}
