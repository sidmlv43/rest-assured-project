package com.comcast.helpers;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Helpers {


    public static <T, U extends Comparable<? super U>> boolean isSorted(
            List<T> list,
            Function<T, U> keyExtractor,
            boolean ascending
    ) {
        if (list == null || list.size() < 2) {
            return true;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            U current = keyExtractor.apply(list.get(i));
            U next = keyExtractor.apply(list.get(i + 1));

            int cmp = current.compareTo(next);

            if (ascending) {
                if (cmp > 0) {
                    return false; // current > next → not ascending
                }
            } else {
                if (cmp < 0) {
                    return false; // current < next → not descending
                }
            }
        }
        return true; // ✅ THIS IS CRITICAL
    }


}
