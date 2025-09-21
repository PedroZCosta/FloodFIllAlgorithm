public class StackStructure implements FillStructure {

    //Cada elemento é um nó da pilha encadeada
    private static class Node {
        int[] point;
        Node next;
        Node(int[] p){ this.point = p; }
    }

    //Aponta para o último elemento e a quantidade de elementos na pilha
    private Node top = null;
    private int size = 0;

    //Adiciona um elemento na pilha
    @Override
    public void add(int[] point) { // push
        Node n = new Node(point);
        n.next = top;
        top = n;
        size++;
    }

    //Remove um elemento da pilha
    @Override
    public int[] remove() { // pop
        if (top == null) return null;
        int[] p = top.point;
        top = top.next;
        size--;
        return p;
    }

    //Verifica se tem elementos na pilha
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}