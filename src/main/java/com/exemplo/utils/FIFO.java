package com.exemplo.utils;

import com.exemplo.models.CloneableClass;

import java.lang.reflect.Array;

public class FIFO<T extends CloneableClass> {

    private static final int DEFAULT_SIZE = 32;

    private T[] queue;
    private int first = 0;
    private int last = 0;
    private int maxSize;

    @SuppressWarnings("unchecked")
    public FIFO(Class<T> clazz){
        this(clazz, DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public FIFO(Class<T> clazz, int size){
        this.maxSize = size;
        queue = (T[]) Array.newInstance(clazz, size);
    }

    @SuppressWarnings("unchecked")
    public T get() throws CloneNotSupportedException {
        T obj = (T) queue[first].clone();
        queue[first] = null;
        first++;
        return obj;
    }

    public void put(T obj) {
        queue[last] = obj;
        last++;
    }

    public void clean(){

    }

    public int getCurrentSize(){
        return last - first;
    }

    public boolean isEmpty(){
        return getCurrentSize() == 0;
    }

    public boolean isFull(){
        return getCurrentSize() == maxSize;
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
