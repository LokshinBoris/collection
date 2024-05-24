package bbl.util.test;

import org.junit.jupiter.api.BeforeEach;

import bbl.util.LinkedList;

public class LinkedListTest extends ListTest
{
	  @BeforeEach
	  @Override
	  void setUp()
	  {
		  collection = new LinkedList<Integer>();
		  super.setUp();
	  }
}


