package src.System;

public class CustomLinkedList<T> {
    private Node head;
    private int size;

    public CustomLinkedList() 
    {
        head = null;
        size = 0;
    }

    public void add(T element) 
    {
        Node newNode = new Node(element);
        if (head == null) 
        {
            head = newNode;
        } else 
        {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) 
        {
            current = current.next;
        }
        return current.data;
    }

    public int size() 
    {
        return size;
    }

    private class Node 
    {
        private T data;
        private Node next;

        public Node(T data) 
        {
            this.data = data;
            next = null;
        }
    }
}