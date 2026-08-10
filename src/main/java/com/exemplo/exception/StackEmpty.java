package com.exemplo.exception;

import com.exemplo.utils.ErrorUtils;

public class StackEmpty extends RuntimeException {
    public StackEmpty(){
        super(ErrorUtils.getErrorMessage("err.stack.empty"));
    }
}
