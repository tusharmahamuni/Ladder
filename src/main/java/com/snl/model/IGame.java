package com.snl.model;

public interface IGame {

	public void movePlayer(int roll);

	public boolean notOver();

	public boolean isOver();

	public Player winner();

	public Player currentPlayer();

	public void play();

	public Board gameBoard();

}