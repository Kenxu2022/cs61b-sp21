package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private int nextFirst;
    private T[] items;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 7;
        items = (T[]) new Object[8];
        nextLast = 0;
    }

    private void resizeUp() {
        int newLength = items.length * 2;
        int oldFirstLength = items.length - nextFirst - 1;
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, newArray, newLength - oldFirstLength, oldFirstLength);
        nextFirst = newLength - oldFirstLength - 1;
        items = newArray;
    }

    private void resizeDown() {
        int newLength = items.length / 2;
        int oldFirstLength = items.length - nextFirst - 1;
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, newArray, newLength - oldFirstLength, oldFirstLength);
        nextFirst = newLength - oldFirstLength - 1;
        items = newArray;
    }

    public void addFirst(T item) {
        if (nextFirst <= nextLast) {
            resizeUp();
        }
        items[nextFirst] = item;
        nextFirst = nextFirst - 1;
        size = size + 1;
    }

    public void addLast(T item) {
        if (nextLast >= nextFirst) {
            resizeUp();
        }
        items[nextLast] = item;
        nextLast = nextLast + 1;
        size = size + 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i < 8; i = i + 1) {
            System.out.print(items[i] + " ");
        }
        for (int i = 0; i < nextLast; i = i + 1) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size >= 16 && size <= items.length / 2) {
            resizeDown();
        }
        if (nextFirst == items.length - 1) {
            return removeLastFirst();
        }
        nextFirst = nextFirst + 1;
        size = size - 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size >= 16 && size <= items.length / 2) {
            resizeDown();
        }
        if (nextLast == 0) {
            return removeFirstLast();
        }
        nextLast = nextLast - 1;
        size = size - 1;
        return items[nextLast];
    }

    private T removeFirstLast() {
        T[] newArray = (T[]) new Object[items.length - 1];
        T removedItem = items[items.length - 1];
        System.arraycopy(items, 0, newArray, 0, items.length - 1);
        size = size - 1;
        items = newArray;
        return removedItem;
    }

    private T removeLastFirst() {
        T[] newArray = (T[]) new Object[items.length];
        T removedItem = items[0];
        System.arraycopy(items, 1, newArray, 0, items.length - 1);
        size = size - 1;
        nextLast= nextLast - 1;
        items = newArray;
        return removedItem;
    }

    private T[] tidyArray() {
        T[] newArray = (T[]) new Object[size];
        int firstLength = items.length - nextFirst - 1;
        System.arraycopy(items, nextFirst + 1, newArray, 0, firstLength);
        System.arraycopy(items, 0, newArray, firstLength, nextLast);
        return newArray;
    }

    public T get(int index) {
        T[] getArray = this.tidyArray();
        return getArray[index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentPosition;

        public ArrayDequeIterator() {
            currentPosition = 0;
        }

        public boolean hasNext() {
            return currentPosition < size;
        }

        public T next() {
            T returnItem = get(currentPosition);
            currentPosition = currentPosition + 1;
            return returnItem;
        }
    }

    public boolean oldEquals(Object o) {
        if (o instanceof ArrayDeque) {
            if (((ArrayDeque<T>) o).size == size) {
                T[] originalArray = this.tidyArray();
                T[] compareArray = (T[]) ((ArrayDeque<?>) o).tidyArray();
                for (int i = 0; i < size; i = i + 1) {
                    if (originalArray[i] != compareArray[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque testArrayDeque) {
            if (this == testArrayDeque) { // same reference
                return true;
            }
            if (this.size == testArrayDeque.size) {
                Iterator<T> arrayDequeIterator = this.iterator();
                Iterator<?> testArrayDequeIterator = testArrayDeque.iterator();
                while (arrayDequeIterator.hasNext()) {
                    if (arrayDequeIterator.next() != testArrayDequeIterator.next()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }
}