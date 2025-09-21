public class QueueStructure implements FillStructure {
    // Aqui cria o nó da lista encadeada, guarda a referencia do proximo
    private static class Node {
        int[] point;
        Node next;
        Node(int[] p){ this.point = p; }
    }

    // Controla o primeiro, o último e a quantidade de elementos na fila
    private Node head = null, tail = null;
    private int size = 0;

    // Adiciona um elemento na fila
    @Override
    public void add(int[] point) { // enqueue
        Node n = new Node(point);
        if (tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    // Remove um elemento da fila
    @Override
    public int[] remove() { // dequeue
        if (head == null) return null;
        int[] p = head.point;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return p;
    }

    //Verifica se tem elementos
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}