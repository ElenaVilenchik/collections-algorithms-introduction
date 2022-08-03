package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	private class ArrayListIterator implements Iterator<T> {
		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[current++];
		}
	}

	@Override
	public boolean add(T obj) {
		ensureCapacity();
		array[size++] = obj;
		return true;
	}

	private void ensureCapacity() {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
	}

	@Override
	public boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if (index >= 0) {
			removeByIndex(index);
			return true;
		}
		return false;
	}

	private void removeByIndex(int index) {
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int sizeNew = size;
		int index = 0;
		while (index < size) {
			if (predicate.test(array[index])) {
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
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
		boolean isAdd = false;
		if (index >= 0 && index <= size) {
			isAdd = true;
			ensureCapacity();
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = obj;
			size++;
		}
		return isAdd;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (checkExistingIndex(index)) {
			res = array[index];
			removeByIndex(index);
		}
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int index) {
		return checkExistingIndex(index) ? array[index] : null;
	}
}
