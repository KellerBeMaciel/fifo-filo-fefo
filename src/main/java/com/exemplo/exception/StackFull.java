package com.exemplo.exception;

import com.exemplo.utils.ErrorUtils;

public class StackFull extends RuntimeException {
    public StackFull(){
        super(ErrorUtils.getErrorMessage("err.stack.full"));
    }
}
