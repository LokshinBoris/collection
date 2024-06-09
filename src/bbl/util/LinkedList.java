package bbl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<T> extends AbstractCollection<T> implements List<T>
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

	@Override
	public boolean add(T obj) 
	{
		// O(1)
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;	
	}


	@Override
	public Iterator<T> iterator() 
	{	
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>
	{
		private Node<T> current=head;
		boolean flNext=false;
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
			flNext=true;
			return ret.data;
		}
		
		@Override
		public void remove()
		{
			if(!flNext)
			{
				throw new IllegalStateException();
			}
			if(current==null) LinkedList.this.remove(tail);
			else LinkedList.this.remove(current.prev);
			flNext=false;
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
	
	public void remove(Node<T> node)
	{
		if(node==head) removeHead();
		else if (node==tail) removeTail();
		else removeMiddle(node);
		size--;
	}

	private T removeMiddle(int index)
	{
		Node <T> nodeRemoved=getNode(index);
		T removed=nodeRemoved.data;
		removeMiddle(nodeRemoved);
		return removed;
	}

	private void removeMiddle(Node<T> nodeRemoved)
	{
		Node <T> nodePrev=nodeRemoved.prev;
		Node <T> nodeNext=nodeRemoved.next;
		eraseNode(nodeRemoved);
		nodePrev.next=nodeNext;
		nodeNext.prev=nodePrev;	
	}

	private T removeTail() 
	{
		T removed=tail.data;
		Node <T> nodePrev=tail.prev;
		Node <T> forRem=tail;

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
		eraseNode(forRem);
		return removed;
	}

	private T removeHead() 
	{
		T removed=head.data;
		Node <T> nodeNext=head.next;
		Node <T> forRem=head;
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
		eraseNode(forRem);
		return removed;
	}
	private void eraseNode(Node<T> obj)
	{
		obj.data=null;
		obj.prev=null;
		obj.next=null;
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
