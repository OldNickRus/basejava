package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
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
            return;
        }
        if (size >= 1000){
            System.out.println("Can't save: resume storage overflow, size over 1000!");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int retIndex = this.getIndex(uuid);
        if (retIndex == -1) {
            System.out.println("Resume '" + uuid + "' not found in storage!");
            return null;
        }
        return storage[retIndex];
    }

    public void delete(String uuid) {
        int removeIndex = this.getIndex(uuid);
        if (removeIndex == -1) {
            System.out.println("Resume '" + uuid + "' does not exist in storage!");
        }
        System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex);
//            for (int i = removeIndex + 1; i < actualSize; i++) {
//                storage[i - 1] = storage[i];
//            }
        storage[size] = null;
        size--;
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
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
