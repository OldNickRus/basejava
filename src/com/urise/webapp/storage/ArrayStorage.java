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
        boolean found = false;
        for (int i = 0; i<size; i++){
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i] = r;
                found = true;
            }
        }
        if (!found) {
            System.out.println("com.urise.webapp.model.Resume '" + r.getUuid() + "' does not exist in array!");
        }
    }

    public void save(Resume r) {
        Resume existResume = this.get(r.getUuid());
        if (existResume == null) {
            if (size >= 1000){
                System.out.println("Resume array overflow: size over 1000!");
            } else {
                storage[size] = r;
                size++;
            }
        } else {
            System.out.println("Resume '" + r + "' already exists in array!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int removeIndex = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex);
//            for (int i = removeIndex + 1; i < actualSize; i++) {
//                storage[i - 1] = storage[i];
//            }
            storage[size] = null;
            size--;
        } else {
            System.out.println("Resume '" + uuid + "' does not exist in array!");
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
}
