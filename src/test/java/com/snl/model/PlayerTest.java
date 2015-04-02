/**
 * 
 */
package com.snl.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Tushar.Mahamuni
 *
 */
public class PlayerTest {

	@Test
	public void checkPlayerString() {
		final Player tom = new Player("TOM");
		assertEquals("TOM",tom.toString());
	}
}
