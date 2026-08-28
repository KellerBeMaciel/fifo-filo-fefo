package com.exemplo.utils;

import com.exemplo.exception.QueueEmpty;
import com.exemplo.exception.QueueFull;
import com.exemplo.models.ExpiredableCloneable;

import java.lang.reflect.Array;
import java.util.Date;

public class FEFO <T extends ExpiredableCloneable> {

    private static final int DEFAULT_SIZE = 32;

    private T[] items;
    private int maxSize;
    private int currentSize = 0;

    @SuppressWarnings("unchecked")
    public FEFO(Class<T> clazz){
        this(clazz, DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public FEFO(Class<T> clazz, int size){
        this.maxSize = size;
        items = (T[]) Array.newInstance(clazz, size);
    }

    public void put(T obj){
        if(this.isFull())
            throw new QueueFull();

        int position = searchPositionByExpirationDate(obj.getExpirationDate());

        T oldObj = items[position];
        items[position] = obj;

        for(int i = position + 1; i <= currentSize; i++){
            T temp = items[i];
            items[i] = oldObj;
            oldObj = temp;
        }

        currentSize++;
    }

    private int searchPositionByExpirationDate(Date expirationDate){
        if(this.isEmpty()) return 0;

        if(expirationDate.after(items[0].getExpirationDate())) return 0;

        if(expirationDate.before(items[currentSize - 1].getExpirationDate())) return currentSize;

        return searchPositionByExpirationDate(expirationDate, 0, currentSize - 1);
    }

    private int searchPositionByExpirationDate(Date expirationDate, int begin, int end){
        Date itemExpirationDate;

        if(begin == end) {
            itemExpirationDate = items[begin].getExpirationDate();

            if(expirationDate.before(itemExpirationDate)){
                return begin + 1;
            } else {
                return begin;
            }
        }

        int middle = (begin + (end - begin)) / 2;
        itemExpirationDate = items[middle].getExpirationDate();

        if(expirationDate.before(itemExpirationDate)) {
            return searchPositionByExpirationDate(expirationDate, middle + 1, end);
        } else {
            return searchPositionByExpirationDate(expirationDate, begin, middle);
        }
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public int getCurrentSize() { return currentSize; }

    // Protected for tests only
    protected T[] getStack(){
        return items;
    }

    public T get(){
        if(this.isEmpty())
            throw new QueueEmpty();

        T obj = items[currentSize - 1];
        items[currentSize - 1] = null;
        currentSize--;

        return obj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FEFO=[");
        for (int i = 0; i < currentSize; i++) {
            sb.append(items[i].toString());
            if (i < currentSize - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
