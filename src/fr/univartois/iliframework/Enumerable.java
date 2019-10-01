package fr.univartois.iliframework;

import java.util.Enumeration;
import java.util.Iterator;

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
	
	public static <T> Enumerable<T> of(Enumeration<T> adapted) {
		return new Enumerable<>(adapted);
	}
}
