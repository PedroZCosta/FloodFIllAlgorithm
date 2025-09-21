import java.util.LinkedList;

public class QueueStructure implements FillStructure {
    private LinkedList<int[]> list = new LinkedList<>();

    @Override
    public void add(int[] point) {
        list.addLast(point); // enfileirar
    }

    @Override
    public int[] remove() {
        return list.removeFirst(); // desenfileirar
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}