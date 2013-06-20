package com.orgsync.api.model.accounts;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.IdAndName;

public class CustomProfileField {

    public static final Type TYPE = new TypeToken<CustomProfileField>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<CustomProfileField>>() {
    }.getType();

    private int id;
    private String name;
    private String instructions;
    private String type;
    private int page;
    private int position;
    private List<IdAndName> choices;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getInstructions() {
        return instructions;
    }

    public final String getType() {
        return type;
    }

    public final int getPage() {
        return page;
    }

    public final int getPosition() {
        return position;
    }

    public final List<IdAndName> getChoices() {
        return choices;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((choices == null) ? 0 : choices.hashCode());
        result = prime * result + id;
        result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + page;
        result = prime * result + position;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        CustomProfileField other = (CustomProfileField) obj;
        if (choices == null) {
            if (other.choices != null)
                return false;
        } else if (!choices.equals(other.choices))
            return false;
        if (id != other.id)
            return false;
        if (instructions == null) {
            if (other.instructions != null)
                return false;
        } else if (!instructions.equals(other.instructions))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (page != other.page)
            return false;
        if (position != other.position)
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomProfileField [id=" + id + ", name=" + name + ", instructions=" + instructions + ", type=" + type
                + ", page=" + page + ", position=" + position + ", choices=" + choices + "]";
    }

}
