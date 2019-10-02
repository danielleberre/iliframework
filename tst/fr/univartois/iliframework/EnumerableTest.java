package fr.univartois.iliframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import fr.univartois.iliframework.Enumerable;

class EnumerableTest {

	@Test
	void testEmptyEnumeration() {
		Enumeration<String> e = new Vector<String>().elements();	
		for (String s : Enumerable.of(e)) {
			fail("The enumeration is empty, got "+s);
		}
	}

	@Test
	void testNonEmptyEnumeration() {
		Vector<Integer> v = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		int cpt=0;
		for (int i : Enumerable.of(v.elements())) {
			assertEquals(++cpt,i);
		}
	}
}
