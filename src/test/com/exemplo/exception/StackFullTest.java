package com.exemplo.exception;

import org.junit.jupiter.api.Test;

public class StackFullTest {

    @Test
    public void testStackFullExceptionMessage(){
        StackFull exception = new StackFull();
        assert exception.getMessage().equals("The stack is full. Please remove items before adding new ones.");
    }
}
