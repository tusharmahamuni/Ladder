package com.snl.model;

public abstract class AbstractSquare implements ISquare {

	protected int position;
	protected Board board;
	private Player player;
	
	public AbstractSquare(Board board, int position) {
		this.position = position;
		this.board = board;
		assert isSquareValid();
	}

	protected boolean isSquareValid() {
		return board != null && board.isPositionValid(position);
	}

	@Override
	public int position() {
		return this.position;
	}

	@Override
	public ISquare moveToOccupy(int moves) {
		assert moves > 0;
		final ISquare square = board.findBoardSquare(position, moves);
		return square.occupyOrBackToStart();
	}
	
	@Override
	public ISquare occupyOrBackToStart() {
		return this.alreadyOccupied() ? this.board.firstBoardSquare() : this;
	}

	@Override
	public boolean isFirstSquare() {
		return false;
	}

	@Override
	public boolean isLastSquare() {
		return false;
	}

	@Override
	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}

	@Override
	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	@Override
	public boolean isOccupied() {
		return this.player != null;
	}
	
	public boolean alreadyOccupied() {
		return isOccupied();
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}

	public String toString() {
		return "[" + this.squareContent() + this.player() + "]";
	}
}