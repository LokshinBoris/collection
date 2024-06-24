package bbl.util;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V>
{
	protected Set<Entry<K,V>> set;
	abstract Set<K> getEmptyKeySet();
	@Override
	public V get(K key)
	{
		Entry<K,V> entryFind=new Entry<K,V>(key,null);
		Entry<K,V> entry=set.get(entryFind);
		return entry==null? null:entry.getValue();
	}

	@Override
	public V put(K key, V value)
	{
		V result=null;
		Entry<K,V> entry=new Entry<K,V>(key,value);
		Entry<K,V> entryNow=set.get(entry);
		if(entryNow==null)
		{
			set.add(entry);
		}
		else
		{
			result=entryNow.getValue();
			entryNow.setValue(value);
		}		
		return result;
	}

	@Override
	public V remove(K key) 
	{
		V res=null;
		Entry<K,V> entry=new Entry<K,V>(key,null);
		Entry<K,V> entryNow=set.get(entry);
		if(entryNow!=null)
		{
			res=entryNow.getValue();
			set.remove(entryNow);
		}
		return res;
	}

	@Override
	public Set<K> keySet()
	{
		Set<K> newSet= getEmptyKeySet(); 
		set.forEach(entry->newSet.add(entry.getKey()));
		return newSet;
	}

	@Override
	public Set<Entry<K, V>> entrySet() 
	{	
		Set<Entry<K, V>> setNew=new HashSet<Entry<K, V>>();
		set.forEach(entry->setNew.add(entry));
		return setNew;
	}

	@Override
	public Collection<V> values()
	{
		Collection<V> collection = new ArrayList<V>();
		Iterator <Entry<K,V>> it=set.iterator();
		
		while(it.hasNext())
		{
			collection.add(it.next().getValue());
		}
		return collection;
	}

}
