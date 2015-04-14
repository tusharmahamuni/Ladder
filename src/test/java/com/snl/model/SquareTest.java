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
public class SquareTest {
	
	private final Board board = new Board(10,2);
	
	@Test
	public void tryToCreateSquareForValidBoard() {
		final Square square = new Square(board, 2);
		assertFalse(square == null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void tryToCreateSquareForNullBoard() throws Exception {
		new Square(null,1);
	}
	
	@Test
	public void checkForSquarePosition() {
		final Square square = new Square(board, 2);
		assertEquals(2, square.position());
	}
	
	@Test
	public void checkGetToOccupyForValidMoves() throws Exception {
		final Square square = new Square(board, 2);
		final Square squareAtNewPos = square.getToOccupy(2);
		assertEquals(4, squareAtNewPos.position());
	}
	
	@Test
	public void checkIsFirstSquare() {
		final Square square = new Square(board, 2);
		assertFalse(square.isFirstSquare());
	}
	
	@Test
	public void checkIsLastSquare() {
		final Square square = new Square(board, 2);
		assertFalse(square.isLastSquare());
	}
	
	@Test
	public void checkIsOccupiedWhenPlayerIsPresentInSquare() {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		square.enter(tom);
		assertTrue(square.isOccupied());
	}
	
	@Test
	public void checkIsOccupiedWhenPlayerIsAbsentInSquare() {
		final Square square = new Square(board, 2);
		assertFalse(square.isOccupied());
	}
	
	@Test
	public void checkForOccupyOrBackToStartForUnOccupiedSquare() {
		final Square square = new Square(board, 2);
		final Square squareAtNewPos = square.occupyOrBackToStart();
		assertEquals(squareAtNewPos.position(), square.position());
	}
	
	@Test
	public void checkForOccupyOrBackToStartForOccupiedSquare() {
		final Player tom = new Player("TOM");
		final Square square = new Square(board, 2);
		tom.occupyBoardSquare(board.getBoardSquare(4));
		final Square squareAtNewPos = square.getToOccupy(2);
		assertEquals(1, squareAtNewPos.position());
		assertTrue(squareAtNewPos.isFirstSquare());
	}
	
	@Test
	public void tryToEnterPlayer() {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		square.enter(tom);
		assertEquals(square.player(), "<" + tom + ">");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void tryToEnterNullPlayer() throws Exception {
		final Square square = new Square(board, 2);
		square.enter(null);
	}
	
	@Test
	public void tryToLeavePlayer() {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		tom.occupyBoardSquare(square);
		square.leave(tom);
		assertEquals("", square.player());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void tryToLeavePlayerWhoIsNotPresentInSquare() throws Exception {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		square.leave(tom);
	}
	
	@Test
	public void checkForPlayer() {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		tom.occupyBoardSquare(square);
		assertEquals("<TOM>", square.player());
	}
	
	@Test
	public void checkForSquareContent() {
		final Square square = new Square(board, 2);
		assertEquals("2", square.squareContent());
	}
	
	@Test
	public void checkForToString() {
		final Square square = new Square(board, 2);
		final Player tom = new Player("TOM");
		tom.occupyBoardSquare(square);
		assertEquals("[2<TOM>]", square.toString());
	}
}
