package com.urise.webapp.exception;

public class StorageOverFlowException extends StorageException{

    public StorageOverFlowException(String uuid, int size) {
        super(uuid, "Storage overflow, more than " + size + " elements added!");
    }

}
