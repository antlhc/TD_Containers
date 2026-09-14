package container;

import java.util.Arrays;
import java.util.Iterator;

public class IntFIFO implements Queue<Integer>{
    private int capacity;
    private Integer[] tab;
    private int insertIndex;
    private int popIndex;

    IntFIFO(int capacity) {
        this.capacity = capacity;
        this.tab = new Integer[capacity];

    }

    static void main() {
        IntFIFO test_fifo = new IntFIFO(10);
        test_fifo.insertElement(1);
        test_fifo.insertElement(2);
        test_fifo.insertElement(3);
        System.out.println(test_fifo.popElement());
        System.out.println(test_fifo.size());
    }


    @Override
    public boolean insertElement(Integer integer) {
        if (this.insertIndex != this.popIndex || this.tab[this.insertIndex] == null) {
            this.tab[insertIndex] = integer;
            this.popIndex = 0;
            this.insertIndex += 1;
            this.insertIndex %= this.capacity;
        } else {
            this.capacity *= 2;
            int len = this.tab.length;
            Integer[] tempTab = new Integer[this.capacity];
            for (int i = this.popIndex; i<len; i++) {
                tempTab[i-this.popIndex] = this.tab[i];
            }
            for (int i=0; i<this.popIndex; i++) {
                tempTab[i+(len-this.popIndex)] = this.tab[i];
            }
            this.tab = tempTab;
            this.popIndex = 0;
            this.insertIndex = len;
        }
        return true;
    }

    @Override
    public Integer element() {
        // max
        if (!this.isEmpty()) return this.tab[this.popIndex];
        else return null;
    }

    @Override
    public Integer popElement() { // modifier pour gérer mieux le cas où il ne reste plus qu'un seul élément dans la queue
        Integer value = this.element();
        if (value != null) {
            if (this.popIndex != this.tab.length-1) this.popIndex++;
            else this.popIndex = 0;
            return value;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (this.popIndex == this.insertIndex);
    }

    @Override
    public int size() {
        if (this.insertIndex-this.popIndex >= 0) return this.insertIndex-this.popIndex;
        else return this.insertIndex + this.tab.length - this.popIndex;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntFIFOIterator(this);
    }

    static class IntFIFOIterator implements Iterator<Integer> {
        int currentIndex;
        Integer[] tab;
        int endFIFO;
        int beginFIFO;

        IntFIFOIterator(IntFIFO list) {
            this.currentIndex = list.popIndex;
            this.tab = list.tab;
            this.endFIFO = list.insertIndex;
            this.beginFIFO = list.popIndex;
        }

        @Override
        public Integer next() {
            int temp = this.tab[currentIndex];
            this.currentIndex++;
            if (this.currentIndex >= this.tab.length) this.currentIndex = 0;
            return temp;
        }

        @Override
        public boolean hasNext() {
            return (this.currentIndex >= this.endFIFO && this.currentIndex <= this.beginFIFO);
        }
    }
}
