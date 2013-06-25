package com.orgsync.api;

import java.util.List;

/**
 * Some general utility methods that are used throughout the implementation of the API. These are generally available
 * with better implementations in other libraries (.e.g Google Guava), but a few methods aren't worth adding another
 * dependency.
 * 
 * @author steffyj
 * 
 */
public class Util {

    /**
     * Given a list and some separator string, create a string with the entries in the list joined with the separator.
     * 
     * @param list
     *            the list to join
     * @param sep
     *            the separator string
     * @return the joined string
     */
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

    /**
     * Check that an argument is not <code>null</code>, and if it is, throw a {@link NullPointerException}.
     * 
     * @param obj
     *            the object to check for <code>null</code>
     * 
     * @throws NullPointerException
     *             if the passed in object is null
     */
    /* package */static void checkNotNull(final Object obj) {
        if (obj == null) {
            throw new NullPointerException("Argument cannot be null!");
        }
    }
}
