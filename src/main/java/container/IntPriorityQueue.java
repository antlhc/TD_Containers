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

    static void main() {
        IntPriorityQueue testHeap = new IntPriorityQueue(10);
        testHeap.insertElement(5);
        testHeap.insertElement(6);
        testHeap.insertElement(3);
        testHeap.insertElement(4);
        System.out.println(testHeap.popElement());
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
        // On fait l'algorithme de percolation vers le haut
        int indexParent = i/2-1;
        while (indexParent >= 0 && this.heapTable[indexParent] < this.heapTable[i]) {
            int temp = this.heapTable[indexParent];
            this.heapTable[indexParent] = this.heapTable[i];
            this.heapTable[i] = temp;
            i = indexParent;
            indexParent = i/2-1;
        }
        this.nbElements++;
        return true;
    }

    @Override
    public Integer element() {
        if (!this.isEmpty()) return this.heapTable[0];
        return null;
    }

    @Override
    public Integer popElement() {
        if (this.isEmpty()) return -1;
        // On recherche le premier emplacement vide
        int i = 0;
        while (i < this.heapTable.length && this.heapTable[i] != null) i++;
        // On echange le premier element et le dernier element i.e. l'élément à l'index i-1
        i = i-1;
        int poppedElement = this.heapTable[0];
        this.heapTable[0] = this.heapTable[i];
        // On supprime le dernier element
        this.heapTable[i] = null;
        // On percole vers le bas le premier element
        i = 0;
        int indexLeftChild = 1;
        int indexRightChild = 2;
        while ((indexLeftChild < this.nbElements-1 && this.heapTable[indexLeftChild] > this.heapTable[i])
                || (indexRightChild < this.nbElements-1 && this.heapTable[indexRightChild] > this.heapTable[i])) {
            if (this.heapTable[indexLeftChild] > this.heapTable[i]) {
                int temp = this.heapTable[indexLeftChild];
                this.heapTable[indexLeftChild] = this.heapTable[i];
                this.heapTable[i] = temp;
                i = indexLeftChild;
                indexLeftChild = 2 * i + 1;
                indexRightChild = 2 * i + 1;
            } else {
                int temp = this.heapTable[indexRightChild];
                this.heapTable[indexRightChild] = this.heapTable[i];
                this.heapTable[i] = temp;
                i = indexRightChild;
                indexLeftChild = 2 * i + 1;
                indexRightChild = 2 * i + 1;
            }
        }
        this.nbElements--;
        return poppedElement;
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
        return new IntPriorityQueueIterator(this);
    }

    static class IntPriorityQueueIterator implements Iterator<Integer>{
        Integer[] tab;
        int currentIndex;

        IntPriorityQueueIterator(IntPriorityQueue obj) {
            this.tab = obj.heapTable;
            this.currentIndex = 0;

        }

        @Override
        public boolean hasNext() {
            int indexLeftChild = 2*this.currentIndex + 1;
            int indexRightChild = 2*this.currentIndex + 2;
            return (indexLeftChild < this.tab.length && this.tab[indexLeftChild] != null) ||
                    (indexRightChild < this.tab.length && this.tab[indexRightChild] != null);
        }

        @Override
        public Integer next() {
            int indexLeftChild = 2*this.currentIndex + 1;
            int indexRightChild = 2*this.currentIndex + 2;
            if (this.tab[indexLeftChild] != null) {
                this.currentIndex = indexLeftChild;
                return this.tab[indexLeftChild];
            }
            else {
                this.currentIndex = indexRightChild;
                return this.tab[indexRightChild];
            }
        }
    }
}
