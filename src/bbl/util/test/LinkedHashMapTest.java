package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.LinkedHashMap;

class LinkedHashMapTest extends AbstractMapTest
{
	@Override
	@BeforeEach
	void setUp()
	{
		map = new LinkedHashMap<Integer,Integer>();
		super.setUp();
	}
}
