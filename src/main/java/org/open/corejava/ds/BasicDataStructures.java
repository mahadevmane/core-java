package org.open.corejava.ds;

public class BasicDataStructures {

    public static void main(String[] args) {
        MyStack stack = new MyStack(10);

        if (!stack.isFull())
            stack.push(10);
    }
}

class MyStack {
    private final int capacity;
    private final int[] stackArray;
    private int top;

    public MyStack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.stackArray = new int[capacity];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == capacity - 1);
    }

    public void push(int item) {
        if (isFull()) {
            System.out.println("Stack overflow.");
        } else {
            stackArray[++top] = item;
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow.");
            return -1;
        } else {
            return stackArray[top--];
        }
    }

    public int peek() {
        return stackArray[top];
    }
}

class MyQueue {
    int front;
    int rear;
    int size;
    int capacity;
    int[] queueArray;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.front = this.rear = this.size = 0;
        this.queueArray = new int[capacity];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }

    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full.");
        } else {
            size++;
            queueArray[rear] = item;
            rear = (rear + 1) % capacity;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            int item = queueArray[front];
            size--;
            front = (front + 1) % capacity;

            return item;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }

        return queueArray[front];
    }

    public int peekRear() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }

        if (rear == 0)
            return queueArray[capacity - 1];

        return queueArray[rear - 1];
    }
}
