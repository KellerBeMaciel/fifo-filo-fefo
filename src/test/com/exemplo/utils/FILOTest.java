package com.exemplo.utils;

import com.exemplo.abstracts.TestObject;
import com.exemplo.exception.StackEmpty;
import com.exemplo.exception.StackFull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FILOTest {

    private final FILO<TestObject> filo = new FILO<>(TestObject.class, 3);

    @Test
    public void testDefaultConstructor() {
        FILO<TestObject> filoTest = new FILO<>(TestObject.class);
        Assertions.assertEquals(32, filoTest.getStack().length);
    }

    @Test
    public void testPutObject() {
        TestObject obj1 = new TestObject("Object 1");
        filo.put(obj1);
        Assertions.assertEquals(1, filo.getCurrentSize());
    }

    @Test
    public void testGetObject() throws CloneNotSupportedException {
        TestObject obj1 = new TestObject("Object 1");
        filo.put(obj1);
        Assertions.assertEquals(obj1, filo.get());
    }

    @Test
    public void testToString() {
        TestObject obj1 = new TestObject("Object 1");
        TestObject obj2 = new TestObject("Object 2");

        filo.put(obj1);
        filo.put(obj2);

        Assertions.assertEquals("FILO=[" + obj1 + ", " + obj2 + "]", filo.toString());
    }

    @Test
    public void testIsEmptyNew() {
        Assertions.assertTrue(filo.isEmpty());
        Assertions.assertEquals(0, filo.getCurrentSize());
    }

    @Test
    void testIsEmptyException() throws CloneNotSupportedException {
        Assertions.assertThrowsExactly(StackEmpty.class, () -> filo.get());
    }

    @Test
    public void testIsEmptyUsed() throws CloneNotSupportedException {
        filo.put(new TestObject("Object 1"));
        filo.get();

        Assertions.assertTrue(filo.isEmpty());
        Assertions.assertEquals(0, filo.getCurrentSize());
    }

    @Test
    public void testIsFull() {
        TestObject obj1 = new TestObject("Object 1");
        TestObject obj2 = new TestObject("Object 2");
        TestObject obj3 = new TestObject("Object 3");

        filo.put(obj1);
        filo.put(obj2);
        filo.put(obj3);

        Assertions.assertTrue(filo.isFull());
        Assertions.assertEquals(3, filo.getCurrentSize());
    }

    @Test
    public void testIsFullException() {
        TestObject obj1 = new TestObject("Object 1");
        TestObject obj2 = new TestObject("Object 2");
        TestObject obj3 = new TestObject("Object 3");

        filo.put(obj1);
        filo.put(obj2);
        filo.put(obj3);

        Assertions.assertThrowsExactly(StackFull.class, () -> filo.put(new TestObject("Object 4")));
    }
}
