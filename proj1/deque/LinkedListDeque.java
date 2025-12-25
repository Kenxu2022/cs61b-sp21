package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private Node prevNode;
        private T item;
        private Node nextNode;

        private Node(Node prevNode, T i, Node nextNode) {
            this.prevNode = prevNode;
            item = i;
            this.nextNode = nextNode;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.nextNode = sentinel;
        sentinel.prevNode = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.nextNode = new Node(sentinel, item, sentinel.nextNode);
        sentinel.nextNode.nextNode.prevNode = sentinel.nextNode;
        size = size + 1;
    }

    public void addLast(T item) {
        sentinel.prevNode = new Node(sentinel.prevNode, item, sentinel);
        sentinel.prevNode.prevNode.nextNode = sentinel.prevNode;
        size = size + 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node currentNode = sentinel;
        while (true) {
            if (currentNode.nextNode != sentinel) {
                System.out.print(currentNode.nextNode.item + " ");
                currentNode = currentNode.nextNode;
            } else {
                System.out.println();
                break;
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node deletedNode = sentinel.nextNode;
        sentinel.nextNode = sentinel.nextNode.nextNode;
        sentinel.nextNode.prevNode = sentinel;
        size = size - 1;
        return deletedNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node deletedNode = sentinel.prevNode;
        sentinel.prevNode = sentinel.prevNode.prevNode;
        sentinel.prevNode.nextNode = sentinel;
        size = size - 1;
        return deletedNode.item;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        Node currentNode = sentinel;
        if (index < size / 2) { // counting forward
            for (int i = 0; i <= index; i = i + 1) {
                currentNode = currentNode.nextNode;
            }
        } else { // counting backward
            for (int i = 0; i < size - index; i = i + 1) {
                currentNode = currentNode.prevNode;
            }
        }
        return currentNode.item;
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.nextNode);
    }

    private T getRecursive(int index, Node currentNode) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursive(index - 1, currentNode.nextNode);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int currentPosition;

        LinkedListDequeIterator() {
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
//        if (o instanceof LinkedListDeque) {
//            if (((LinkedListDeque<?>) o).size == size) {
//                Node currentNode = sentinel;
//                Node currentTestNode = (Node) ((LinkedListDeque<?>) o).sentinel;
//                for (int i = 0; i < size; i = i + 1) {
//                    if (currentNode.nextNode.item != currentTestNode.nextNode.item) {
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
    public boolean equals(Object o) { // utilize iteration
        if (o instanceof Deque) {
            Deque<T> testDeque = (Deque<T>) o;
            if (this == testDeque) { // same reference
                return true;
            }
            if (this.size == testDeque.size()) {
                Iterator<T> currentIterator = this.iterator();
                Iterator<?> testIterator = testDeque.iterator();
                while (currentIterator.hasNext()) {
                    if (!currentIterator.next().equals(testIterator.next())) {
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
