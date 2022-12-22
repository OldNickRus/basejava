package com.urise.webapp.exception;

public class ExistsStorageException extends StorageException{

    public ExistsStorageException(String uuid) {
        super(uuid, "Resume '" + uuid + "' already exists in storage!");
    }

}
