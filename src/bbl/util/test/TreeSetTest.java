package bbl.util.test;

import org.junit.jupiter.api.BeforeEach;

import bbl.util.TreeSet;

public class TreeSetTest extends SortedSetTest
{
	@Override
	@BeforeEach
	void setUp()
	{
		collection = new TreeSet<Integer>(); 
		super.setUp();
	}
}
