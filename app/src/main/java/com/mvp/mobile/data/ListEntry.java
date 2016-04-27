/**
 * 
 */
package com.mvp.mobile.data;



import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author kituri
 * List用这个
 */
public class ListEntry extends Entry{

	private static final long serialVersionUID = 8369729682149235659L;
	
	private List<Entry> mEntries = Collections.synchronizedList(new EntryList());

	private Object lock = new Object();
	
	public void add(Entry entry) {
		synchronized (lock){
			mEntries.add(entry);
		}
	}
	
	public void add(Entry entry,int index){
		synchronized (lock){
			mEntries.add(0, entry);
		}
	}
	
	public void inSet(int index, Entry entry) {
		synchronized (lock){
			mEntries.add(index, entry);
		}
	}

	public void remove(Entry entry) {
		synchronized (lock){
			mEntries.remove(entry);
		}
	}
	
	public void clear() {
		synchronized (lock){
			mEntries.clear();
		}
	}

	public void addAll(Collection<? extends Entry> collection){
		synchronized (lock){
			mEntries.addAll(collection);
		}
	}

	public List<Entry> getEntries() {
		return mEntries;
	}
	
//	public void print(){
//		for(Entry entry:mEntries){
//			entry.print();
//		}
//	}

}
