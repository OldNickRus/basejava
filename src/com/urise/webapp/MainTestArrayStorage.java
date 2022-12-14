package com.urise.webapp;

import com.urise.webapp.exception.NotExistsStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;


/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r2);
        printAll();
        ARRAY_STORAGE.save(r1);

        ARRAY_STORAGE.update(r2);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.toString()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: ");
            ARRAY_STORAGE.get("dummy");
        }
        catch (NotExistsStorageException e) {
            System.out.println(e.getMessage());
        }

        printAll();
        ARRAY_STORAGE.delete(r1.toString());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        for(int i=0; i<1000; i++){
            Resume r = new Resume();
            try {
                ARRAY_STORAGE.save(r);
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("NumElement: " + i + " Storage.size = " + ARRAY_STORAGE.size());
            }
        }
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
