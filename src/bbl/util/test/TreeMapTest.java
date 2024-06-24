package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.Set;
import bbl.util.TreeMap;

class TreeMapTest extends AbstractMapTest {

	TreeMap<Integer,Integer> treeMap;
	@Override
	@BeforeEach
	void setUp()
	{
		map=new TreeMap<Integer,Integer>();
		super.setUp();
		treeMap=(TreeMap<Integer,Integer>)map;
	}
	
	@Override
	protected void runTest(Integer[] expected)
	{
		Set<Integer> set=map.keySet();
		Integer [] actual = set.stream().sorted().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	void firstKeyTest()
	{	
		assertEquals(-20,((TreeMap)map).firstKey());
	}

	@Test
	void lastKeyTest()
	{	
		assertEquals(100,((TreeMap)map).lastKey());
	}
	

	@Test
	void floorTest()
	{
		assertEquals(10,((TreeMap)map).floorKey(10));
		assertEquals(10,((TreeMap)map).floorKey(11));
		assertNull(((TreeMap)map).floorKey(-25));
		assertEquals(100,((TreeMap)map).floorKey(150));
	}

	@Test
	void ceilingTest()
	{
		assertEquals(10,((TreeMap)map).ceilingKey(10));
		assertEquals(100,((TreeMap)map).ceilingKey(11));
		assertNull(((TreeMap)map).ceilingKey(150));
		assertEquals(-20,((TreeMap)map).ceilingKey(-25));
	}

}
