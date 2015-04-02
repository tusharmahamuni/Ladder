/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public class RegularSquare extends AbstractSquare {

	public RegularSquare(Board board, int position) {
		super(board, position);
	}

	@Override
	public String squareContent() {
		return Integer.toString(position);
	}
}
