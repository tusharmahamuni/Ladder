package com.snl.model;

public class Square {

	protected int position;
	protected Board board;
	private Player player;
	
	public Square(Board board, int position) {
		this.position = position;
		this.board = board;
		validate();
	}

	protected void validate() {
		if(board == null) {
			throw new IllegalArgumentException("board should not be null");
		}
	}

	public int position() {
		return this.position;
	}

	public Square getToOccupy(int moves) {
		final Square square = board.findBoardSquare(position, moves);
		return square.occupyOrBackToStart();
	}
	
	public Square occupyOrBackToStart() {
		return this.alreadyOccupied() ? this.board.firstBoardSquare() : this;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	public void enter(Player player) {
		if(player == null) {
			throw new UnsupportedOperationException("Player should not be null");
		}
		this.player = player;
	}

	public void leave(Player player) {
		if(this.player == player) {
			this.player = null;
		}else {
			throw new UnsupportedOperationException("Player " + player + " is not occupying current square");
		}
	}

	public boolean isOccupied() {
		return this.player != null;
	}
	
	public boolean alreadyOccupied() {
		return isOccupied();
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}

	protected String squareContent() {
		return Integer.toString(position);
	}
	
	public String toString() {
		return "[" + this.squareContent() + this.player() + "]";
	}
}