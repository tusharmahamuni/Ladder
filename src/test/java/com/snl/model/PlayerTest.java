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
public class PlayerTest {

	@Test
	public void checkPlayerString() {
		final Player tom = new Player("TOM");
		assertEquals("TOM",tom.toString());
	}
	
	
	@Test
	public void checkForCurrentPositionOfPlayer() {
		final Player tom = new Player("TOM");
		Board board = new Board(10, 2);
		tom.occupyBoardSquare(board.firstBoardSquare());
		assertEquals(1, tom.currentPosition());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tryToCreatePlayerWithNullName() throws Exception {
		new Player(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tryToCreatePlayerWithEmptyName() throws Exception {
		new Player(" ");
	}
	
	@Test
	public void checkForMoveForwardForPlayer() {
		final Player tom = new Player("TOM");
		Board board = new Board(10, 2);
		tom.occupyBoardSquare(board.firstBoardSquare());
		tom.moveForward(4);
		assertEquals(5, tom.currentPosition());
	}
	
	@Test
	public void checkForMoveForwardWhenPlayerWins() {
		final Player tom = new Player("TOM");
		Board board = new Board(10, 2);
		tom.occupyBoardSquare(board.firstBoardSquare());
		tom.moveForward(6);
		tom.moveForward(6);
		tom.moveForward(6);
		tom.moveForward(1);
		assertEquals(board.lastBoardSquare().position(), tom.currentPosition());
		assertTrue(board.lastBoardSquare().alreadyOccupied());
		assertTrue(tom.wins());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkForMoveForwardForInvalidValueOfMoves() throws Exception {
		final Player tom = new Player("TOM");
		Board board = new Board(10, 2);
		tom.occupyBoardSquare(board.firstBoardSquare());
		tom.moveForward(0);
	}
}
