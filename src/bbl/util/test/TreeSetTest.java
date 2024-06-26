package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.TreeSet;


public class TreeSetTest extends SortedSetTest {
	TreeSet<Integer> treeSet;
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>(); 
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
	}
	@Test
	void displayRootChildrenTest() {
		treeSet.displayRootChildren();
	}
	@Test
	void treeInversionTest() {
		treeSet.treeInversion();
		Integer[] expected = {100, 10, 1, -5,  -20};
		Integer[] actual = new Integer[treeSet.size()];
		int index = 0;
		for(Integer num: treeSet) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(treeSet.contains(100));
	}
	@Test
	void displayTreeRotatedTest() {
		treeSet.setSpacesPerLevel(4);
		treeSet.displayTreeRotated();
	}
	
	@Test
	void widthTest() {
		assertEquals(2, treeSet.width());
		
	}
	@Test
	void heightTest() {
		assertEquals(4, treeSet.height());
	}
	@Test
	void sortedSequenceTreeTest() {
		TreeSet<Integer> treeSet = new TreeSet<>();
		int[] sortedArray = new Random().ints().distinct()
				.limit(N_ELEMENTS).sorted().toArray();
		transformArray(sortedArray);
		for(int num: sortedArray) {
			treeSet.add(num);
		}
		balancedTreeTest(treeSet);
		
	}
	@Test
	void transformArrayTest()
	{
		int[] expected=new int[] {2,1,3};
		int[] sortedArray=new int[3];
		for(int i=0;i<sortedArray.length;i++ ) sortedArray[i]=i+1;
		transformArray(sortedArray);
		assertArrayEquals(expected,sortedArray);
	}
	
	@Test
	void transformArrayBigTest()
	{
		TreeSet<Integer> treeSet = new TreeSet<>();
		int[] sortedArray=new int[N_ELEMENTS];
		for(int i=0;i<sortedArray.length;i++ ) sortedArray[i]=i+1;
		transformArray(sortedArray);
		for(int num: sortedArray) {
			treeSet.add(num);
		}
		balancedTreeTest(treeSet);
	}
	
	private void balancedTreeTest(TreeSet<Integer> treeSet) {
		assertEquals(20, treeSet.height());
		assertEquals((N_ELEMENTS + 1) / 2, treeSet.width());
	}
	private void transformArray(int[] sortedArray) 
	{
		int[] newArray=new int[sortedArray.length];
		transformArray(sortedArray,0,newArray,0,sortedArray.length-1);
		System.arraycopy(newArray, 0, sortedArray, 0, newArray.length);
		
	}
	private void transformArray(int[] sortedArray, int indexInsert, int[] newArray, int left, int right)
	{
		
		if(left<=right)
		{
			int middle=(left+right)/2;
			newArray[indexInsert]=sortedArray[middle];
			transformArray(sortedArray,indexInsert*2+1,newArray,left,middle-1);
			transformArray(sortedArray,indexInsert*2+2,newArray,middle+1,right);
		}	
	}
	
	@Test
	void balanceTreeTest() {
		createBigRandomCollection(new Random());
		treeSet.balance();
		balancedTreeTest(treeSet);
		int index = 0;
		for(Integer num: treeSet) {
			index++;
		}
		assertEquals(treeSet.size(), index);
	}
}