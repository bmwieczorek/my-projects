package com.bawi;

import junit.framework.Assert;

import org.junit.Test;

public class ExampleTest {

	private Example example = new Example();

	@Test
	public void testAdd() {
		Assert.assertEquals(4, example.add(2, 2));
	}

}
