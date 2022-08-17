package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
	public static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T obj = current.obj;
			current = current.next;
			flNext = true;
			return obj;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			if (current == null) {
				removeNode(tail);
			} else {
				removeNode(current.prev);
			}
			flNext = false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public boolean add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean add(int index, T obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		boolean res = false;
		if (index >= 0 && index <= size) {
			res = true;
			if (index == size) {
				add(obj);
			} else if (index == 0) {
				addHead(obj);
			} else {
				addIndex(index, obj);
			}
		}
		return res;
	}

	private void addIndex(int index, T obj) {
		Node<T> newNode = new Node<>(obj);
		Node<T> afterNode = getNodeIndex(index);
		Node<T> beforeNode = afterNode.prev;
		newNode.next = afterNode;
		afterNode.prev = newNode;
		beforeNode.next = newNode;
		newNode.prev = beforeNode;
		size++;
	}

	private Node<T> getNodeIndex(int index) {
		return index > size / 2 ? getNodeRightToLeft(index) : getNodeLeftToRight(index);
	}

	private Node<T> getNodeLeftToRight(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeRightToLeft(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private void addHead(T obj) {
		size++;
		Node<T> newNode = new Node<>(obj);
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if (index >= 0) {
			Node<T> node = getNodeIndex(index);
			removeNode(node);
			return true;
		}
		return false;
	}

	private void removeNode(Node<T> node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
		size--;
	}

	private void removeMiddle(Node<T> node) {
		Node<T> nodeAfter = node.next;
		Node<T> nodeBefore = node.prev;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;

	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
	}

	private void removeHead() {
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (checkExistingIndex(index)) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
			removeNode(node);
		}
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			T node = get(i);
			if (node.equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for (int i = size - 1; i >= 0; i--) {
			T node = get(i);
			if (node.equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int index) {
		T res = null;
		if (checkExistingIndex(index)) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * performs reversing of the objects order current - {10, -5, 30) -> after
	 * reverse {30, -5, 10}
	 * @return 
	 * 
	 * @return
	 */
//	public void reverse() {
//		int limit = size / 2;
//		Node<T> forwardCurrent = head;
//		Node<T> backwardCurrent = tail;
//		for (int i = 0; i < limit; i++, forwardCurrent = forwardCurrent.next, backwardCurrent = backwardCurrent.prev) {
//			T tmp = forwardCurrent.obj;
//			forwardCurrent.obj = backwardCurrent.obj;
//			backwardCurrent.obj = tmp;
//		}
//	}
	
	
	public Object reverse(Node<T> node) {
		if (node == null) {
			return null;
		}
		Node<T> tmp = node.next;
		node.next = node.prev;
		node.prev = tmp;
		if (node.prev == null) {
			return node;
		}
		return reverse(node.prev);
	}
}
