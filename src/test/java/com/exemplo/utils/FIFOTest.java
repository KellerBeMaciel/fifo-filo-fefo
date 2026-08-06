package com.exemplo.utils;

import com.exemplo.abstracts.TestObject;
import com.exemplo.exception.QueueEmpty;
import com.exemplo.exception.QueueFull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class FIFOTest {

    private FIFO<TestObject> fifo = new FIFO<>(TestObject.class, 3);

    @Nested
    class General {
        @Test
        public void QueueDefaultConstructor(){
            FIFO<TestObject> fifoDefault = new FIFO<>(TestObject.class);
            Assertions.assertEquals(32, fifoDefault.getQueue().length);
        }

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
        public void QueueToStringTest(){
            TestObject obj1 = new TestObject("Object 1");
            TestObject obj2 = new TestObject("Object 2");

            fifo.put(obj1);
            fifo.put(obj2);

            String expected = "FIFO=[" + obj1 + ", " + obj2 + "]";
            Assertions.assertEquals(expected, fifo.toString());
        }
    }

    @Nested
    class QueueIsEmpty {
        @Test
        public void QueueIsEmptyTrueNew(){
            Assertions.assertTrue(fifo.isEmpty());
            Assertions.assertEquals(0, fifo.getCurrentSize());
            Assertions.assertThrowsExactly(QueueEmpty.class, () -> fifo.get());
        }

        @Test
        public void QueueIsEmptyTrueUsed() throws CloneNotSupportedException {
            TestObject obj1 = new TestObject("Object 1");
            fifo.put(obj1);
            fifo.get();

            Assertions.assertTrue(fifo.isEmpty());
            Assertions.assertEquals(0, fifo.getCurrentSize());
            Assertions.assertThrowsExactly(QueueEmpty.class, () -> fifo.get());
        }

        @Test
        public void QueueIsEmptyFalse(){
            TestObject obj1 = new TestObject("Object 1");
            fifo.put(obj1);

            Assertions.assertEquals(1, fifo.getCurrentSize());
            Assertions.assertFalse(fifo.isEmpty());
        }
    }

    @Nested
    class QueueIsFull {
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
            Assertions.assertEquals(3, fifo.getCurrentSize());
            Assertions.assertThrowsExactly(QueueFull.class, () -> fifo.put(obj4));
        }

        @Test
        public void QueueIsFullFalse(){
            TestObject obj1 = new TestObject("Object 1");
            fifo.put(obj1);

            Assertions.assertEquals(1, fifo.getCurrentSize());
            Assertions.assertFalse(fifo.isFull());
        }

        @Test
        public void QueueNewAfterFull() throws CloneNotSupportedException {
            TestObject obj1 = new TestObject("Object 1");
            TestObject obj2 = new TestObject("Object 2");
            TestObject obj3 = new TestObject("Object 3");
            TestObject obj4 = new TestObject("Object 4");

            fifo.put(obj1);
            fifo.put(obj2);
            fifo.put(obj3);
            // Queue Full
            Assertions.assertTrue(fifo.isFull());
            Assertions.assertEquals(3, fifo.getCurrentSize());

            fifo.get();
            fifo.get();
            // Remove 2
            Assertions.assertEquals(1, fifo.getCurrentSize());

            fifo.put(obj4);
            Assertions.assertEquals(2, fifo.getCurrentSize());
        }
    }

    @Test
    public void QueueClearTest(){
        TestObject obj1 = new TestObject("Object 1");
        TestObject obj2 = new TestObject("Object 2");

        fifo.put(obj1);
        fifo.put(obj2);
        Assertions.assertEquals(2, fifo.getCurrentSize());

        fifo.clear();
        Assertions.assertTrue(fifo.isEmpty());
        Assertions.assertTrue(Arrays.stream(fifo.getQueue()).allMatch(Objects::isNull));
    }
}
