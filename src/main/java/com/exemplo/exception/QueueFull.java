package com.exemplo.exception;

import com.exemplo.utils.ErrorUtils;

public class QueueFull extends RuntimeException {

    public QueueFull(){
        super(ErrorUtils.getErrorMessage("err.queue.full"));
    }
}
