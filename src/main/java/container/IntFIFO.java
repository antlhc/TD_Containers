package container;

import java.util.Iterator;

public class IntFIFO implements Queue<Integer>{
    @Override
    public boolean insertElement(Integer integer) {
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
