package com.exemplo.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueFullTest {

    @Test
    public void testQueueEmptyExceptionMessage() {
        QueueFull exception = new QueueFull();
        Assertions.assertEquals("The queue is full. Please remove items before adding new ones.", exception.getMessage());
    }
}
