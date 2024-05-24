package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.List;

	public abstract class ListTest extends CollectionTest 
	{
	   List<Integer> list;
	   @BeforeEach
	   @Override
	   void setUp() 
	   {
		   super.setUp();
		   list = (List<Integer>)collection;
	   }

	   @Test
	   void getTest()
	   {
		   assertEquals(10,list.get(1));
		   assertThrowsExactly(IndexOutOfBoundsException.class,	()->list.get(9));
	   }
	   @Test
	   void addTest()
	   {
		   Integer [] expected = {-20, -5, 10, 1, 100, -5};
		   list.add(1, -5);
		   runTest(expected);
		   assertThrowsExactly(IndexOutOfBoundsException.class,	()->list.add(7,12));
	   }
	   
	   @Test
	   void removeTest()
	   {
		   Integer [] expected1 = {-20, 10, 1, 100};
		   Integer [] expected2 = {-20, 1, 100};
		   list.remove(4);
		   runTest(expected1);
		   list.remove(list.get(1));
		   Integer a = 10;
		   list.remove(a);
		   runTest(expected2);
		   assertThrowsExactly(IndexOutOfBoundsException.class,	()->list.remove(7));
		   
	   }
	   
	   @Test
	   void indexOfTest()
	   {
		   Integer a=1;
		   Integer b=88;
		   Integer c=-20;
		   assertEquals(2,list.indexOf(a));
		   assertEquals(-1,list.indexOf(b));
		   list.add(4, c);
		   assertEquals(0,list.indexOf(c));
		   assertEquals(4,list.lastindexOf(c));
	   }
	   
	
	}

