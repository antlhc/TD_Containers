package container;

import java.util.Iterator;

public class IntPriorityQueue implements Queue<Integer>{
    private int capacity = 0;
    private Integer[] heapTable = null;
    private int nbElements = 0;

    IntPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heapTable = new Integer[capacity];

    }

    public static void main() {
        IntPriorityQueue testHeap = new IntPriorityQueue(10);
    }

    @Override
    public boolean insertElement(Integer integer) {
        if (this.size() == this.capacity) {
            this.capacity *= 2;
            Integer[] tempTab = new Integer[this.capacity];
            System.arraycopy(heapTable, 0, tempTab, 0, this.heapTable.length);
            this.heapTable = tempTab;
        }
        int i = 0;
        while (i<this.heapTable.length && this.heapTable[i] != null) {
            i++;
        }// Alors par construction, on a forcément trouvé un emplacement vide d'après le if
        // On insère le nouvel élément à la fin du tas
        this.heapTable[i] = integer;
        // On fait l'algorithme de remontage
        int indexParent = i/2-1;
        while (indexParent >= 0 && this.heapTable[indexParent] < this.heapTable[i]) {
            int temp = this.heapTable[indexParent];
            this.heapTable[indexParent] =
        }

        return false;
    }

    @Override
    public Integer element() {
        if (!this.isEmpty()) return this.heapTable[0];
        return null;
    }

    @Override
    public Integer popElement() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public int size() {
        return this.nbElements;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
