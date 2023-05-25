package com.solvd.project.System;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }
    public Node<T> getHead() {
        return head;
    }
    
    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) 
        {
            this.data = data;
            next = null;
        }

        public T getData() 
        {
            return data;
        }

        public Node<T> getNext() 
        {
            return next;
        }

    }

}