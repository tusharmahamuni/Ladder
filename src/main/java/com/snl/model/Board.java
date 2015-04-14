/**
 * 
 */
package com.snl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.snl.model.TransportSquare.TransportType;

/**
 * The Snakes & Ladders Board class
 * @author Tushar.Mahamuni
 *
 */
public final class Board {

	private List<Square> boardSquares;
	private int squaresPerRow;
	private int totalRows;
	private int size;
	
	public Board(int squaresPerRow, int totalRows){
		this.squaresPerRow = squaresPerRow;
		this.totalRows = totalRows;
		this.size = squaresPerRow * totalRows;
		validate();
		this.addBoardSquares(size);
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
	public List<Square> getBoardSquares() {
		return boardSquares;
	}
	
	private void validate() {
		if((squaresPerRow <= 2)) {
			throw new IllegalArgumentException("Min value for squaresPerRow should be 3. Received value is=" + squaresPerRow);
		}
		
		if(totalRows <= 0) {
			throw new IllegalArgumentException("Invalid totalRows=" + totalRows);
		}
	}
	
	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	protected Square firstBoardSquare() {
		return getBoardSquare(1);
	}
	
	protected Square lastBoardSquare() {
		return getBoardSquare(size);
		
	}

	public Square findBoardSquare(int position, int steps) {
		int targetLocation = position + steps;
		if (targetLocation > size) { // reverse direction if we go past the end
			targetLocation = size - (targetLocation - size);
		}
		return this.getBoardSquare((targetLocation));
	}

	public boolean isPositionValid(int position) {
		return (position >= 1) && (position <= this.size);
	}
	
	private void addBoardSquares(int size) {
		boardSquares = new ArrayList<Square>(size);
		for(int i=0; i<size; i++) {
			Square square = new Square(this, i+1);
			boardSquares.add(square);
		}
		this.initBoardSquare(1, new FirstSquare(this, 1));
		this.initBoardSquare(size, new LastSquare(this, size));
	}
	
	private void initBoardSquare(int position, Square square) {
		boardSquares.set(position-1, square);
	}

	public void convertBoardSquareToLadder(final int position, final int transportLength) {
		this.setBoardSquare(position, new TransportSquare(this, position, transportLength, TransportType.LADDER));
	}
	
	public void convertBoardSquareToSnake(final int position, final int transportLength) {
		this.setBoardSquare(position, new TransportSquare(this, position, transportLength, TransportType.SNAKE));
	}
	
	private void setBoardSquare(final int position, final Square square) {
		if((!this.getBoardSquare(position).isLastSquare()) && (!this.getBoardSquare(position).isFirstSquare())) {
			this.initBoardSquare(position, square);
		}
	}
	
	public Square getBoardSquare(final int position) {
		if(this.isPositionValid(position)) {
			return boardSquares.get(position - 1);
		}else {
			throw new IllegalArgumentException("Invalid position value=" + position);
		}
		
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
			final List<Square> row, final boolean performReverse) {
		final List<Square> tempList = new ArrayList<Square>(row);
		if (performReverse) {
			Collections.reverse(tempList);
		}
		for (Square square : tempList) {
			boardDisplayBuilder.append(square.toString());
		}
		boardDisplayBuilder.append("\n");
	}
}
