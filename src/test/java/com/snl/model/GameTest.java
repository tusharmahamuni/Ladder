/**
 * 
 */
package com.snl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.snl.application.SnakesAndLaddersMain;

/**
 * Test class for {@link Game}
 * @author Tushar.Mahamuni
 *
 */
public class GameTest {

	private static final int SQUARES_PER_ROW = 10;
	private static final int TOTAL_ROWS = 12;
	private final Player tom = new Player("TOM");
	private final Player jerry = new Player("JERRY");
	Player[] participants = {tom, jerry};
	private IGame newGame;
	
	@Before
	public void init() throws Exception {
		newGame = new SnakesAndLaddersMain(SQUARES_PER_ROW, TOTAL_ROWS, participants).getGame();
	}
	
	@After
	public void clear() throws Exception {
		newGame = null;
	}
	
	
	@Test
	public void beforeGameStarts() {
		final Board board = newGame.gameBoard();
		assertTrue(newGame.notOver());
		assertTrue(board.firstBoardSquare().isOccupied());
		assertEquals(2, ((FirstSquare)board.firstBoardSquare()).getPlayers().size());
		assertEquals(1, tom.currentPosition());
		assertEquals(1, jerry.currentPosition());
		assertEquals(tom, newGame.currentPlayer());
		assertEquals("[START<TOM><JERRY>]", board.firstBoardSquare().toString());
		
		
		
		//ladders
		assertEquals("[4->34]", board.getBoardSquare(4).toString());
		assertEquals("[9->51]", board.getBoardSquare(9).toString());
		assertEquals("[20->58]", board.getBoardSquare(20).toString());
		assertEquals("[28->104]", board.getBoardSquare(28).toString());
		assertEquals("[40->79]", board.getBoardSquare(40).toString());
		assertEquals("[51->87]", board.getBoardSquare(51).toString());
		assertEquals("[71->111]", board.getBoardSquare(71).toString());
		
		//snakes
		assertEquals("[7<-37]", board.getBoardSquare(37).toString());
		assertEquals("[34<-74]", board.getBoardSquare(74).toString());
		assertEquals("[19<-82]", board.getBoardSquare(82).toString());
		assertEquals("[24<-107]", board.getBoardSquare(107).toString());
		assertEquals("[73<-113]", board.getBoardSquare(113).toString());
		assertEquals("[75<-115]", board.getBoardSquare(115).toString());
		assertEquals("[78<-119]", board.getBoardSquare(119).toString());
		
		
	}
	
	@Test
	public void tomMovesby4() {
		newGame.movePlayer(4);
		assertTrue(newGame.notOver());
		assertEquals(5, tom.currentPosition());
		assertEquals(1, jerry.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
	}
	
	@Test
	public void tomGetsBonusRoll() {
		newGame.movePlayer(6);
		assertTrue(newGame.notOver());
		assertEquals(7, tom.currentPosition());
		assertEquals("[7<TOM>]", newGame.gameBoard().getBoardSquare(7).toString());
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		assertEquals(tom, newGame.currentPlayer());// current player not changed 
		
		
		//bonus roll gives Tom 3
		newGame.movePlayer(3);
		assertTrue(newGame.notOver());
		assertEquals(10, tom.currentPosition());
		assertEquals("[10<TOM>]", newGame.gameBoard().getBoardSquare(10).toString());
		assertEquals(1, jerry.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
	}
	
	@Test
	public void jerryLandsOnAlreadyOccupiedSquareByTom() {
		newGame.movePlayer(4);//TOM moves to 5
		assertTrue(newGame.notOver());
		assertEquals("[5<TOM>]", newGame.gameBoard().getBoardSquare(5).toString());
		assertTrue(newGame.gameBoard().getBoardSquare(5).isOccupied());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		newGame.movePlayer(4);//JERRY moves to 5 where TOM is already present so JERRY has to move to first square
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		assertEquals(tom, newGame.currentPlayer());
	}

	@Test
	public void tomGetsLadder() {
		newGame.movePlayer(3);
		assertEquals(1, jerry.currentPosition());
		assertTrue(newGame.notOver());
		assertEquals("[34<TOM>]", newGame.gameBoard().getBoardSquare(34).toString());
		assertFalse(newGame.gameBoard().getBoardSquare(4).isOccupied());
		assertEquals(34, tom.currentPosition());
		assertTrue(newGame.gameBoard().getBoardSquare(34).isOccupied());
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		assertEquals(jerry, newGame.currentPlayer());
	}
	
	@Test
	public void jerryLandsViaLadderToSquareOccupiedByTom() {
		newGame.movePlayer(3);
		assertEquals(1, jerry.currentPosition());
		assertTrue(newGame.notOver());
		assertFalse(newGame.gameBoard().getBoardSquare(4).isOccupied());
		assertEquals("[34<TOM>]", newGame.gameBoard().getBoardSquare(34).toString());
		assertEquals(jerry, newGame.currentPlayer());
		newGame.movePlayer(3);
		assertTrue(newGame.notOver());
		assertFalse(newGame.gameBoard().getBoardSquare(4).isOccupied());
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		assertEquals(tom, newGame.currentPlayer());
	}
	
	@Test
	public void tomMeetsSnake() {
		newGame.movePlayer(6+6+6+6+6+5+1);//TOM moves to 37 where SNAKE is present so TOM goes down
		assertTrue(newGame.notOver());
		assertFalse(newGame.gameBoard().getBoardSquare(37).isOccupied());
		assertEquals(7, tom.currentPosition());
		assertEquals("[7<TOM>]", newGame.gameBoard().getBoardSquare(7).toString());
		assertEquals(jerry, newGame.currentPlayer());
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
	}
	
	@Test
	public void jerryDescendsViaSnakeToSquareOccupiedByTom() {
		newGame.movePlayer(5);
		assertTrue(newGame.notOver());
		assertEquals(6, tom.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
		newGame.movePlayer(2);
		assertEquals(3, jerry.currentPosition());
		assertEquals(tom, newGame.currentPlayer());
		newGame.movePlayer(1);
		assertEquals(7, tom.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
		newGame.movePlayer(6+6+6+6+6+4);//JERRY moves to 37 where SNAKE is present so he goes to 7. Tom is already present at 7 so JERRY has to go back to first square
		assertFalse(newGame.gameBoard().getBoardSquare(37).isOccupied());
		assertEquals(1, jerry.currentPosition());
		assertEquals("[START<JERRY>]", newGame.gameBoard().getBoardSquare(1).toString());
		assertTrue(newGame.notOver());
		assertEquals(tom, newGame.currentPlayer());
	}
	
	@Test
	public void jerryWinsTheGame() {
		newGame.movePlayer(20+20+20+20+3);
		assertTrue(newGame.notOver());
		assertEquals(84, tom.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
		newGame.movePlayer(20+20+20+20+17);
		assertTrue(newGame.notOver());
		assertEquals(98, jerry.currentPosition());
		assertEquals(tom, newGame.currentPlayer());
		newGame.movePlayer(4);
		assertTrue(newGame.notOver());
		assertEquals(88, tom.currentPosition());
		assertEquals(jerry, newGame.currentPlayer());
		newGame.movePlayer(6+6+6+4);
		assertTrue(newGame.isOver());
		assertEquals(120, jerry.currentPosition());
		assertTrue(jerry.wins());
		assertEquals("[FINISH<JERRY>]", newGame.gameBoard().getBoardSquare(120).toString());
		assertEquals(jerry, newGame.winner());
		assertTrue(newGame.gameBoard().getBoardSquare(jerry.currentPosition()).isLastSquare());
		assertEquals(tom, newGame.currentPlayer());
	}
	
	@Test
	public void tomMovesBackFromLastBoardSquare() {
		newGame.movePlayer(20+20+20+20+20+17+5);
		assertTrue(newGame.notOver());
		assertFalse(newGame.gameBoard().lastBoardSquare().isOccupied());
		assertEquals(117, tom.currentPosition());
		assertEquals("[117<TOM>]", newGame.gameBoard().getBoardSquare(117).toString());
		assertFalse(tom.wins());
		assertFalse(tom.equals(newGame.winner()));
		assertEquals(jerry, newGame.currentPlayer());
	}
}
