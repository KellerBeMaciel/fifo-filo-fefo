package com.exemplo.exception;

import com.exemplo.utils.ErrorUtils;

public class QueueEmpty extends RuntimeException {

    public QueueEmpty(){
        super(ErrorUtils.getErrorMessage("err.queue.empty"));
    }
}
