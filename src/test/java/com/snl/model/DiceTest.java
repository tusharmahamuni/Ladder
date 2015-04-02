/**
 * 
 */
package com.snl.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tushar.Mahamuni
 *
 */
public class DiceTest {

	private static final int ROLL_TRIAL_COUNT = 15;
	
	private final Dice dice = new Dice();
	
	@Test
	public void checkRollValueRange() {
		for(int i=0; i < ROLL_TRIAL_COUNT; i++) {
			final int faceValue = dice.roll();
			Assert.assertTrue(1 <= faceValue && faceValue <= Dice.MAX_FACES_VALUE);
		}
	}
	
	@Test
	public void checkIfRollingCoversEachFace() {
		final Set<Integer> diceFaceValues = new HashSet<Integer>();
		for(int i=0; i < ROLL_TRIAL_COUNT; i++) {
			diceFaceValues.add(dice.roll());
			if(diceFaceValues.size() == Dice.MAX_FACES_VALUE) {
				break;
			}
		}
		
		for(Integer diceFaceVal: diceFaceValues) {
			Assert.assertTrue(1 <= diceFaceVal && diceFaceVal <= Dice.MAX_FACES_VALUE);
		}
	}
}
