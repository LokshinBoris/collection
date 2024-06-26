package bbl.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import bbl.util.HashSet;

public class HashSetTest extends SetTest 
{
	@Override
	@BeforeEach
	void setUp()
	{
		collection=new HashSet<>(3,0.75f);
		super.setUp();
	}
	@Override
	protected void runTest(Integer[] expected)
	{
		Integer[] actual=collection.stream().sorted().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected,actual);
	}
}
