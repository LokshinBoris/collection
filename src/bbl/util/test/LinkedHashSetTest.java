package bbl.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.util.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
@Override
@BeforeEach
void setUp()
{
	collection = new LinkedHashSet<Integer>(); //for testing Hash Table re-creation
	super.setUp();
}


}
