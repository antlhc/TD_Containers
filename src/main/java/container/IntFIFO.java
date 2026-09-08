package container;

import java.util.Arrays;
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
        test_fifo.insertElement(1);
        test_fifo.insertElement(2);
        test_fifo.insertElement(3);
        System.out.println(test_fifo.popElement());
        System.out.println(test_fifo.popElement());
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
        return Arrays.stream(this.tab).iterator(); // modifier pour que ça marche
    }
}
