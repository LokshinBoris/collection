package bbl.util.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.Collection;
import bbl.util.Map;
import bbl.util.Map.Entry;
import bbl.util.Set;
import bbl.util.TreeMap;

class AbstractMapTest {

	 protected Map<Integer, Integer> map;
	 
	 Integer[] numbers = {-20, 10, 1, 100, -5};
		@BeforeEach
		void setUp() 
		{
			for(Integer num: numbers)
			{
				map.put(num,num+1);
			}
		}
		
		
		protected void runTest(Integer[] expected)
		{
			Set<Integer> set=map.keySet();
			Integer [] actual = set.stream().toArray(Integer[]::new);
			assertArrayEquals(expected, actual);
		}
		
		@Test
		void keySetTest()
		{
			runTest(numbers);
		}
		
		@Test
		void entrySetTest()
		{
			Set <Entry<Integer,Integer>> newSet=map.entrySet();
			Integer key;
			Integer value;
			Entry<Integer,Integer> entry;
			Iterator <Entry<Integer,Integer>> it=newSet.iterator();
			while(it.hasNext())
			{
				entry=it.next();
				key=entry.getKey();
				value=entry.getValue();
				assertEquals(key,value-1);
			}
		}
		
		@Test
		void valuesTest()
		{
			Collection<Integer> collection=map.values();
			Integer [] actual = collection.stream().sorted().toArray(Integer[]::new);
			Integer [] expected = new Integer[numbers.length];
			for(int i=0;i<expected.length;i++) expected[i]=numbers[i]+1;
			Arrays.sort(expected);
			assertArrayEquals(expected,actual);
		}
		
		@Test
		void getTest()
		{
			assertEquals(11,map.get(10));
			assertNull(map.get(11));
		}
		
		@Test
		void getOrDefaultTest()
		{
			assertNull(map.getOrDefault(30,null));
			assertEquals(101,map.getOrDefault(100,null));
		}

		@Test
		void putTest()
		{
			assertNull(map.put(12, 25));
			assertEquals(25,map.get(12));
		}
		
		@Test
		void putIfAbsentTest()
		{
			assertNull(map.putIfAbsent(11, 12));
			assertEquals(11,map.putIfAbsent(10, 12));
			assertEquals(6,map.keySet().size());
		}
		
		@Test 
		void removeTest()
		{
			assertNull(map.remove(2));
			assertEquals(2,map.remove(1));
			

		}

}
