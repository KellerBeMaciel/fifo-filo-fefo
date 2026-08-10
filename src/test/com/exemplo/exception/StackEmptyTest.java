package com.exemplo.exception;

import org.junit.jupiter.api.Test;

public class StackEmptyTest {

    @Test
    public void testStackEmptyExceptionMessage(){
        StackEmpty exception = new StackEmpty();
        assert exception.getMessage().equals("The stack is empty. Please add items before proceeding.");
    }
}
