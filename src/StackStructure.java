import java.util.ArrayList;

public class StackStructure implements FillStructure {
    private ArrayList<int[]> list = new ArrayList<>();

    @Override
    public void add(int[] point) {
        list.add(point); // empilhar
    }

    @Override
    public int[] remove() {
        return list.remove(list.size() - 1); // desempilhar
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}