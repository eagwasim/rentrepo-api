package com.noubug.rentrepo.infrastructure.exceptions;

public class TaskQueueException extends RuntimeException {
    public TaskQueueException(String message) {
        super(message);
    }
}
