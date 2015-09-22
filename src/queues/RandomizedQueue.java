package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int size;

	/**
	 * construct an empty randomized queue
	 */
	public RandomizedQueue() {
		array = (Item[]) new Object[2];
		size = 0;
	}

	/**
	 * is the queue empty?
	 * @return true if this stack is empty; false otherwise
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * return the number of items on the queue
	 * @return size
	 */
	public int size(){
		return size;
	}

	private void resize(int capacity){
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for(int i = 0; i<size; i++){
			temp[i] = array[i];
		}
		array = temp;
	}

	/**
	 * add the item
	 * @param item
	 */
	public void enqueue(Item item){
		if(item == null) throw new NullPointerException();
		if(size == array.length) resize(2*array.length);
		array[size++] = item;
	}

	/**
	 * remove and return a random item
	 * @return
	 */
	public Item dequeue(){
		if(isEmpty()) throw new NoSuchElementException();
		int rand = StdRandom.uniform(size);
		Item item = array[rand];
		array[rand] = array[size - 1];
		array[--size] = null;
		if(size > 0 && size == array.length/4) resize(array.length/2);
		return item;
	}

	/**
	 * return (but do not remove) a random item
	 * @return
	 */
	public Item sample(){
		int rand = StdRandom.uniform(size);
		return array[rand];
	}

	/**
	 * return an independent iterator over items in random order
	 */
	@Override
	public Iterator<Item> iterator(){
		return new RandomIterator();
	}

	private class RandomIterator implements Iterator<Item>{

		private int[] indices;
		private int p;

		public RandomIterator(){
			indices = new int[size];
			for(int i = 0; i<size; i++) indices[i] = i;
			StdRandom.shuffle(indices);
			p = 0;
		}

		public boolean hasNext(){
			return p < size;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			return array[indices[p++]];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
