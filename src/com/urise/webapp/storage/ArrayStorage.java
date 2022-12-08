package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int updateIndex = this.getIndex(r.getUuid());
        if (updateIndex == -1) {
            System.out.println("Resume '" + r.getUuid() + "' does not exist in storage!");
            return;
        }
        storage[updateIndex] = r;
    }

    public void save(Resume r) {
        int oldIndex = this.getIndex(r.getUuid());
        if (oldIndex != -1) {
            System.out.println("Resume '" + r + "' already exists in storage!");
        } else if (size == storage.length) {
            System.out.println("Can't save: resume storage overflow!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int retIndex = this.getIndex(uuid);
        if (retIndex == -1) {
            System.out.println("Resume '" + uuid + "' not exists in storage!");
            return null;
        }
        return storage[retIndex];
    }

    public void delete(String uuid) {
        int removeIndex = this.getIndex(uuid);
        if (removeIndex == -1) {
            System.out.println("Resume '" + uuid + "' not exists in storage!");
        } else {
            storage[removeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resStorage = new Resume[size];
        System.arraycopy(storage, 0, resStorage, 0, size);
        return resStorage;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
