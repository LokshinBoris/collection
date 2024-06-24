package bbl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import bbl.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();
	
	class LinkedHashSetIterator implements Iterator<T> {
		
		Iterator<T> it=list.iterator();
		T keyNow;
		@Override
		public boolean hasNext()
		{
			return it.hasNext();
		}

		@Override
		public T next()
		{
			keyNow=it.next();
			return keyNow;
		}
		@Override
		public void remove()
		{
			it.remove();
			map.remove(keyNow);
			size--;
		}
		
	}
	@Override
	public Iterator<T> iterator()
	{
		return new LinkedHashSetIterator();
	}

	
	@Override
	public T get(T pattern) {
		Node<T> node = map.get(pattern);		
		return node == null ? null : node.data;
	}

	@Override
	public boolean add(T obj)
	{
		boolean res=false;
		if(!contains(obj))
		{
			list.add(obj);
			Node<T> node=list.tail;
			map.put(obj, node);
			res=true;
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = map.get(pattern);
		boolean res = false;
		if (node != null) {
			res = true;
			list.removeNode(node);
			map.remove(pattern);
			size--;
		}
		
		return res;
	}

	@Override
	public boolean contains(T pattern) 
	{
		Node<T> node=map.get(pattern);
		return node==null? false:true;
	}


}
