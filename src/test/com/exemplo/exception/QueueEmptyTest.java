package com.exemplo.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class QueueEmptyTest {

    @Test
    public void testQueueEmptyExceptionMessage() {
        QueueEmpty exception = new QueueEmpty();
        Assertions.assertEquals("The queue is empty. Please add items before proceeding.", exception.getMessage());
    }
}
