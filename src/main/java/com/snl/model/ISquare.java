/**
 * 
 */
package com.snl.model;

/**
 * @author Tushar.Mahamuni
 *
 */
public interface ISquare {
	public int position();
	public ISquare moveToOccupy(int moves);
	public boolean isFirstSquare();
	public boolean isLastSquare();
	public void enter(Player player);
	public void leave(Player player);
	public boolean isOccupied();
	public ISquare occupyOrBackToStart();
	public String squareContent();
}
