package bbl.util.test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bbl.util.Set;

public abstract class SetTest extends CollectionTest 
{
	Set<Integer> set;
	@Override
	void setUp()
	{
		super.setUp();
		set=(Set<Integer>)collection;
	}
	@Test
	void getTest()
	{
		assertEquals(100,(int)set.get(100));
		assertNull(set.get(10000));
	}
	@Override
	@Test
	void addEqualedTest()
	{
		assertFalse(set.add(numbers[0]));
	}
}
