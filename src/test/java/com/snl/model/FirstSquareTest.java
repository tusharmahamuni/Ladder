/**
 * 
 */
package com.snl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * @author Tushar.Mahamuni
 *
 */
public class FirstSquareTest {

	private final Board board = new Board(10, 12);
	
	@Test
	public void checkIfSquareIsFirstSquare() {
		final Square firstSquare = new FirstSquare(board, 1);
		assertEquals(1, firstSquare.position());
		assertTrue(firstSquare.isFirstSquare());
	}
	
	@Test
	public void checkSquareContent() {
		final Square firstSquare = new FirstSquare(board, 1);
		assertEquals("START", firstSquare.squareContent());
	}
	
	@Test
	public void tryToAddMultiplePlayers() {
		final Player tom = new Player("TOM");
		final Player jerry = new Player("JERRY");
		final Square firstSquare = new FirstSquare(board, 1);
		firstSquare.enter(tom);
		firstSquare.enter(jerry);
		assertTrue(firstSquare.isOccupied());
		assertEquals(2, ((FirstSquare)firstSquare).getPlayers().size());
		assertEquals("<TOM><JERRY>", firstSquare.player());
	}
	
	@Test
	public void tryToMakeLeaveMultiplePlayers() {
		final Player tom = new Player("TOM");
		final Player jerry = new Player("JERRY");
		final Square firstSquare = new FirstSquare(board, 1);
		firstSquare.enter(tom);
		firstSquare.enter(jerry);
		
		firstSquare.leave(tom);
		firstSquare.leave(jerry);
		assertEquals(0, ((FirstSquare)firstSquare).getPlayers().size());
		assertFalse(firstSquare.isOccupied());
	}
	
	@Test
	public void checkForOccupyOrBackToStart() {
		final Square firstSquare = new FirstSquare(board, 1);
		assertEquals(firstSquare.position(), firstSquare.occupyOrBackToStart().position());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void tryToAddAlreadyAddedPlayer() {
		final Player tom = new Player("TOM");
		final Square firstSquare = new FirstSquare(board, 1);
		firstSquare.enter(tom);
		firstSquare.enter(tom);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void tryToMakeLeaveAlreadyLeftPlayer() {
		final Player tom = new Player("TOM");
		final Square firstSquare = new FirstSquare(board, 1);
		firstSquare.leave(tom);
		firstSquare.leave(tom);
	}
	
}
