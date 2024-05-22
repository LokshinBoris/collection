package bbl.util.test;

import org.junit.jupiter.api.BeforeEach;

import bbl.util.ArrayList;

public class ArrayListTest extends ListTest
{
  @BeforeEach
  @Override
  void setUp()
  {
	  collection = new ArrayList<Integer>();
	  super.setUp();
  }
}
