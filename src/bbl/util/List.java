package bbl.util;

public interface List<T> extends Collection<T>
{
// return reference to an object at a given index
// throws IndexOutOfBoundException for  either index<0 or index >= size()
	static void checkIndex(int index, int maxValue, boolean exclusive) {
		if (exclusive) {
			maxValue--;
		}
		if(index < 0 || index > maxValue) {
			throw new IndexOutOfBoundsException();
		}
	}
	T get(int index);
	/**
	 * 
	 * @param index
	 * @param obj
	 * adds obj at a given index
	 * throws exception for index<0 or index > size()
	 */
	void add(int index, T obj);
	/**
	 * 
	 * @param index
	 * @return
	 * return reference to a removed object
	 * throws IndexOutOfBoundException for  either index<0 or index >= size()
	 */
	T remove(int index);
	/**
	 * 
	 * @param pattern
	 * @return index for first occurrence of an object equaled to a given pattern
	 * otherwise -1
	 */
	int indexOf(T pattern);
	/**
	 * 
	 * @param pattern
	 * @return index for last occurrence of an object equaled to a given pattern
	 * otherwise -1
	 */
	int lastindexOf(T pattern);
	@Override
	default boolean remove(T pattern)
	{
		int index=indexOf(pattern);
		boolean res=false;
		if(index>-1)
			{
			res=true;
			remove(index);
			}
		return res;
	}
	
	@Override
	default boolean contains(T pattern)
	{	
		// O(N)		
		return indexOf(pattern)!=-1;
	}
}
