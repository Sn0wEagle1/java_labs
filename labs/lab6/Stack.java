package lab6;

import java.util.EmptyStackException;

public class Stack<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            resizeArray();
        }
        data[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = data[--size];
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        int newCapacity = data.length * 2;
        data = java.util.Arrays.copyOf(data, newCapacity);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.peek());

        stack.push(4);
        System.out.println(stack.pop());
    }
}
