/**
 * 
 */
package com.snl.model;


/**
 * @author Tushar.Mahamuni
 *
 */
public class Dice {

	static final int MIN_FACE_VALUE = 1;
	static final int MAX_FACES_VALUE = 6;

	public int roll() {
		final int faceValue = (((int) (Math.random() * 10) % MAX_FACES_VALUE) + MIN_FACE_VALUE); 
		assert MIN_FACE_VALUE <= faceValue && faceValue <= MAX_FACES_VALUE;
		return faceValue;
	}
}
