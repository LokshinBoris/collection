package bbl.util;

public interface SortedSet<T> extends Set<T>
{
	T first();
	T last();
	T ceiling(T Key);
	T floor(T Key);
}
