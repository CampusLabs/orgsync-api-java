package com.orgsync.api.messages;

import java.util.Date;

public class Org {

    private final int id;
    private final String short_name;
    private final String long_name;
    private final Date created_at;

    public Org(final int id, final String short_name, final String long_name,
            final Date created_at) {
        super();
        this.id = id;
        this.short_name = short_name;
        this.long_name = long_name;
        this.created_at = created_at;
    }

    public final int getId() {
        return id;
    }

    public final String getShort_name() {
        return short_name;
    }

    public final String getLong_name() {
        return long_name;
    }

    public final Date getCreated_at() {
        return created_at;
    }

    @Override
    public String toString() {
        return "Org [id=" + id + ", short_name=" + short_name + ", long_name="
                + long_name + ", created_at=" + created_at + "]";
    }

}
