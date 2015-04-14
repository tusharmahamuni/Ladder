/**
 * 
 */
package com.snl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.snl.model.TransportSquare.TransportType;
/**
 * @author Tushar.Mahamuni
 *
 */
public class TransportSquareTest {

	private final Board board = new Board(10, 12);
	
	@Test
	public void createTransportSquareWithTransportType_LADDER() {
		final TransportSquare ladder = new TransportSquare(board, 37, 40, TransportType.LADDER);
		assertTrue(ladder != null);
		assertEquals(37, ladder.position());
		assertEquals(40, ladder.transportLength());
		assertEquals(TransportType.LADDER, ladder.transportType());
	}
	
	@Test
	public void createTransportSquareWithTransportType_SNAKE() {
		final TransportSquare snake = new TransportSquare(board, 97, -20, TransportType.SNAKE);
		assertTrue(snake != null);
		assertEquals(97, snake.position());
		assertEquals(-20, snake.transportLength());
		assertEquals(TransportType.SNAKE, snake.transportType());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tryToAddNegativeTransportForLADDER() throws Exception {
		new TransportSquare(board, 37, -10, TransportType.LADDER);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tryToAddPositiveTransportForSNAKE() throws Exception {
		new TransportSquare(board, 37, 20, TransportType.SNAKE);
	}
	
	@Test
	public void checkSquareContentForLADDER() {
		final TransportSquare ladder = new TransportSquare(board, 37, 40, TransportType.LADDER);
		assertEquals("37->77", ladder.squareContent());
	}
	
	@Test
	public void checkSquareContentForSNAKE() {
		final TransportSquare snake = new TransportSquare(board, 97, -40, TransportType.SNAKE);
		assertEquals("57<-97", snake.squareContent());
	}
	
	@Test
	public void checkDestinationForLADDER() {
		final TransportSquare ladder = new TransportSquare(board, 37, 40, TransportType.LADDER);
		assertEquals("[77]", ladder.destination().toString());
	}
	
	@Test
	public void checkDestinationForSNAKE() {
		final TransportSquare snake = new TransportSquare(board, 97, -40, TransportType.SNAKE);
		assertEquals("[57]", snake.destination().toString());
	}
	
	public void checkOccupyOrBackToStartForLADDER() {
		final TransportSquare ladder = new TransportSquare(board, 37, 40, TransportType.LADDER);
		final Square square = ladder.occupyOrBackToStart(); 
		assertFalse(square == null);
		assertEquals(ladder.position(), square.position());
	}
	
	public void checkOccupyOrBackToStartForSNAKE() {
		final TransportSquare snake = new TransportSquare(board, 37, 40, TransportType.SNAKE);
		final Square square = snake.occupyOrBackToStart(); 
		assertFalse(square == null);
		assertEquals(snake.position(), square.position());
	}
}
