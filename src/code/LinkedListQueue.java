package code;

public class LinkedListQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> SLL;

    public LinkedListQueue() {
        SLL = new SinglyLinkedList<E>();
    }

    public int size() {
        return SLL.size();
    }

    public boolean isEmpty() {
        return SLL.isEmpty();
    }

    public E first() {
        return SLL.first();
    }

    public void enqueue(E node) {
        SLL.addLast(node);
    }

    public E dequeue() {
        return SLL.removeFirst();
    }
}
