package bbl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<T> implements List<T>
{
	private static class Node<T>
	{
		T data;
		Node <T> prev;
		Node <T> next;
		Node (T data)
		{
			this.data=data;
		}
	}
	Node <T> head;
	Node <T> tail;
	int size;
	@Override
	public boolean add(T obj) 
	{
		// O(N)
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;	
	}

	@Override
	public boolean remove(T pattern) 
	{
		// O(N)
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
		// O(N)		
		return indexOf(pattern)!=-1;
	}

	@Override
	public int size() 
	{	
		// O(1)
		return size;
	}

	@Override
	public Iterator<T> iterator() 
	{	
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>
	{
		private Node<T> current=head;
		@Override
		public boolean hasNext() 
		{
			return current!=null;
		}

		@Override
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			Node <T> ret=current;
			current=current.next;																																			
			return ret.data;
		}
		
	}

	@Override
	public T get(int index) 
	{
		// O(N)
		List.checkIndex(index, size,true);
		return getNode(index).data;
	}

	@Override
	public void add(int index, T obj)
	{
		// O(N)
		List.checkIndex(index, size, false);
		Node<T> node = new Node<>(obj);
		addNode(index,node);
	}

	@Override
	public T remove(int index)
	{
		// O(N)
		List.checkIndex(index, size, true);
		T removed;
		if(index==0) removed=removeHead();
		else if (index==size-1) removed=removeTail();
		else removed=removeMiddle(index);
		size--;
		return removed;
	}

	private T removeMiddle(int index)
	{
		Node <T> nodeRemoved=getNode(index);
		T removed=nodeRemoved.data;
		Node <T> nodePrev=nodeRemoved.prev;
		Node <T> nodeNext=nodeRemoved.next;
		nodeRemoved.data=null;
		nodeRemoved.prev=null;
		nodeRemoved.next=null;
		nodePrev.next=nodeNext;
		nodeNext.prev=nodePrev;
		return removed;
	}

	private T removeTail() 
	{
		T removed=tail.data;
		Node <T> nodePrev=tail.prev;
		tail.data=null;
		tail.prev=null;
		if(tail!=head)
		{
			tail=nodePrev;
			tail.next=null;
		}
		else
		{
			head=null;
			tail=null;
		}
		return removed;
	}

	private T removeHead() 
	{
		T removed=head.data;
		Node <T> nodeNext=head.next;
		head.data=null;
		head.next=null;
		if(head!=tail)
		{
			head=nodeNext;
			head.prev=null;
		}
		else
		{
			head=null;
			tail=null;
		}
		return removed;
	}

	@Override
	public int indexOf(T pattern)
	{
		// O(N)
		int index=0;
		Node<T> cur=head;
		while(cur!=null && !equals(cur.data,pattern))
		{
			index++;
			cur=cur.next;
		}
		return index==size ? -1: index;
	}

	@Override
	public int lastindexOf(T pattern)
	{
		// O(N)
		int index=size-1;
		Node<T> cur=tail;
		while(cur!=null && !equals(cur.data,pattern))
		{
			index--;
			cur=cur.prev;
		}
		return index;
	}

	private Node<T> getNode(int index)
	{
		return index<size/2 ? getNodeFromHead(index): getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index)
	{
		Node <T> current=tail;
		for(int i=size-1;i>index;i--)
		{
			current=current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index)
	{
		Node <T> current=head;
		for(int i=0;i<index;i++)
		{
			current=current.next;
		}
		return current;
	}
	
	private void addNode(int index, Node<T> node)
	{
		if(index==0) addHead(node);
		else if (index==size) addTail(node);
		else addMiddle(node,index);
		size++;
	}

	private void addMiddle(Node<T> node,int index)
	{
		 Node<T> nodeNext=getNode(index);
		 Node<T> nodePrev=nodeNext.prev;
		 nodeNext.prev=node;
		 nodePrev.next=node;
		 node.prev=nodePrev;
		 node.next=nodeNext;
	}

	private void addTail(Node<T> node)
	{
		tail.next=node;
		node.prev=tail;
		tail=node;		
	}

	private void addHead(Node<T> node)
	{
		if(head==null)
		{
			head=tail=node;
		}
		else
		{
			node.next=head;
			head.prev=node;
			head=node;
		}
		
	}
	public static <T> boolean equals(T elem1, T elem2)
	{
		return elem1==null? elem1==elem2: elem1.equals(elem2);
	}

}
