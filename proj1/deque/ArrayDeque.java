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
        T[] newArray = (T[]) new Object[newLength];
        int currentIndex = 0;
        for (T i : this) {
            newArray[currentIndex] = i;
            currentIndex = currentIndex + 1;
        }
        nextFirst = newLength - 1;
        nextLast = size();
        items = newArray;
    }

    private void resizeDown() {
        int newLength = items.length / 2;
        T[] newArray = (T[]) new Object[newLength];
        int currentIndex = 0;
        for (T i : this) {
            newArray[currentIndex] = i;
            currentIndex = currentIndex + 1;
        }
        nextFirst = newLength - 1;
        nextLast = size();
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
        for (T i : this) {
            System.out.print(i);
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
        nextFirst = getFirstIndex();
        size = size - 1;
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size >= 16 && size <= items.length / 2) {
            resizeDown();
        }
        nextLast = getLastIndex();
        size = size - 1;
        T removedItem = items[nextLast];
        items[nextLast] = null;
        return removedItem;
    }

    private int getFirstIndex() {
        int currentFirstIndex = nextFirst + 1;
        return currentFirstIndex % items.length;
    }

    private int getLastIndex() {
        int currentLastIndex = nextLast - 1;
        return (currentLastIndex + items.length) % items.length;
    }

    public T get(int index) { // treat index as offset
        int firstIndex = getFirstIndex();
        int realIndex = (firstIndex + index) % items.length;
        return items[realIndex];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentPosition;

        ArrayDequeIterator() {
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

//    public boolean oldEquals(Object o) {
//        if (o instanceof ArrayDeque) {
//            if (((ArrayDeque<T>) o).size == size) {
//                T[] originalArray = this.tidyArray();
//                T[] compareArray = (T[]) ((ArrayDeque<?>) o).tidyArray();
//                for (int i = 0; i < size; i = i + 1) {
//                    if (originalArray[i] != compareArray[i]) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> testDeque = (Deque<T>) o;
            if (this == testDeque) { // same reference
                return true;
            }
            if (this.size == testDeque.size()) {
                for (int i = 0; i < this.size; i = i + 1) {
                    T item = this.get(i);
                    T testItem = testDeque.get(i);
                    if (!item.equals(testItem)) {
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
