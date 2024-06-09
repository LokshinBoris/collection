package bbl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class HashSet<T> extends AbstractCollection<T> implements Set<T> 
	{
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T> [] hashTable;
	float factor;
	 
	private class HashSetIterator  implements Iterator <T>
	{
		int current=0;
		int currentTableNum=0;
		Iterator<T> it=null; 
		boolean flNext=false;
		T prev=null;
		@Override
		public boolean hasNext()
		{
			return current<size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			if(hashTable[currentTableNum]==null) findNotNull();
			T ret=null;
			while(ret==null)
			{
				if(it.hasNext())
				{
					ret=it.next();
					current++;
				}
				else
				{
					currentTableNum++;
					findNotNull();
				}
			}
			flNext=true;
			prev=ret;
			return ret;
		}

		private void findNotNull()
		{
			while(hashTable[currentTableNum]==null) currentTableNum++;				
			it=hashTable[currentTableNum].iterator();
		}
		
		@Override
		public void remove()
		{
			if(!flNext)
			{
				throw new IllegalStateException();
			}
			HashSet.this.remove(prev);
			current--;
			flNext=false;
		}
		

		
	}
	@SuppressWarnings("unchecked")
	public HashSet(int hashTableLength, float factor) 
	{
		hashTable = new List[hashTableLength];
		this.factor=factor;
	}
	
	public HashSet()
	{
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}
	@Override
	public Iterator<T> iterator()
	{	
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj)
	{
		boolean res=false;
		if(!contains(obj))
		{
			if((float)size/hashTable.length>=factor)
			{
				hashTableReallocation();
			}
			addObjInHashTable(obj,hashTable);
			size++;
			res=true;
		}
		return res;
	}

	private void hashTableReallocation() 
	{
		@SuppressWarnings("unchecked")
		List<T> [] tmp=new List[hashTable.length*2];
		for(List<T> list: hashTable)
		{
			if(list!=null)
			{
				for(T obj:list)
				{
					addObjInHashTable(obj,tmp);
				}
			}
		}
		hashTable=tmp;
	}

	private void addObjInHashTable(T obj, List<T> [] table) 
	{
		int index=getIndex(obj,table.length);
		List<T> list=table[index];
		if (list==null)
		{
			list=new LinkedList<>();
			table[index]=list;
		}
		list.add(obj);
	}

	private int getIndex(T obj, int modul) 
	{
		int hashCode=obj.hashCode();
		int index=Math.abs(hashCode%modul);
		return index;
	}

	@Override
	public boolean remove(T pattern)
	{
		boolean ret=false;
		int index=getIndex(pattern,hashTable.length);
		List <T> list = hashTable[index];
		if(list!=null)
		{
			ret=list.remove(pattern);
			if(list.size()==0) hashTable[index]=null;
		}
		size--;
		return ret;
	}

	@Override
	public boolean contains(T pattern) {
		int index=getIndex(pattern,hashTable.length);
		List<T> list=hashTable[index];
		return list!=null && list.contains(pattern);
	}

	@Override
	public T get(T pattern)
	{
		T ret=null;
		int index=getIndex(pattern,hashTable.length);
		List<T> list=hashTable[index];
		if(list!=null) 
		{
			int num=list.indexOf(pattern);
			if(num>-1) ret=list.get(num);
		}
		return ret;
	}

}
