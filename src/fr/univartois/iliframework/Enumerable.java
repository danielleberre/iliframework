package fr.univartois.iliframework;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Utility class to allow the use of for each loops
 * with Enumeration objets.
 * 
 * It is a simple Object Adapter for the Iterable interface
 * using type inference to simplify it's use by the end user.
 * 
 * @author leberre
 *
 * @param <T> the type of the elements to iterate on
 */
public class Enumerable<T> implements Iterable<T> {

	private Enumeration<T> adapted;
	
	private Enumerable(Enumeration<T> adapted) {
		this.adapted = adapted;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return adapted.hasMoreElements();
			}

			@Override
			public T next() {
				return adapted.nextElement();
			}
		};
	}
	
	/**
	 * Factory method used to derive the type of the Enumerable from the Enumeration object.
	 * 
	 * @param <T> the type of the elements of adapted
	 * @param adapted the Enumeration to iterate on in the for loop
	 * @return an Enumerable object of the required type
	 */
	public static <T> Enumerable<T> of(Enumeration<T> adapted) {
		return new Enumerable<>(adapted);
	}
}
