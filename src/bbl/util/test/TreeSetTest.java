package bbl.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import bbl.util.TreeSet;

public class TreeSetTest extends SortedSetTest
{
	TreeSet<Integer> treeSet;
	@BeforeEach
	@Override
	void setUp()
	{
		collection = new TreeSet<Integer>(); 
		super.setUp();
		treeSet=(TreeSet<Integer>) collection;
	}
	
	@Test
	public void displayRootChildrenTest()
	{

		setUp();

		treeSet.setSpacesPerLevel(4);
		treeSet.displayRootChildren();
	}
	
	@Test
	public void treeInversionTest()
	{
		setUp();
		
		treeSet.treeInversion();
		Integer[] expected= {100,10,1,-5,-20};
		Integer[] actual = new Integer[treeSet.size()];
		int index=0;
		for(Integer num: treeSet)
		{
			actual[index++]=num;
		}
		assertArrayEquals(expected,actual);
		assertTrue(treeSet.contains(100));
	}
	
	@Test
	public void displayTreeRotatedTest()
	{
		setUp();
		
		treeSet.setSpacesPerLevel(4);
		treeSet.displayTreeRotated();

	}
	
	@Test
	public void widthTest()
	{
		setUp();
		
		assertEquals(2,treeSet.width());
	}
	
	@Test
	public void heightTest()
	{
		setUp();
		
		assertEquals(4,treeSet.height());
	}
}
