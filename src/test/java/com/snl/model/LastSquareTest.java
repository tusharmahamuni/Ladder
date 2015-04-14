/**
 * 
 */
package com.snl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Tushar.Mahamuni
 *
 */
public class LastSquareTest {
	private final Board board = new Board(10, 12);
	
	@Test
	public void checkIfSquareIsLastSquare() {
		final Square lastSquare = new LastSquare(board, board.size());
		assertEquals(board.size(), lastSquare.position());
		assertTrue(lastSquare.isLastSquare());
	}
	
	@Test
	public void checkForSquareContent() {
		final Square lastSquare = new LastSquare(board, board.size());
		assertEquals("FINISH", lastSquare.squareContent());
	}
}
