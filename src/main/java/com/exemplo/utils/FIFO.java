package java.com.exemplo.utils;

import java.lang.reflect.Array;

public class FIFO<T extends Cloneable> {

    private static final int DEFAULT_SIZE = 32;

    private T[] queue;
    private int first;
    private int last;
    private int size;

    @SuppressWarnings("unchecked")
    public FIFO(Class<T> clazz){
        this(clazz, DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public FIFO(Class<T> clazz, int size){
        this.size = size;
        queue = (T[]) Array.newInstance(clazz, size);
    }

    public T get() {
        return null;
    }

    public void put() {

    }

    public void clean(){

    }

    private void reorder() {

    }

    private void cleanGarbage(){

    }

    @Override
    public String toString(){
        return null;
    }
}
