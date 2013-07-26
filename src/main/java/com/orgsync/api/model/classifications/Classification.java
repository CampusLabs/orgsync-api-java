package com.orgsync.api.model.classifications;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * A model for a classification.
 * 
 * @author steffyj
 * 
 */
public class Classification {

    public static final Type TYPE = new TypeToken<Classification>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<Classification>>() {
    }.getType();

    private final int id;
    private final String name;

    public Classification(final int id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Classification other = (Classification) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Classification [id=" + id + ", name=" + name + "]";
    }

}
