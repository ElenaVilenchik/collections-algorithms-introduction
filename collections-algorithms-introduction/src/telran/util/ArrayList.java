package telran.util;

import java.util.Arrays;
import java.util.Iterator;
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
		private int currentInd = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return currentInd < size;
		}

		@Override
		public T next() {
			flNext = true;
			return array[currentInd++];
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--currentInd);
			flNext = false;
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
		// array[size]==array[size-1] => Memory leak
		array[size] = null; // solution for preventing memory leak
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

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		int indDestination = 0;
		int sizeAfterDeletion = size;
		for (int indSource = 0; indSource < size; indSource++) {
			if (predicate.test(array[indSource])) {
				sizeAfterDeletion--;
			} else {
				array[indDestination++] = array[indSource];
			}
		}
		res = afterDeletionProcessing(sizeAfterDeletion);
		return res;
	}

	private boolean afterDeletionProcessing(int sizeAfterDeletion) {
		boolean res = false;
		if (sizeAfterDeletion < size) {
			res = true;
			for (int i = sizeAfterDeletion; i < size; i++) {
				array[i] = null;
			}
			size = sizeAfterDeletion;
		}
		return res;
	}
}
