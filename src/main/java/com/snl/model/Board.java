/**
 * 
 */
package com.snl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Snakes & Ladders Board class
 * @author Tushar.Mahamuni
 *
 */
public final class Board {

	private List<ISquare> boardSquares;
	private int squaresPerRow;
	private int totalRows;
	private int size;
	
	public Board(int squaresPerRow, int totalRows) {
		assert (squaresPerRow > 0) && (totalRows > 0);
		this.squaresPerRow = squaresPerRow;
		this.totalRows = totalRows;
		this.size = squaresPerRow * totalRows;
		this.addBoardSquares(size);
		assert isBoardValid();
	}

	/**
	 * @return the totalRows
	 */
	public int totalRows() {
		return totalRows;
	}

	/**
	 * @return the squaresPerRow
	 */
	public int squaresPerRow() {
		return squaresPerRow;
	}

	/**
	 * @return the squares
	 */
	public List<ISquare> getBoardSquares() {
		return boardSquares;
	}
	
	private boolean isBoardValid() {
		return ((boardSquares.size() > 3) && (size == boardSquares.size()));
	}
	
	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	protected ISquare firstBoardSquare() {
		return getBoardSquare(1);
	}
	
	protected ISquare lastBoardSquare() {
		return getBoardSquare(size);
		
	}

	public ISquare findBoardSquare(int position, int steps) {
		int targetLocation = position + steps;
		if (targetLocation > size) { // reverse direction if we go past the end
			targetLocation = size - (targetLocation - size);
		}
		return this.getBoardSquare((targetLocation));
	}

	public boolean isPositionValid(int position) {
		return (position >= 1) && (position <= Dice.MAX_FACES_VALUE);
	}
	
	private void addBoardSquares(int size) {
		boardSquares = new ArrayList<ISquare>(size);
		for(int i=0; i<size; i++) {
			RegularSquare square = new RegularSquare(this, i+1);
			boardSquares.add(square);
		}
		this.initBoardSquare(1, new FirstSquare(this, 1));
		this.initBoardSquare(size, new LastSquare(this, size));
	}
	
	private void initBoardSquare(int position, ISquare square) {
		assert this.isPositionValid(position) && square != null;
		boardSquares.set(position-1, square);
	}

	public void convertBoardSquareToLadder(final int position, final int transportLength) {
		this.setBoardSquare(position, new Ladder(this, position, transportLength));
	}
	
	public void convertBoardSquareToSnake(final int position, final int transportLength) {
		this.setBoardSquare(position, new Snake(this, position, transportLength));
	}
	
	public void setBoardSquare(final int position, final ISquare square) {
		assert !this.getBoardSquare(position).isLastSquare()
			&& !this.getBoardSquare(position).isFirstSquare();
		this.initBoardSquare(position, square);
		assert isBoardValid();
	}
	
	public ISquare getBoardSquare(final int position) {
		assert this.isPositionValid(position);
		return boardSquares.get(position - 1);
	}

	public String toString() {
		StringBuilder boardDisplayBuilder = new StringBuilder();
		boolean performReverse = false;
		int i = this.squaresPerRow;
		int k = 0;
		while(i <= this.size) {
			if(k % 2 == 0) {
				performReverse = true;
			}else {
				performReverse = false;
			}
			
			addToDisplay(boardDisplayBuilder, boardSquares.subList(boardSquares.size()-i, boardSquares.size() - (i - this.squaresPerRow)), performReverse);
			i = i + this.squaresPerRow;
			k++;
		}
		return boardDisplayBuilder.toString();
	}
	
	private void addToDisplay(final StringBuilder boardDisplayBuilder,
			final List<ISquare> row, final boolean performReverse) {
		final List<ISquare> tempList = new ArrayList<ISquare>(row);
		if (performReverse) {
			Collections.reverse(tempList);
		}
		for (ISquare square : tempList) {
			boardDisplayBuilder.append(square.toString());
		}
		boardDisplayBuilder.append("\n");
	}
}
