package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.Collection;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	Integer[] numbers = {-20, 10, 1, 100, -5};
	
	@BeforeEach
	void setUp() {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}
	@Test
	void iteratorTest() {
		runTest(numbers);
	}
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.stream().toArray(Integer[]::new);
		assertArrayEquals(expected, actual);
	};
	
	@Test
	void addColTest()
	{
		Integer a=8;
		Integer [] expected = {-20, 10, 1, 100, -5, 8};
		   collection.add(a);
		   Integer[] actual=collection.stream().toArray(Integer[]::new);
		   assertArrayEquals(expected, actual);	
	}
	@Test
	void removeColTest()
	{
		Integer [] expected = {-20, 10, 100, -5};
		Integer a=1;
		collection.remove(a);
        Integer[] actual=collection.stream().toArray(Integer[]::new);
	    assertArrayEquals(expected, actual);	
	}
	@Test
	void containsTest()
	{
		Integer a=10;
		Integer b=8;
		assertTrue(collection.contains(a));
		assertFalse(collection.contains(b));
	}
	@Test
	void sizeTest()
	{
		assertEquals(5,collection.size());
		Integer a=10;		
		collection.remove(a);
		assertEquals(4,collection.size());
		Integer b=10;		
		collection.add(b);
		collection.add(b);
		assertEquals(6,collection.size());
	}
}