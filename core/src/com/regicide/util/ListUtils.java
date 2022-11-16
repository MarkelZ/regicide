package com.regicide.util;

import java.util.ArrayList;

public class ListUtils {
    private ListUtils() {
    }

    /**
     * The elements of the second list that the first list does not contain are
     * added to the first list. Therefore, the first list is modified.
     * 
     * @param <T>   Type of the elements of the arraylists.
     * @param st1   First list.
     * @param list2 Second list.
     */
    public static <T> void addAllUnique(ArrayList<T> list1, ArrayList<T> list2) {
        for (T x : list2) {
            if (!list1.contains(x)) {
                list1.add(x);
            }
        }
    }
}