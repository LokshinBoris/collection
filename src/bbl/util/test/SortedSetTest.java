package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import bbl.util.SortedSet;

class SortedSetTest extends SetTest {

	SortedSet<Integer> set;
	@Override
	void setUp()
	{
		super.setUp();
		set=(SortedSet<Integer>)collection;
	}
		
	@Override
	protected void runTest(Integer[] expected)
	{
		Integer[] actual=collection.stream().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected,actual);
	}
	@Test
	void firstTest()
	{
		assertEquals(-20,(int)set.first());
	}
	
	@Test
	void lastTest()
	{
		assertEquals(100,(int)set.last());
	}
	
}
