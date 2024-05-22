package bbl.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] array;
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity)
	{
		array =(T[]) new Object[capacity];
	}
	public ArrayList()
	{
		this(DEFAULT_CAPACITY);
	}
	private class ArrayListIterator implements Iterator<T>
	{
		private int current=0;
		@Override
		public boolean hasNext() 
		{
			return current<size;
		}

		@Override
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return array[current++];
		}
		
	}
	@Override
	public boolean add(T obj) 
	{
		if(size==array.length)
		{
			allocate();
		}
		array[size++]=obj;
		return true;
	}

	private void allocate()
	{
		array=Arrays.copyOf(array,array.length*2);	
	}
	@Override
	public boolean remove(T pattern)
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
	public boolean contains(T pattern)
	{
		return indexOf(pattern)>-1;		
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new ArrayListIterator();
	}

	@Override
	public T get(int index) 
	{
		if(index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException(); 
		}
		return array[index];
	}

	@Override
	public void add(int index, T obj) 
	{
		if(index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException(); 
		}
		if(size==array.length)
		{
			allocate();
		}
		System.arraycopy(array, index, array, index+1, size-index);
		size++;
		array[index]=obj;
	}

	@Override
	public T remove(int index) 
	{
		if(index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException(); 
		}
		T rem = array[index];	
		System.arraycopy(array, index+1, array, index , size-1-index);
		size--;
		return rem;
	}

	@Override
	public int indexOf(T pattern)
	{
		int index=0;
		while(index<size && !equals(array[index],pattern))
		{
			index++;
		}
		return index==size ? -1: index;
	}

	@Override
	public int lastindexOf(T pattern)
	{
		int index=size-1;
		while(index>=0 && !equals(array[index],pattern))
		{
			index--;
		}
		return index;
	}
	public static <T> boolean equals(T elem1, T elem2)
	{
		return elem1==null? elem1==elem2: elem1.equals(elem2);
	}
}
