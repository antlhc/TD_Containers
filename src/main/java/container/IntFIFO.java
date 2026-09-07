package container;

import java.util.Iterator;

public class IntFIFO implements Queue<Integer>{
    private int capacity = 0;
    private Integer[] tab = null;
    private int insertIndex = 0;
    private int popIndex = 0;

    IntFIFO(int capacity) {
        this.capacity = capacity;
        this.tab = new Integer[capacity];

    }

    public static void main() {
        IntFIFO test_fifo = new IntFIFO(10);

    }


    @Override
    public boolean insertElement(Integer integer) {
        if (this.insertIndex != this.popIndex || this.tab[this.insertIndex] != null) {
            this.tab[insertIndex] = integer;
            this.popIndex = 0;
            this.insertIndex += 1;
            this.insertIndex %= this.capacity;
            return true;
        } else {
            this.capacity *= 2;
            Integer[] tempTab = new Integer[this.capacity];
            for (int i = this.popIndex; i<this.tab.length; i++) {
                tempTab[i-this.popIndex] = this.tab[i];
            }
            for (int i=0; i<this.popIndex; i++) {
                tempTab[i+(this.tab.length-this.popIndex)] = this.tab[i];
            }
        }

        return false;
    }

    @Override
    public Integer element() {
        return 0;
    }

    @Override
    public Integer popElement() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
