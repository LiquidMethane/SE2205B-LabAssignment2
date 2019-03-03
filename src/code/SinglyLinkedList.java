package code;

public class SinglyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return head.getElement();
    }

    public E last() {
        return tail.getElement();
    }

    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (isEmpty())
            tail = head;
        size ++;
    }

    public void addLast(E element) {
        if (isEmpty())
            addFirst(element);
        else {
            tail.next = new Node<>(element, tail);
            tail = tail.next;
            size ++;
        }

    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        E retElement = first();
        head = head.next;
        size --;
        if (isEmpty())
            tail = null;
        return retElement;
    }

}