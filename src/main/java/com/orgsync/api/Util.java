package com.orgsync.api;

import java.util.List;

public class Util {

    // TODO don't want to depend on a library just for join...
    public static <T> String joinList(final List<T> list, final String sep) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            str.append(list.get(i).toString());
            if (i < list.size() - 1) {
                str.append(sep);
            }
        }

        return str.toString();
    }

    // Another reason for Guava...
    /* package */static void checkNotNull(final Object obj) {
        if (obj == null) {
            throw new NullPointerException("Argument cannot be null!");
        }
    }
}
