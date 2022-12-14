package com.urise.webapp.exception;

public class StorageException extends RuntimeException{
    private final String uuid;

    public StorageException(String uuid, String msg) {
        super(msg);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
