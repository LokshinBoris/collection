package bbl.util;

import java.util.Comparator;

public class TreeMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V>
{
	
	@Override
	public K firstKey()
	{
		TreeSet<Entry<K, V>> tmp=(TreeSet<Entry<K, V>>)set;
		return tmp.first().getKey();
	}

	@Override
	public K lastKey()
	{
		TreeSet<Entry<K, V>> tmp=(TreeSet<Entry<K, V>>)set;
		return tmp.last().getKey();
	}

	@Override
	public K floorKey(K key)
	{
		TreeSet<Entry<K, V>> tmp=(TreeSet<Entry<K, V>>)set;
		Entry<K,V> entry=new Entry<K,V>(key,null);
		Entry<K,V> entryNew=tmp.floor(entry);
		return entryNew==null? null: entryNew.getKey();
	}

	@Override
	public K ceilingKey(K key) 
	{
		TreeSet<Entry<K, V>> tmp=(TreeSet<Entry<K, V>>)set;
		Entry<K,V> entry=new Entry<K,V>(key,null);
		Entry<K,V> entryNew=tmp.ceiling(entry);
		return entryNew==null? null: entryNew.getKey();
	}

	@Override
	protected Set<K> getEmptyKeySet() {
		
		return new TreeSet<K>();
	}
	public TreeMap() {
		set = new TreeSet<>();
	}
	public TreeMap(Comparator<Entry<K, V>> comp) {
		set = new TreeSet<>(comp);
	}

}
