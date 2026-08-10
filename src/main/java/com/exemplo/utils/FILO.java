package com.exemplo.utils;

import com.exemplo.exception.StackEmpty;
import com.exemplo.exception.StackFull;
import com.exemplo.models.CloneableClass;

import java.lang.reflect.Array;

public class FILO<T extends CloneableClass> {

    private static final int MAX_SIZE = 32;

    private T[] stack;
    private int top = 0;
    private int max;

    public FILO(Class<T> clazz){
        this(clazz, MAX_SIZE);
    }

    @SuppressWarnings("unchecked")
    public FILO(Class<T> clazz, int size){
        this.max = size;
        this.stack = (T[]) Array.newInstance(clazz, size);
    }

    public void put(T obj){
        if(isFull())
            throw new StackFull();

        this.stack[top++] = obj;
    }

    @SuppressWarnings("unchecked")
    public T get() throws CloneNotSupportedException {
        if(isEmpty())
            throw new StackEmpty();

        T obj = (T) stack[--top].clone();
        stack[top] = null;
        return obj;
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public boolean isFull(){
        return top == max;
    }

    public int getCurrentSize(){
        return top;
    }

    public void clear(){
        for(int i = 0; i < top; i++){
            stack[i] = null;
        }

        top = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("FILO=[");

        for(int i = 0; i < top; i++){
            sb.append(stack[i]);
            if(i < top - 1){
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    protected T[] getStack(){
        return this.stack;
    }

}
