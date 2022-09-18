package lesson_2_collections;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Node prev;
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIter();
    }

    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    private class ListIter implements ListIterator<T> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }

        @Override
        public boolean hasPrevious() {
            return current.getPrev() != null;
        }

        @Override
        public T previous() {
            current = current.getPrev();
            return current.getValue();
        }

        @Override
        public int nextIndex() {
            return indexOf(current.getValue()) + 1;
        }

        @Override
        public int previousIndex() {
            return indexOf(current.getValue()) - 1;
        }

        @Override
        public void remove() {
            Node temp = current;
            delete(current.getValue());
            current = temp.getNext();
        }

        @Override
        public void set(T t) {
            int index = indexOf(t);
            if (index == -1) return;
            if (index < (size / 2)) {
                current = first;
                while (!current.getValue().equals(t)) {
                    current = current.getNext();
                }
            } else {
                current = last;
                while (!current.getValue().equals(t)) {
                    current = current.getPrev();
                }
            }
        }

        @Override
        public void add(T t) {
            int index = indexOf(current.getValue());
            index++;
            insert((index), t);
            current = current.getNext();
        }
    }

    private class Iter implements Iterator<T> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }
    }

    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void insertLast(T item) {
        Node newNode = new Node(last, item, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T deleteFirst() {
        T temp = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return temp;
    }

    public T deleteLast() {
        T temp = getLast();
        if (last.getPrev() == null) {
            first = null;
        } else {
            last.getPrev().setNext(null);
        }
        last = last.getPrev();
        size--;
        return temp;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return first.getValue();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return last.getValue();
    }

    public int indexOf(T item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (item.equals(current.getValue())) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }
        Node current;
        if (index < (size / 2)) {
            current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
        } else {                    //чтоб оптимальнее вставлять вближе к концу списка
            current = last;
            for (int i = 0; i < size - index; i++) {
                current = current.getPrev();
            }
        }
        Node newNode = new Node(current, item, current.getNext());
        current.getNext().setPrev(newNode);
        current.setNext(newNode);
        size++;
    }

    public boolean delete(T item) {
        if (isEmpty()) {
            return false;
        }
        if (item.equals(first.getValue())) {
            deleteFirst();
            return true;
        }
        Node current = first;
        while (current != null && !item.equals(current.getValue())) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            deleteLast();
            return true;
        }
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = first;
        for (int i = 0; i < size; i++) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
