package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node head;
	private Node tail;

	private class Node {
		public Item item;
		public Node next;
		public Node prev;
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * is the deque empty?
	 * @return
	 */
	public boolean isEmpty(){
		return size == 0;
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
		check(item);
		Node newhead = new Node();
		if(head == null) {
			newhead.next=null;
		}
		else{
			newhead.next = head;
			head.prev = newhead;
		}
		newhead.prev = null;
		head = newhead;
		head.item = item;
		size++;
		if(tail == null){
			tail = head;
		}
	}

	/**
	 * add the item to the end
	 * @param item
	 */
	public void addLast(Item item){
		check(item);
		Node newtail = new Node();
		if(tail == null){
			newtail.prev = null;
		}else{
			newtail.prev = tail;
			tail.next = newtail;
		}
		newtail.next = null;
		tail = newtail;
		newtail.item = item;
		size++;
		if(head == null){
			head = tail;
		}
	}
	
	private void check(Item item){
		if(item == null){
			throw new NullPointerException();
		}
	}

	/**
	 * remove and return the item from the front
	 * @return
	 */
	public Item removeFirst(){
		emptyCheck();
		Item result = head.item;
		if(size == 1){
			head = null;
			tail = null;
		}else{
			head = head.next;
			head.prev = null;
		}
		size--;
		return result;
	}

	/**
	 * remove and return the item from the end
	 * @return
	 */
	public Item removeLast(){
		emptyCheck();
		Item result = tail.item;
		if(size==1){
			head = null;
			tail = null;
		}else{
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return result;
	}
	
	private void emptyCheck(){
		if(size == 0) throw new NoSuchElementException();
	}

	/**
	 * return an iterator over items in order from front to end
	 */
	@Override
	public Iterator<Item> iterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = head;
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
		Deque<Integer> deque = new Deque<Integer>();
		System.out.println(deque.isEmpty());
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addLast(3);
		deque.removeFirst();
		deque.addFirst(4);
		deque.removeLast();
		deque.addLast(5);
		Iterator<Integer> iter = deque.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}

}
