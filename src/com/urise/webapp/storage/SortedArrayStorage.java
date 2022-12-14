package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        int insertIndex = -index-1;
        System.arraycopy(storage, insertIndex, storage, insertIndex+1, size-insertIndex);
        storage[insertIndex] = r;
        size++;
    }

    @Override
    protected void removeElement(int index) {
        int numMoved = size-(index+1);
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }

    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage,0, size, searchKey);
    }
}
