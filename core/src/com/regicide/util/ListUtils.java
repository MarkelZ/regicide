package com.regicide.util;

import java.util.Collection;

public class ListUtils {
    private ListUtils() {
    }

    /**
     * The elements of the second collection that the first collection does not
     * contain are added to the first collection.
     * Therefore, the first collection is modified.
     * 
     * @param <T>   Type of the elements of the collections.
     * @param st1   First collection.
     * @param list2 Second collection.
     */
    public static <T> void addAllUnique(Collection<T> list1, Collection<T> list2) {
        for (T x : list2) {
            if (!list1.contains(x)) {
                list1.add(x);
            }
        }
    }
}