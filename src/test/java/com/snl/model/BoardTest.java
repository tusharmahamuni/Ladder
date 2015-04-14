package com.snl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.snl.model.TransportSquare.TransportType;

public class BoardTest {

	private static final int SQUARES_PER_ROW = 10;
	private static final int TOTAL_ROWS = 2;
	
	@Test
	public void createBoardWithValidParameters() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		assertFalse(board == null);
		assertEquals(2, board.totalRows());
		assertEquals(10, board.squaresPerRow());
		assertEquals(20, board.getBoardSquares().size());
		assertTrue(board.firstBoardSquare().position() == 1);
		assertTrue(board.lastBoardSquare().position() == (SQUARES_PER_ROW * TOTAL_ROWS));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createBoardWithInvalidValueForSquaresPerRow() throws Exception {
		new Board(2, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createBoardWithInvalidValueForTotalRows() throws Exception {
		new Board(SQUARES_PER_ROW, 0);
	}
	
	@Test
	public void checkBoardSize() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		assertEquals(SQUARES_PER_ROW * TOTAL_ROWS, board.size());
	}
	
	@Test
	public void checkForFirstBoardSquare() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		assertFalse(board.firstBoardSquare() == null);
		assertEquals(1, board.firstBoardSquare().position());
	}
	
	@Test
	public void checkForLastBoardSquare() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		assertFalse(board.lastBoardSquare() == null);
		assertEquals(SQUARES_PER_ROW * TOTAL_ROWS, board.lastBoardSquare().position());
	}
	
	@Test
	public void tryToGetBoardSquareForExistingSquare() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		final Square square = board.getBoardSquare(19);
		assertFalse(square == null);
		assertEquals(19, square.position());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tryToGetBoardSquareForNonExistingSquare() throws Exception {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		board.getBoardSquare(21);
	}
	
	@Test
	public void checkForConvertBoardSquareToLadderWithValidTransportValue() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		board.convertBoardSquareToLadder(4, 10);
		final TransportSquare ladder = (TransportSquare) board.getBoardSquare(4);
		assertEquals(4, ladder.position());
		assertEquals(TransportType.LADDER, ladder.transportType());
		assertEquals(10, ladder.transportLength());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkForConvertBoardSquareToLadderWithInvalidTransportValue() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		board.convertBoardSquareToLadder(4,-10);
	}
	
	@Test
	public void convertBoardSquareToSnakeWithValidTransportValue() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		board.convertBoardSquareToSnake(14, -10);
		final TransportSquare snake = (TransportSquare) board.getBoardSquare(14);
		assertEquals(14, snake.position());
		assertEquals(TransportType.SNAKE, snake.transportType());
		assertEquals(-10, snake.transportLength());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void convertBoardSquareToSnakeWithInvalidTransportValue() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		board.convertBoardSquareToSnake(14, 4);
	}
	
	@Test
	public void checkForFindBoardSquareInForwardDirection() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		final Square square = board.findBoardSquare(10, 5);
		assertEquals(15, square.position());
	}
	
	@Test
	public void checkForFindBoardSquareInBackwardDirection() {
		final Board board = new Board(SQUARES_PER_ROW, TOTAL_ROWS);
		final Square square = board.findBoardSquare(18, 6);
		assertEquals(16, square.position());
	}
}
