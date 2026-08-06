package com.exemplo.utils;

import com.exemplo.exception.QueueEmpty;
import com.exemplo.exception.QueueFull;
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
        if(isEmpty())
            throw new QueueEmpty();

        T obj = (T) queue[first].clone();
        queue[first] = null;
        first++;
        return obj;
    }

    public void put(T obj) {
        if(isFull())
            throw new QueueFull();

        if(last == maxSize)
            reorder();


        queue[last] = obj;
        last++;
    }

    public void clear(){
        first = 0;
        last = 0;

        cleanGarbage();
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
        int index = 0;

        for(int i = first; i < last; i++){
            queue[index] = queue[i];
            index++;
        }

        last -= first;
        first = 0;

        cleanGarbage();
    }

    private void cleanGarbage(){
        for(int i = last; i < maxSize; i++){
            queue[i] = null;
        }
    }

    // Protected for tests only
    protected T[] getQueue(){
        return queue;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("FIFO=[");

        for(int i = first; i < last; i++){
            sb.append(queue[i]);
            if(i < last - 1){
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
