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
		
		public Node(Item item, Node next, Node prev){
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
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
		if(item == null) throw new NullPointerException();
		Node tmp = new Node(item,head,null);
		if(head != null) head.prev = tmp;
		head = tmp;
		if(tail == null) tail = tmp;
		size++;
	}

	/**
	 * add the item to the end
	 * @param item
	 */
	public void addLast(Item item){
		if(item == null) throw new NullPointerException();
		Node tmp = new Node(item,null,tail);
		if(tail != null) tail.next = tmp;
		tail = tmp;
		if(head == null) head = tmp;
		size++;
	}

	/**
	 * remove and return the item from the front
	 * @return
	 */
	public Item removeFirst(){
		if(size == 0) throw new NoSuchElementException();
		Node tmp = head;
		head = head.next;
		head.prev = null;
		size--;
		return tmp.item;
	}

	/**
	 * remove and return the item from the end
	 * @return
	 */
	public Item removeLast(){
		if(size == 0) throw new NoSuchElementException();
		Node tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;
		return tmp.item;
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
