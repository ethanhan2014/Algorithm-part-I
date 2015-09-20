package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node first;
	private Node last;

	private class Node {
		private Item item;
		private Node next;
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
		size = 0;
		first = null;
	}

	/**
	 * is the deque empty?
	 * @return
	 */
	public boolean isEmpty(){
		return first == null;
	}

	/**
	 * return the number of items on the deque
	 * @return
	 */
	public int size(){
		return size;
	}

	/**
	 * add the item to the front
	 * @param item
	 */
	public void addFirst(Item item){

	}

	/**
	 * add the item to the end
	 * @param item
	 */
	public void addLast(Item item){

	}

	/**
	 * remove and return the item from the front
	 * @return
	 */
	public Item removeFirst(){
		return first.item;
	}

	/**
	 * remove and return the item from the end
	 * @return
	 */
	public Item removeLast(){
		return last.item;
	}

	/**
	 * return an iterator over items in order from front to end
	 */
	public Iterator<Item> iterator(){
		return new ListIterator();
	}

	private class Iterator ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}

	/**
	 * A test Client
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
