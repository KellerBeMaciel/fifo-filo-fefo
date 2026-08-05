package com.exemplo.utils;

import com.exemplo.abstracts.TestObject;
import com.exemplo.exception.QueueEmpty;
import com.exemplo.exception.QueueFull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FIFOTest {

    private FIFO<TestObject> fifo = new FIFO<>(TestObject.class, 3);

    @Test
    public void QueuePutObject(){
        TestObject obj1 = new TestObject("Object 1");
        fifo.put(obj1);
        Assertions.assertEquals(1, fifo.getCurrentSize());
    }

    @Test void QueueGetObject() throws CloneNotSupportedException {
        TestObject obj1 = new TestObject("Object 1");
        fifo.put(obj1);
        TestObject obj2 = fifo.get();
        Assertions.assertEquals(obj1.name, obj2.name);
    }

    @Test
    public void QueueIsEmptyTrueNew(){
        Assertions.assertTrue(fifo.isEmpty());
        Assertions.assertThrowsExactly(QueueEmpty.class, () -> fifo.get());
    }

    @Test
    public void QueueIsEmptyTrueUsed() throws CloneNotSupportedException {
        TestObject obj1 = new TestObject("Object 1");
        fifo.put(obj1);
        fifo.get();

        Assertions.assertTrue(fifo.isEmpty());
        Assertions.assertThrowsExactly(QueueEmpty.class, () -> fifo.get());
    }

    @Test
    public void QueueIsEmptyFalse(){
        TestObject obj1 = new TestObject("Object 1");
        fifo.put(obj1);

        Assertions.assertFalse(fifo.isEmpty());
    }

    @Test
    public void QueueIsFullTrue(){
        TestObject obj1 = new TestObject("Object 1");
        TestObject obj2 = new TestObject("Object 2");
        TestObject obj3 = new TestObject("Object 3");
        TestObject obj4 = new TestObject("Object 4");

        fifo.put(obj1);
        fifo.put(obj2);
        fifo.put(obj3);

        Assertions.assertTrue(fifo.isFull());
        Assertions.assertThrowsExactly(QueueFull.class, () -> fifo.put(obj4));
    }

    @Test
    public void QueueIsFullFalse(){
        TestObject obj1 = new TestObject("Object 1");
        fifo.put(obj1);

        Assertions.assertFalse(fifo.isFull());
    }
}
