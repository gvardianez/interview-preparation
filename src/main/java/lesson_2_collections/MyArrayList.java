package lesson_2_collections;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayList<T> {

    private T[] innerArray;
    private int size;
    private static final int DEFAULT_SIZE = 10;
    private static final double RATIO = 1.75;

    public MyArrayList(T[] innerArray) {
        size = innerArray.length;
        this.innerArray = Arrays.copyOf(innerArray, innerArray.length + 1);
    }

    public MyArrayList(Class<T> tClass, int size) {
        if (size < 0) throw new RuntimeException("Size < 0");
        this.innerArray = (T[]) Array.newInstance(tClass, size);
    }

    public MyArrayList(Class<T> tClass) {
        this.innerArray = (T[]) Array.newInstance(tClass, DEFAULT_SIZE);
    }

    public int getSize() {
        return size;
    }

    public T[] getInnerArray() {
        return innerArray;
    }

    public void add(T element) {
        checkSize();
        innerArray[size] = element;
        size++;
    }

    public void add(int index, T element) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Bad index");
        checkSize();
        for (int i = size; i > index; i--) {
            innerArray[i] = innerArray[i - 1];
        }
        innerArray[index] = element;
        size++;
    }

    public T get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Bad index");
        return innerArray[index];
    }

    public boolean remove(T element) {
        int indexElement = indexOf(element);
        if (indexElement == -1) return false;
        remove(indexElement);
        return true;
    }

    public boolean remove(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Bad index");
        for (int i = index; i < size; i++) {
            innerArray[i] = innerArray[i + 1];
        }
        size--;
        return true;
    }

    public int indexOf(T element) {
        for (int i = 0; i < innerArray.length; i++) {
            if (innerArray[i].equals(element)) return i;
        }
        return -1;
    }

    private void checkSize() {
        if (size + 1 == innerArray.length)
            innerArray = Arrays.copyOf(innerArray, (int) (innerArray.length * RATIO));
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(innerArray, size));
    }


}
