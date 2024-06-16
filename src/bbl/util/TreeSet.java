package bbl.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements SortedSet<T>
{
	private static class Node<T>
	{
		T data;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		Node(T data)
		{
			this.data=data;
		}
	}
	
	private class TreeSetIterator implements Iterator<T>
	{
		Node<T> current=getLeastFrom(root);
		boolean flNext=false;
		Node<T> prev=null;
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
			 T res=current.data;
			 prev=current;
			 flNext=true;
			 current=getCurrent(current);
			 return res;
		}
		
		@Override
		public void remove()
		{
			if(!flNext)
			{
				throw new IllegalStateException();
			}
			TreeSet.this.removeNode(prev);
			flNext=false;
		}
		
	}
	private static final int DEFAULT_SPACES_PER_LEVEL =1;
	Node<T> root;
	private Comparator<T> comp;
	private int spacesPerLevel=DEFAULT_SPACES_PER_LEVEL;
	
	public void setSpacesPerLevel(int spacesPerLevel)
	{
		this.spacesPerLevel = spacesPerLevel;
	}
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public T get(T pattern)
	{
		Node<T> node = getNode(pattern);
		return node == null ? null : node.data;		
	}

	private Node<T> getNode(T pattern) {
		Node<T> res = null;
		Node<T> node = getParentOrNode(pattern);
		if (node != null && comp.compare(node.data, pattern) == 0) {
			res = node;
		}
		return res;
	}
	
	private Node<T> getParentOrNode(T pattern) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while(current != null && (compRes = comp.compare(pattern, current.data)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}
	public Node<T> getCurrent(Node<T> current) {
		return current.right != null ? getLeastFrom(current.right) : getFirstGreaterParent(current);
	}

	private Node<T> getFirstGreaterParent(Node<T> current)
	{
		Node<T> parent=current.parent;
	
		while(parent!=null && parent.right==current)
		{
			current=parent;
			parent=current.parent;			
		}
		return parent;
	}
	
	private Node<T> getFirstLeastParent(Node<T> current)
	{
		Node<T> parent=current.parent;
	
		while(parent!=null && parent.left==current)
		{
			current=parent;
			parent=current.parent;			
		}
		return parent;
	}
	
	public Node<T> getLeastFrom(Node<T> node)
	{
		
		if (node != null) {
			
		while(node.left != null) {
				node = node.left;
			}
		}
		return node;
	}

	@Override
	public boolean add(T obj)
	{
		boolean ret=false; 
		if(obj==null)
		{
			throw new NullPointerException();
		}
		if(!contains(obj))
		{
			ret=true;
			Node<T> node = new Node<T>(obj);
			if(root==null)
			{
				addRoot(node);
			}
			else
			{
				addWithParent(node);
			}
			size++;			
		}
		return ret;
	}
	
	private void addWithParent(Node<T> node)
	{
		Node<T> parent=getParent(node);
		if(comp.compare(node.data,parent.data)>0)
		{
			parent.right=node;
		}
		else
		{
			parent.left=node;
		}
		node.parent = parent;
	}

	private Node<T> getParent(Node<T> node) {
		Node<T> parent = getParentOrNode(node.data);
		return parent;
	}

	private void addRoot(Node<T> node)
	{
		root=node;		
	}

	@Override
	public boolean remove(T pattern)
	{
		boolean ret=false;
		Node<T> node=getNode(pattern);
		if(node!=null)
		{
			removeNode(node);
			ret=true;			
		}
		return ret;
	}

	private void removeNode(Node<T> node)
	{
		
		if(node.left==null && node.right==null)
		{ // without child
			removeLeaf(node);
		}
		else if (node.left==null || node.right==null)
		{ // only right or only left child
			removeInChain(node);
		}
		else
		{ // both child's
			removeFork(node);
		}
		size--;
	}
	
	private void removeFork(Node<T> node)
	{
		Node<T> newNode=getGreatesFrom(node.left);
		node.data=newNode.data;
		if(newNode.left==null) removeLeaf(newNode);
		else removeInChain(newNode);
	}
	
	private void removeInChain(Node<T> node)
	{
		Node<T> newNode;
		if(node.left==null) newNode=node.right;
		else newNode=node.left;
		newNode.parent=node.parent;
		if(node==root) 	
		{	
			if(node.left==null)	root=node.right;
			else root=node.left;
		}
		else
		{
			if(node.parent.left==node) node.parent.left=newNode;
			else node.parent.right=newNode;
		}
		eraseNode(node);		
	}	
	
	private void removeLeaf(Node<T> node)
	{
		if(node==root) 	root=null;
		else
		{
			if(node.parent.left==node) node.parent.left=null;
			else node.parent.right=null;
		}
		eraseNode(node);
	}
	
	private void eraseNode(Node<T> node) 
	{
		node.parent=null;
		node.left=null;
		node.right=null;
		node.data=null;	
	}
	
	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		return root == null ? null : getLeastFrom(root).data;
	}

	@Override
	public T last() {
		return root == null ? null : getGreatesFrom(root).data;
	}
	private Node<T> getGreatesFrom(Node<T> node)
	{
		if (node != null)
		{
				while(node.right != null) node = node.right;
		}
		return node;
	}
	@Override
	/**
	 * Returns the least element in this set greater than 
	 * or equal to the given element,
	 *  or null if there is no such element
	 */
	public T ceiling(T Key) 
	{
		Node<T> res = null;
		Node<T> node = getParentOrNode(Key);
		int resComp=0;  
		if (node != null)
		{	
			resComp=comp.compare(Key,node.data);
			if(resComp > 0 )
			{	
				if(node.parent==null || node.parent.right==node) res=null;
				else res = getFirstGreaterParent(node);
			}
			else res=node;
		}
		return res==null ? null:res.data;			
	}
	@Override
	/**
	 * Returns the greatest element in this set less than 
	 * or equal to the given element,
	 *  or null if there is no such element
	 */
	public T floor(T Key)
	{
		Node<T> res = null;
		Node<T> node = getParentOrNode(Key);
		int resComp=0;  
		if (node != null)
		{	
			resComp=comp.compare(Key,node.data);
			if(resComp < 0 )
			{	
				if(node.parent==null || node.parent.left==node) res=null;
				else res = getFirstLeastParent(node);
			}
			else res=node;
		}
		return res==null ? null:res.data;	
	}
	
	/**
	 * display tree in the following form:
	 *  -20
	 *     10
	 *        1
	 *           -5
	 *        100
	 */
	public void displayRootChildren()
	{
		displayRootChildren(root,1);
	}
	
	private void displayRootChildren(Node<T> node,int level)
	{
		if(node!=null)
		{
			displayRoot(node, level);
			displayRootChildren(node.left, level+1);
			displayRootChildren(node.right, level+1);
		}
	}
	
	public void treeInversion()
	{
		treeInversion(root);
		comp=comp.reversed();
	}
	
	private void treeInversion(Node<T> node)
	{
		if(node!=null)
		{
			treeInversion(node.left);
			treeInversion(node.right);
			swapNode(node);
		}
	}
	
	private void swapNode(Node<T> node)
	{
		Node<T> tmp=node.left;
		node.left=node.right;
		node.right=tmp;
	}
	
	public void displayTreeRotated()
	{
		displayTreeRotated(root,1);
	}
	
	private void displayTreeRotated(Node<T> root, int level)
	{
		if(root!=null)
		{
			displayTreeRotated(root.right,level+1);
			displayRoot(root,level);
			displayTreeRotated(root.left,level+1);
		}
	}
	
	private void displayRoot(Node<T> root, int level)
	{
		System.out.printf("%s", " ".repeat(level*spacesPerLevel));
		System.out.println(root.data);		
	}
	
	public int width()
	{		
		return width(root);
	}
	
	private int width(Node<T> root)
	{
		int res=0;
		if(root!=null)
		{
			if(root.left==null && root.right==null) res=1;
			else
			{
				res=width(root.left)+width(root.right);
			}
		}
		return res;
	}
	public int height()
	{
		return height(root);
	}
	
	private int height(Node<T> root)
	{
		int res=0;
		if(root!=null)
		{
			int heightLeft=height(root.left);
			int heightRight=height(root.right);
			res=Math.max(heightLeft, heightRight)+1;
		}
		return res;
	}
}
