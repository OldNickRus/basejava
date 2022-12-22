package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistsStorageException;
import com.urise.webapp.exception.NotExistsStorageException;
import com.urise.webapp.exception.StorageOverFlowException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 1000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int retIndex = this.getIndex(uuid);
        if (retIndex < 0) {
            throw new NotExistsStorageException(uuid);
        }
        return storage[retIndex];
    }

    public void update(Resume r) {
        int updateIndex = this.getIndex(r.getUuid());
        if (updateIndex < 0) {
            throw new NotExistsStorageException(r.getUuid());
        }
        storage[updateIndex] = r;
    }

    public void save(Resume r) {
        int index = this.getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistsStorageException(r.getUuid());
        } else if (size == storage.length) {
            throw new StorageOverFlowException(r.getUuid(), storage.length);
        } else {
            insertElement(r, index);
        }
    }

    public void delete(String uuid) {
        int index = this.getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume '" + uuid + "' not exists in storage!");
        } else {
            removeElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void removeElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);


}
