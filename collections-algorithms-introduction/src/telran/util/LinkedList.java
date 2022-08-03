package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
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

		@Override
		public boolean hasNext() {
			return current != null;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T obj = current.obj;
			current = current.next;
			return obj;
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
	public boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if (index >= size || index < 0) {
	        throw new ArrayIndexOutOfBoundsException(); 
	    } else {
			removeByIndex(index);
			return true;
		}
	}

	private void removeByIndex(int index) {
		if (index == 0) {
			removeFirst();
		} else {
			if (index == size - 1) {
				removeLast();
			} else {
			Node<T> temp = head;
				for (int i = 0; i < index - 1; i++) {
					temp = temp.next;
				}
				temp.next = temp.next.next;
			}
		}
		size--;
	}

	private void removeLast() {
		Node<T> temp = head;
		for (int i = 0; i < size - 2; i++) {
			temp = temp.next;
		}
		tail = temp;
		temp.next = null;
	}

	private void removeFirst() {
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
		}
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int sizeNew = size;
		int index = 0;
		Node<T> node = getNodeIndex(index);
		while (index < size) {
			if (predicate.test(node.obj)) {
				removeByIndex(index);
			} else {
				index++;
			}
		}
		return size > sizeNew;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(int index, T obj) {
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
		size++;
		Node<T> newNode = new Node<>(obj);
		Node<T> afterNode = getNodeIndex(index);
		Node<T> beforeNode = afterNode.prev;
		newNode.next = afterNode;
		afterNode.prev = newNode;
		beforeNode.next = newNode;
		newNode.prev = beforeNode;
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
	public T remove(int index) {
		T res = null;
		Node<T> node = getNodeIndex(index);
		if (checkExistingIndex(index)) {
			res = node.obj;
			removeByIndex(index);
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
}
