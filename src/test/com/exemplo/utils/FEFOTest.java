package com.exemplo.utils;

import com.exemplo.abstracts.TestExpiredable;
import com.exemplo.models.ExpiredableCloneable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class FEFOTest {

    private final FEFO<TestExpiredable> fefo = new FEFO<>(TestExpiredable.class, 3);
    private Calendar calendar = Calendar.getInstance();;

    @Nested
    class General{
        @Test
        public void DefaultConstructor(){
            FEFO<TestExpiredable> fefoDefault = new FEFO<>(TestExpiredable.class);
            Assertions.assertEquals(32, fefoDefault.getStack().length);
        }

        @Test
        public void PutObject(){
            TestExpiredable obj1 = new TestExpiredable("Object 1", new Date(5000));
            fefo.put(obj1);
            Assertions.assertEquals(1, fefo.getCurrentSize());
        }

        @Test
        public void GetObject(){
            TestExpiredable obj1 = new TestExpiredable("Object 1", new Date(5000));
            fefo.put(obj1);
            TestExpiredable obj2 = fefo.get();
            Assertions.assertEquals(obj1, obj2);
        }

        @Test
        public void ToString(){
            TestExpiredable obj1 = new TestExpiredable("Object 1", new Date(1000000));
            TestExpiredable obj2 = new TestExpiredable("Object 2", new Date(500000));

            fefo.put(obj1);
            fefo.put(obj2);

            String expected = "FEFO=[" + obj1 + ", " + obj2 + "]";
            Assertions.assertEquals(expected, fefo.toString());
        }

    }

    @Test
    public void Ordanation(){
        calendar.set(2026, Calendar.APRIL, 1);
        TestExpiredable obj1 = new TestExpiredable("Obj 1", calendar.getTime());
        fefo.put(new TestExpiredable("Obj 1", calendar.getTime()));

        calendar.set(2026, Calendar.FEBRUARY, 15);
        TestExpiredable obj2 = new TestExpiredable("Obj 2", calendar.getTime());
        fefo.put(new TestExpiredable("Obj 2", calendar.getTime()));

        calendar.set(2026, Calendar.MARCH, 19);
        TestExpiredable obj3 = new TestExpiredable("Obj 3", calendar.getTime());
        fefo.put(new TestExpiredable("Obj 3", calendar.getTime()));

        Assertions.assertEquals(obj2, fefo.get());
        Assertions.assertEquals(obj3, fefo.get());
        Assertions.assertEquals(obj1, fefo.get());
    }
}
