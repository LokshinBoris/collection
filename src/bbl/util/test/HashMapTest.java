package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import bbl.util.HashMap;
import bbl.util.Set;

class HashMapTest extends AbstractMapTest {

	
	HashMap<Integer,Integer> hashMap;
	@Override
	void setUp()
	{
		map=new HashMap<Integer,Integer>();
		super.setUp();
	}
	
	@Override
	protected void runTest(Integer[] expected)
	{
		Set<Integer> set=map.keySet();
		Integer[] actual=set.stream().sorted().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected,actual);
	}

}
