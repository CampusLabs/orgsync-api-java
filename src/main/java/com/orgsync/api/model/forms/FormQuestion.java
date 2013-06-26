package com.orgsync.api.model.forms;

/**
 * A single question of a form.
 * 
 * @author steffyj
 * 
 */
public class FormQuestion {

    private int id;
    private String name;
    private String type;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        FormQuestion other = (FormQuestion) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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
        return "FormQuestion [id=" + id + ", name=" + name + ", type=" + type + "]";
    }

}
